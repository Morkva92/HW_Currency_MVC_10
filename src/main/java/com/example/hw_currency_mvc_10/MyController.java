package com.example.hw_currency_mvc_10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/currency")
    public String showData(Model model) {
        currencyService.updateCurrencyData();
        Iterable<CurrencyEntity> currencies = currencyService.getAllCurrencies();
        model.addAttribute("currencies", currencies);

        return "showData";
    }



}
