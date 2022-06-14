package ru.boomearo.numberextractor.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.boomearo.numberextractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberextractor.dto.ExtractorCountryResponse;
import ru.boomearo.numberextractor.services.ExtractorCountryService;

import java.util.Map;

@RequestMapping("/api/country")
@RestController()
@AllArgsConstructor
public class RestExtractorCountryController {

    private final ExtractorCountryService service;

    @GetMapping
    public ExtractorCountryResponse extractCountryByNumber(@RequestParam Map<String, String> params) {
        String phoneNumber = params.get("phone");
        if (phoneNumber == null) {
            return null;
        }
        return this.service.extractCountry(new ExtractorCountryRequest(phoneNumber));
    }

}
