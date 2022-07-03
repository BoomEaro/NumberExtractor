package ru.boomearo.numberextractor.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boomearo.numberextractor.parser.country.Country;
import ru.boomearo.numberextractor.parser.country.CountryParser;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
public class CountryCodeStorageService {

    private ConcurrentMap<Integer, Country> countriesByCode = new ConcurrentHashMap<>();

    public CountryCodeStorageService(CountryParser parser) {
        try {
            this.countriesByCode = parser.parseCountry();
            log.info("Загружено " + this.countriesByCode.size() + " международных кодов.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCountryNameByCode(int code) {
        Country country = this.countriesByCode.get(code);
        if (country == null) {
            return "Unknown";
        }
        return country.getName();
    }

}
