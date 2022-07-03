package ru.boomearo.numberextractor.parser.country;

import java.util.concurrent.ConcurrentMap;

public interface CountryParser {

    ConcurrentMap<Integer, Country> parseCountry() throws Exception;

}
