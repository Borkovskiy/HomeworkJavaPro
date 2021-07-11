package com.gmail.gor.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyJson {
    private Currency currency;

    public CurrencyJson() {

    }

    public Currency getCurrency() {

        conv();
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String json() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://data.fixer.io/api/latest?access_key=803b19859df142ff2eb035739714bc4d"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }

    public void conv() {
        String json = null;
        try {
            json = json();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().create();
        currency = gson.fromJson(json, Currency.class);

    }
}
