package ru.boomearo.numberextractor.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boomearo.numberextractor.dto.ErrorData;
import ru.boomearo.numberextractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberextractor.dto.ExtractorCountryResponse;
import ru.boomearo.numberextractor.parser.phone.PhonePrefixParser;

@Slf4j
@Service
@AllArgsConstructor
public class ExtractorCountryService {

    private final CountryCodeStorageService phonePrefixStorageService;
    private final PhonePrefixParser phoneParser;

    public ExtractorCountryResponse extractCountry(ExtractorCountryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Запрос не должен быть нулевым!");
        }

        String phoneNumber = request.getPhoneNumber();
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return new ExtractorCountryResponse(new ErrorData("Номер телефона не может быть пустым или нулевым!"));
        }

        int prefix;
        try {
            prefix = this.phoneParser.parsePhonePrefix(phoneNumber);
        }
        catch (Exception e) {
            return new ExtractorCountryResponse(new ErrorData(e.getMessage()));
        }

        String country = this.phonePrefixStorageService.getCountryNameByCode(prefix);

        return new ExtractorCountryResponse(new ExtractorCountryResponse.CountryData(country, phoneNumber, prefix));
    }


}
