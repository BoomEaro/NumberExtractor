package ru.boomearo.numberextractor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtractorCountryRequest {

    private final String phoneNumber;

}
