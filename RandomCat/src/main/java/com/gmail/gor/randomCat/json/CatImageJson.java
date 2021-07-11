package com.gmail.gor.randomCat.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CatImageJson {
    public String getCatJson() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri((
                        URI.create("https://aws.random.cat/meow")
                ))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public CatImageApi getCatImage() throws IOException, InterruptedException {
        CatImageApi catImage = null;
        String json = getCatJson();
        Gson gson = new GsonBuilder().create();
        try {
            catImage = gson.fromJson(json, CatImageApi.class);

        } catch (IllegalStateException | JsonSyntaxException exception) {
            exception.printStackTrace();
        }
        return catImage;
    }
}
