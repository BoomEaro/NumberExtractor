package ru.boomearo.numberextractor.services;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.boomearo.numberextractor.dto.ErrorData;
import ru.boomearo.numberextractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberextractor.dto.ExtractorCountryResponse;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ExtractorCountryService {

    private final CountryCodeStorageService phonePrefixStorageService;

    public ExtractorCountryResponse extractCountry(ExtractorCountryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Запрос не должен быть нулевым!");
        }

        String phoneNumberToParse = "+" + request.getPhoneNumber();

        //С помощью магии, библиотека умеет определять код страны и еще валидировать номер.
        int prefix;
        try {
            Phonenumber.PhoneNumber number = PhoneNumberUtil.getInstance().parse(phoneNumberToParse,
                    Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());

            prefix = number.getCountryCode();
        }
        catch (NumberParseException e) {
            return new ExtractorCountryResponse(List.of(new ErrorData(e.getMessage())));
        }

        String country = this.phonePrefixStorageService.getCountryNameByCode(prefix);

        return new ExtractorCountryResponse(country, request.getPhoneNumber());
    }


}
