package ru.boomearo.numberexcractor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExtractorCountryResponse extends Response {

    private String countryName;
    private String phoneNumber;

    public ExtractorCountryResponse(List<ErrorData> errors) {
        super(errors);
    }

}
