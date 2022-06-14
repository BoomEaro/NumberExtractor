package ru.boomearo.numberextractor.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boomearo.numberextractor.parser.Country;
import ru.boomearo.numberextractor.parser.CountryParser;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
public class CountryCodeStorageService {

    private final ConcurrentMap<Integer, Country> countriesByCode;

    public CountryCodeStorageService(CountryParser parser) {
        this.countriesByCode = parser.parseCountry();

        log.info("Загружено " + this.countriesByCode.size() + " международных кодов.");
    }

    public String getCountryNameByCode(int code) {
        Country country = this.countriesByCode.get(code);
        if (country == null) {
            return "Unknown";
        }
        return country.getName();
    }

}
