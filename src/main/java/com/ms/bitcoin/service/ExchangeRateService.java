package com.ms.bitcoin.service;

import com.ms.bitcoin.provider.BlockChainExchangeRateProvider;
import com.ms.bitcoin.provider.ExmoExchangeRateProvider;

public class ExchangeRateService {

    private BlockChainExchangeRateProvider blockChainExchangeRateProvider = new BlockChainExchangeRateProvider();
    private ExmoExchangeRateProvider exmoExchangeRateProvider = new ExmoExchangeRateProvider();

    public void compareExchangeRates() {
        Double exmoSell = exmoExchangeRateProvider.getSellPrice();
        Double blockChainSell = blockChainExchangeRateProvider.getSellPrice();

        int comparison = exmoSell.compareTo(blockChainSell);

        if(comparison > 0) {
            System.out.println("exmo.com: "+exmoSell);
        }

        if (comparison < 0) {
            System.out.println("blockchain: "+blockChainSell);
        }

        if(comparison == 0 ) {
            System.out.println("exmo.com & blockchain: "+exmoSell);
        }
    }

    public static void main(String[] args) {
        try {
            new ExchangeRateService().compareExchangeRates();
        } catch(Exception e) {
            System.out.println("Exception comparing rates: "+e.toString());
            e.printStackTrace();
        }
    }
}
