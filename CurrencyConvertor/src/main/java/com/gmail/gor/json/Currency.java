package com.gmail.gor.json;

import java.util.SortedMap;

public class Currency {
    private SortedMap<String, Double> rates;

    public SortedMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(SortedMap<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "rates=" + rates +
                '}';
    }
}
