package ru.boomearo.numberexcractor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.boomearo.numberexcractor.dto.ErrorData;
import ru.boomearo.numberexcractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberexcractor.dto.ExtractorCountryResponse;
import ru.boomearo.numberexcractor.utils.StringParserUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryExtractorService {

    private final PhoneValidationService phoneValidationService;
    private final PhonePrefixStorageService phonePrefixStorageService;

    public ExtractorCountryResponse extractorCountry(ExtractorCountryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Запрос не должен быть нулевым!");
        }

        String phoneNumber = request.getPhoneNumber();
        String validation = this.phoneValidationService.validateNumber(phoneNumber);
        if (validation != null) {
            return new ExtractorCountryResponse(List.of(new ErrorData(validation)));
        }

        //8 - длина номера абонента. Цифры перед - международный номер
        String phonePrefix = phoneNumber.substring(0, phoneNumber.length() - 8);

        String country = this.phonePrefixStorageService.getCountryNameByPrefix(StringParserUtils.parseIntegerSafe(phonePrefix));

        return new ExtractorCountryResponse(country, request.getPhoneNumber());
    }


}
