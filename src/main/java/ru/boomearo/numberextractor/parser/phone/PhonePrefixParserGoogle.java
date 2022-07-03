package ru.boomearo.numberextractor.parser.phone;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Component;

@Component
public class PhonePrefixParserGoogle implements PhonePrefixParser {

    @Override
    public int parsePhonePrefix(String phoneNumber) throws Exception {
        String phoneNumberToParse = "+" + phoneNumber;

        //С помощью магии, библиотека умеет определять код страны и еще валидировать номер.
        Phonenumber.PhoneNumber number = PhoneNumberUtil.getInstance().parse(phoneNumberToParse,
                Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
        return number.getCountryCode();
    }

}
