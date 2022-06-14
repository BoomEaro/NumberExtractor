package ru.boomearo.numberexcractor.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class PhonePrefixStorageService {

    private ConcurrentMap<Integer, Country> countriesByPrefix = new ConcurrentHashMap<>();

    public String getCountryNameByPrefix(int prefix) {
        Country country = this.countriesByPrefix.get(prefix);
        if (country == null) {
            return "Unknown";
        }
        return country.getName();
    }

    @Data
    @AllArgsConstructor
    public static class Country {

        private final int prefix;
        private final String name;

    }
}
