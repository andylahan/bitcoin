package com.ms.bitcoin.service;

import com.ms.bitcoin.provider.BlockChainExchangeRateProvider;
import com.ms.bitcoin.provider.ExmoExchangeRateProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BlockChainExchangeRateProvider.class, ExmoExchangeRateProvider.class, ExchangeRateService.class})
public class ExchangeRateServiceTest {

    @Mock
    private BlockChainExchangeRateProvider blockChainExchangeRateProvider;

    @Mock
    private ExmoExchangeRateProvider exmoExchangeRateProvider;

    private final ByteArrayOutputStream systemOut = new ByteArrayOutputStream();
    private final ByteArrayOutputStream systemErr = new ByteArrayOutputStream();

    private static final double LOWER_VALUE = 1.234;
    private static final double HIGHER_VALUE = 2.234;

    @Before
    public void setup() throws Exception {
        initMocks(this);

        whenNew(BlockChainExchangeRateProvider.class).withNoArguments().thenReturn(blockChainExchangeRateProvider);
        whenNew(ExmoExchangeRateProvider.class).withNoArguments().thenReturn(exmoExchangeRateProvider);

        System.setOut(new PrintStream(systemOut));
        System.setErr(new PrintStream(systemErr));
    }

    @After
    public void reset() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void exmoGreaterThanBlockchain() {
        when(blockChainExchangeRateProvider.getSellPrice()).thenReturn(LOWER_VALUE);
        when(exmoExchangeRateProvider.getSellPrice()).thenReturn(HIGHER_VALUE);

        new ExchangeRateService().compareExchangeRates();

        assertEquals("exmo.com: "+HIGHER_VALUE+"\r\n", systemOut.toString());
    }

    @Test
    public void exmoLessThanBlockchain() {
        when(blockChainExchangeRateProvider.getSellPrice()).thenReturn(HIGHER_VALUE);
        when(exmoExchangeRateProvider.getSellPrice()).thenReturn(LOWER_VALUE);

        new ExchangeRateService().compareExchangeRates();

        assertEquals("blockchain: "+HIGHER_VALUE+"\r\n", systemOut.toString());
    }

    @Test
    public void exmoEqualsBlockchain() {
        when(blockChainExchangeRateProvider.getSellPrice()).thenReturn(LOWER_VALUE);
        when(exmoExchangeRateProvider.getSellPrice()).thenReturn(LOWER_VALUE);

        new ExchangeRateService().compareExchangeRates();

        assertEquals("exmo.com & blockchain: "+LOWER_VALUE+"\r\n", systemOut.toString());
    }

    @Test
    public void exceptionHandling() {
        when(blockChainExchangeRateProvider.getSellPrice()).thenReturn(null);
        when(exmoExchangeRateProvider.getSellPrice()).thenReturn(null);

        ExchangeRateService.main(null);

        assertEquals("Exception comparing rates: java.lang.NullPointerException\r\n",systemOut.toString());
        assertTrue(systemErr.toString().contains("at com.ms.bitcoin.service.ExchangeRateService.compareExchangeRates(ExchangeRateService.java"));
    }

}
