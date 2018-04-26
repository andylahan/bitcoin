package com.ms.bitcoin.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static com.ms.bitcoin.provider.ExmoExchangeRateProvider.RATE_KEY;
import static com.ms.bitcoin.provider.ExmoExchangeRateProvider.SELL_PRICE_KEY;
import static com.ms.bitcoin.provider.ExmoExchangeRateProvider.URL;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExmoExchangeRateProvider.class})
public class ExmoExchangeRateProviderTest {

    @Mock
    ExchangeRateProvider exchangeRateProvider;

    @Test
    public void getExchangeRates() throws Exception {

        exchangeRateProvider = mock(ExchangeRateProvider.class);
        whenNew(ExchangeRateProvider.class).withNoArguments().thenReturn(exchangeRateProvider);

        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> rateKeyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> sellPriceCaptor = ArgumentCaptor.forClass(String.class);

        Double expectedResult = 1.234;

        when(exchangeRateProvider.getExchangeRate(urlCaptor.capture(),rateKeyCaptor.capture(),sellPriceCaptor.capture()))
                .thenReturn(expectedResult);

        ExmoExchangeRateProvider exmoExchangeRateProvider = new ExmoExchangeRateProvider();
        Double result = exmoExchangeRateProvider.getSellPrice();
        assertEquals(expectedResult,result);
        assertEquals(urlCaptor.getValue(),URL);
        assertEquals(rateKeyCaptor.getValue(),RATE_KEY);
        assertEquals(sellPriceCaptor.getValue(),SELL_PRICE_KEY);
    }
}
