package ru.boomearo.numberexcractor.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.boomearo.numberexcractor.utils.StringParserUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
public class PhonePrefixStorageService {

    private final ConcurrentMap<Integer, Country> countriesByPrefix;

    private static final String PARSE_URL = "https://en.wikipedia.org/wiki/List_of_country_calling_codes";

    public PhonePrefixStorageService() {
        this.countriesByPrefix = parsePrefixes();

        log.info("Загружено  " + this.countriesByPrefix.size() + " международных кодов.");
    }

    public String getCountryNameByPrefix(int prefix) {
        log.info("Пришел код " + prefix);
        Country country = this.countriesByPrefix.get(prefix);
        if (country == null) {
            return "Unknown";
        }
        return country.getName();
    }

    private static ConcurrentMap<Integer, Country> parsePrefixes() {
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
