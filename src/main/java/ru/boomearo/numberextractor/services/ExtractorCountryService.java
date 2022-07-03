package ru.boomearo.numberextractor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.boomearo.numberextractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberextractor.dto.ExtractorCountryResponse;
import ru.boomearo.numberextractor.parser.phone.PhonePrefixParser;

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
            throw new IllegalArgumentException("Номер телефона не может быть пустым или нулевым!");
        }

        int prefix = this.phoneParser.parsePhonePrefix(phoneNumber);

        String country = this.phonePrefixStorageService.getCountryNameByCode(prefix);

        return new ExtractorCountryResponse(new ExtractorCountryResponse.CountryData(country, phoneNumber, prefix));
    }
}
