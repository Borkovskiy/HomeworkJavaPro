package com.gmail.gor.randomCat.json;


public class CatImageApi {
    private String file;

    public CatImageApi() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "CatImage{" +
                "file='" + file + '\'' +
                '}';
    }
}
