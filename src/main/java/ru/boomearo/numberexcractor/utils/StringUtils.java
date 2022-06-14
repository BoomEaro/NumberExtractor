package ru.boomearo.numberexcractor.utils;

public class StringUtils {

    public static Integer parseIntegerSafe(String numberString) {
        if (numberString == null) {
            return null;
        }
        Integer number = null;
        try {
            number = Integer.parseInt(numberString);
        }
        catch (Exception ignored) {
        }

        return number;
    }

}
