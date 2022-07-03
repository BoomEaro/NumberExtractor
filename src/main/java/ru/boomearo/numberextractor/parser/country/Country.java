package ru.boomearo.numberextractor.parser.country;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Country {

    private final int prefix;
    private final String name;

}
