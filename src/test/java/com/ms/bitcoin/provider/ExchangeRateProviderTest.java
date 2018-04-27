package com.ms.bitcoin.provider;

import org.junit.Test;

import static org.powermock.api.mockito.PowerMockito.whenNew;

public class ExchangeRateProviderTest {

    @Test(expected = RuntimeException.class)
    public void exceptionHandling() throws Exception {
        whenNew(java.net.URL.class).withAnyArguments().thenReturn(null);
        new ExchangeRateProvider().getExchangeRate(null,null,null);
    }
}
