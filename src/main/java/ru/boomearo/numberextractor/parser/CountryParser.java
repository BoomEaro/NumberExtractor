package ru.boomearo.numberextractor.parser;

import java.util.concurrent.ConcurrentMap;

public interface CountryParser {

    ConcurrentMap<Integer, Country> parseCountry();

}
