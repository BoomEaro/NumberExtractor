package ru.boomearo.numberexcractor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.boomearo.numberexcractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberexcractor.dto.ExtractorCountryResponse;

@Service
@AllArgsConstructor
public class CountryExtractorService {

    public ExtractorCountryResponse extractorCountry(ExtractorCountryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Запрос не должен быть нулевым!");
        }

        //TODO Тут что то делается и возвращается запрос.

        return new ExtractorCountryResponse("TEST_STAB", request.getPhoneNumber());
    }

}
