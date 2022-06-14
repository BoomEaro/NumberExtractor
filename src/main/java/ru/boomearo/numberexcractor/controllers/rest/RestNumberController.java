package ru.boomearo.numberexcractor.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.boomearo.numberexcractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberexcractor.dto.ExtractorCountryResponse;
import ru.boomearo.numberexcractor.services.CountryExtractorService;

import java.util.Map;

@RequestMapping("/api/country")
@RestController()
@AllArgsConstructor
public class RestNumberController {

    private final CountryExtractorService service;

    @GetMapping
    public ExtractorCountryResponse extractCountryByNumber(@RequestParam Map<String, String> params) {
        String phoneNumber = params.get("phone");
        if (phoneNumber == null) {
            return null;
        }
        return service.extractorCountry(new ExtractorCountryRequest(phoneNumber));
    }

}
