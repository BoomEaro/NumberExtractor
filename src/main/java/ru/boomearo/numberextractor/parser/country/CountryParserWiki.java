package ru.boomearo.numberextractor.parser.country;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.boomearo.numberextractor.utils.StringParserUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class CountryParserWiki implements CountryParser {

    private static final String PARSE_URL = "https://en.wikipedia.org/wiki/List_of_country_calling_codes";

    @Override
    public ConcurrentMap<Integer, Country> parseCountry() throws Exception {
        ConcurrentMap<Integer, Country> countriesTmp = new ConcurrentHashMap<>();

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

            Element elementCountry = e.get(0);

            String country = elementCountry.text();

            //На википедии в таблице под одной страной может быть намного больше кодов.
            String[] splitPrefixes = prefixString.split(",");
            for (String prefix : splitPrefixes) {
                Integer prefixNum = StringParserUtils.parseIntegerSafe(prefix);
                if (prefixNum == null) {
                    continue;
                }

                countriesTmp.put(prefixNum, new Country(prefixNum, country));
            }
        }
        return countriesTmp;
    }

}
