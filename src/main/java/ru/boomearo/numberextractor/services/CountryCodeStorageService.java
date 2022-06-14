package ru.boomearo.numberextractor.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.boomearo.numberextractor.utils.StringParserUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
public class CountryCodeStorageService {

    private final ConcurrentMap<Integer, Country> countriesByCode;

    private static final String PARSE_URL = "https://en.wikipedia.org/wiki/List_of_country_calling_codes";

    public CountryCodeStorageService() {
        this.countriesByCode = parseCodes();

        log.info("Загружено " + this.countriesByCode.size() + " международных кодов.");
    }

    public String getCountryNameByCode(int code) {
        log.info("Пришел код " + code);
        Country country = this.countriesByCode.get(code);
        if (country == null) {
            return "Unknown";
        }
        return country.getName();
    }

    private static ConcurrentMap<Integer, Country> parseCodes() {
        ConcurrentMap<Integer, Country> countriesTmp = new ConcurrentHashMap<>();
        try {
            Document doc = Jsoup.connect(PARSE_URL).get();
            Elements tables = doc.select("table.wikitable");
            Element rows = tables.get(1);

            Elements data = rows.select("tr");
            for (Element r : data) {
                Elements e = r.select("td");
                if (e.size() < 2) {
                    continue;
                }
                Element elementPrefix = e.get(1);

                //Убираем лишние символы при попытке извлечь код страны
                String prefixString = elementPrefix.text()
                        .replace("+", "")
                        .replace("[notes 1]", "")
                        .replace("was ", "")
                        .replace("assigned ", "")
                        .replace(" ", "");

                //На википедии в таблице под одной страной может быть намного больше кодов.
                String[] splitPrefixes = prefixString.split(",");
                for (String prefix : splitPrefixes) {
                    Integer prefixNum = StringParserUtils.parseIntegerSafe(prefix);
                    if (prefixNum == null) {
                        continue;
                    }

                    Element elementCountry = e.get(0);

                    String country = elementCountry.text();

                    countriesTmp.put(prefixNum, new Country(prefixNum, country));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return countriesTmp;
    }

    @Data
    @AllArgsConstructor
    public static class Country {

        private final int prefix;
        private final String name;

    }
}
