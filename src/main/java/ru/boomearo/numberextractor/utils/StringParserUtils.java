package ru.boomearo.numberextractor.utils;

public class StringParserUtils {

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
