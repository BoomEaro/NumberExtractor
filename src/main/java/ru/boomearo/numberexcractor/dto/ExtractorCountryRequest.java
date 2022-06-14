package ru.boomearo.numberexcractor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtractorCountryRequest {

    private final String phoneNumber;

}
