package com.ms.bitcoin.provider;

import org.junit.Test;

import static com.ms.bitcoin.provider.ExmoExchangeRateProvider.RATE_KEY;
import static com.ms.bitcoin.provider.ExmoExchangeRateProvider.SELL_PRICE_KEY;
import static com.ms.bitcoin.provider.ExmoExchangeRateProvider.URL;
import static org.junit.Assert.assertTrue;

public class ExchangeRateProviderIntegrationTest {

    @Test
    public void retrieveRate() throws Exception {
        Double exchangeRate = new ExchangeRateProvider().getExchangeRate(URL,RATE_KEY,SELL_PRICE_KEY);
        assertTrue(exchangeRate > 0);
    }
}
