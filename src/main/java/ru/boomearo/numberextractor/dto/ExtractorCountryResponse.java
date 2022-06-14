package ru.boomearo.numberextractor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExtractorCountryResponse extends Response {

    private CountryData data;

    public ExtractorCountryResponse(ErrorData error) {
        this(List.of(error));
    }

    public ExtractorCountryResponse(List<ErrorData> errors) {
        super(errors);
    }

    @Data
    @AllArgsConstructor
    public static class CountryData {

        private String countryName;
        private String phoneNumber;
        private Integer countryCode;

    }

}
