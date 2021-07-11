package com.gmail.gor.servise;

import com.gmail.gor.json.Currency;
import com.gmail.gor.json.CurrencyJson;

public class Convertor {
    private String currency;
    private String currencyValue;
    Currency currencyFromJson = new CurrencyJson().getCurrency();

    public Convertor(String currency, String currencyValue) {
        this.currency = currency;
        this.currencyValue = currencyValue;
    }

    public Convertor() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
    }

    public Double convert() {
        Double result = null;
        Double value;
        Double cur;
        try {
            value = Double.valueOf(currencyValue);
            cur = Double.valueOf(currency);
        } catch (NumberFormatException e) {
            return result;
        }

        result = value * cur;

        return result;
    }


    @Override
    public String toString() {
        return "Convertor{" +
                "currency='" + currency + '\'' +
                ", currencyValue='" + currencyValue + '\'' +
                ", currencyFromJson=" + currencyFromJson +
                '}';
    }
}
