package com.example.hw_currency_mvc_10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {

    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

      private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    @Autowired
    private CurrencyEntityRepository currencyEntityRepository;

    @Transactional
    public String getJsonNBU(String code) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(URL + "&valcode=" + code.toLowerCase());

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return EntityUtils.toString(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("No json response");
        return null;
    }

    @Transactional
    public CurrencyEntity[] getCurrencyNBU(String code) {
        String json = getJsonNBU(code);
        return GSON.fromJson(json, CurrencyEntity[].class);
    }

    @Transactional
    public void saveCurrencyEntities(String code) {
        CurrencyEntity[] currencies = getCurrencyNBU(code);

        if (currencies != null && currencies.length > 0) {
            currencyEntityRepository.saveAll(Arrays.asList(currencies));
        }
    }

    @Transactional
    public void updateCurrencyData() {
        List<String> allCurrencyCodes = Arrays.asList("USD", "EUR", "GBP", "JPY");
        for (String currencyCode : allCurrencyCodes) {
            saveCurrencyEntities(currencyCode);
        }
    }

    @Transactional(readOnly = true)
    public Iterable<CurrencyEntity> getAllCurrencies() {
        return currencyEntityRepository.findAll();

    }


}
