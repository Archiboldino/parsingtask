package controller;

import entity.Currency;
import service.JSONService;
import util.json.JSONParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * JSONController
 * created on 6/10/18
 *
 * @author Nikita Zabaykin vladlihovid@gmail.com
 * @version 1.0
 */
public class JSONController {
    private static final String CURRENCIES_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final String FILE_NAME = "file.json";

    private JSONService service;

    public JSONController(JSONParser parser) {
        this.service = new JSONService(parser);
    }

    public void processJSON() {
        List<Currency> currencies = null;
        try {
            currencies = service.getCurrencies(new URL(CURRENCIES_URL).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            service.writeCurrencies(currencies, FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(service.getCurrencies(new FileInputStream(FILE_NAME)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
