package com.ms.bitcoin.provider;

public class BlockChainExchangeRateProvider {

    protected static final String URL = "https://blockchain.info/ticker";
    protected static final String RATE_KEY = "USD";
    protected static final String SELL_PRICE_KEY = "sell";

    public Double getSellPrice() {
        ExchangeRateProvider exchangeRateProvider = new ExchangeRateProvider();
        return exchangeRateProvider.getExchangeRate(URL, RATE_KEY, SELL_PRICE_KEY);
    }
}
