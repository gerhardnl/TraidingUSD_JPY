package com.company;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    public static final String ACCESS_KEY = "";
    public static final String BASE_URL = "https://financialmodelingprep.com/api/v3/forex";
    public static final String ENDPOINT = "/EURUSD";

    private static Scanner s = new Scanner(System.in);
    private static double moveingAvrege = 1.11;


    public static void main(String[] args) {


        OpenPositons Oil = new OpenPositons();
        while (true) {


          HttpGet get = new HttpGet(BASE_URL + ENDPOINT + ACCESS_KEY);

            try {
                CloseableHttpResponse response = httpClient.execute(get);
                HttpEntity entity = response.getEntity();

                JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

                Oil.setCurrentPrice(exchangeRates.getDouble("ask"));

                response.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


            if (moveingAvrege > (Oil.getCurrentPrice() * Oil.getProsentigeTracker())) {
                Oil.buyPosition();
            }
            if (moveingAvrege < (Oil.getCurrentPrice() / Oil.getProsentigeTracker())) {
                Oil.sellPositon();
            }
            Oil.stocks.forEach(stock -> System.out.println("was bough at " + stock.getOpenPrice() + " and will be closed at " + stock.getClosePositionPrice() + " buy = " + stock.isBuy() + " your funds are " + Oil.getFunds()));

            for (int i = 0; i < Oil.stocks.size(); i++) {
                if (Oil.stocks.get(i).isBuy() == true) {
                    if (Oil.getCurrentPrice() >= Oil.stocks.get(i).getClosePositionPrice()) {
                        Oil.closeBuyPosition();
                    }
                } else if (Oil.stocks.get(i).isBuy() == false) {
                    if (Oil.getCurrentPrice() <= Oil.stocks.get(i).getClosePositionPrice()) {
                        Oil.closeSellPosition();
                    }
                }
            }
            try {
                Thread.sleep(10000);
           } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }
}

// 94d2a09e5804e20355997a85ae24611e


