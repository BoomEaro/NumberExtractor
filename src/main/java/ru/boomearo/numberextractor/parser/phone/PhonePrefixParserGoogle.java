package ru.boomearo.numberextractor.parser.phone;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Component;

@Component
public class PhonePrefixParserGoogle implements PhonePrefixParser {

    @Override
    public int parsePhonePrefix(String phoneNumber) {
        String phoneNumberToParse = "+" + phoneNumber;

        Phonenumber.PhoneNumber number;
        try {
            //С помощью магии, библиотека умеет определять код страны и еще валидировать номер.
            number = PhoneNumberUtil.getInstance().parse(phoneNumberToParse,
                    Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
        }
        catch (NumberParseException e) {
            throw new RuntimeException(e);
        }
        return number.getCountryCode();
    }

}
