package com.example.hw_currency_mvc_10;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
//@Table (name = "currency2")
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @SerializedName("txt")
    private String name;
    @SerializedName("cc")
    private String currencyCode;
    @SerializedName("exchangeDate")
    private LocalDate exchangeDate;
    @SerializedName("rate")
    private double rate;

    public CurrencyEntity() {
    }

    public CurrencyEntity(String currencyCode, LocalDate exchangeDate, double rate) {
        this.currencyCode = currencyCode;
        this.exchangeDate = exchangeDate;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDate getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(LocalDate exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CurrencyEntity{" +
                "id=" + id +
                ", currencyCode='" + currencyCode + '\'' +
                ", exchangeDate=" + exchangeDate +
                ", rate=" + rate +
                '}';
    }
}
