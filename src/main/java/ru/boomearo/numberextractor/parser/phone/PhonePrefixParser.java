package ru.boomearo.numberextractor.parser.phone;

public interface PhonePrefixParser {

    int parsePhonePrefix(String phoneNumber) throws Exception;
}
