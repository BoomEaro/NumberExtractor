package ru.boomearo.numberexcractor.services;

import org.springframework.stereotype.Service;

@Service
public class PhoneValidationService {

    public String validateNumber(String number) {
        if (number == null) {
            return "Номер телефона не должен быть нулевым!";
        }
        if (number.isEmpty()) {
            return "Номер телефона не должен быть пустым!";
        }

        int length = number.length();
        if (length < 9) {
            return "Номер телефона слишком короткий!";
        }
        if (length > 11) {
            return "Номер телефона слишком длинный!";
        }

        return null;
    }

}
