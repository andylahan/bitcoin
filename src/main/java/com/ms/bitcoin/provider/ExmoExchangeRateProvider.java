package com.ms.bitcoin.provider;

public class ExmoExchangeRateProvider {

    public static final String URL = "https://api.exmo.com/v1/ticker/";
    public static final String RATE_KEY = "BTC_USD";
    public static final String SELL_PRICE_KEY = "sell_price";

    public Double getSellPrice() {
        ExchangeRateProvider exchangeRateProvider = new ExchangeRateProvider();
        return exchangeRateProvider.getExchangeRate(URL, RATE_KEY, SELL_PRICE_KEY);
    }
}
