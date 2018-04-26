package com.ms.bitcoin.provider;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateProvider {

    public Double getExchangeRate(String urlString, String rateKey, String sellPriceKey) {
        try {
            URL url = new URL(urlString);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootobj = root.getAsJsonObject();
            Double price = rootobj.getAsJsonObject(rateKey).getAsJsonPrimitive(sellPriceKey).getAsDouble();

            return price;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
