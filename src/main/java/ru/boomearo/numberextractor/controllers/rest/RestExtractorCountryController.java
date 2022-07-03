package ru.boomearo.numberextractor.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.boomearo.numberextractor.dto.ErrorData;
import ru.boomearo.numberextractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberextractor.dto.ExtractorCountryResponse;
import ru.boomearo.numberextractor.services.ExtractorCountryService;

@RequestMapping("/api/country")
@RestController()
@AllArgsConstructor
public class RestExtractorCountryController {

    private final ExtractorCountryService service;

    @GetMapping
    public ExtractorCountryResponse extractCountryByNumber(@RequestParam(name = "phone") String phoneNumber) {
        return this.service.extractCountry(new ExtractorCountryRequest(phoneNumber));
    }

    @ExceptionHandler(RuntimeException.class)
    public ExtractorCountryResponse handleException(RuntimeException e) {
        return new ExtractorCountryResponse(new ErrorData(e.getMessage()));
    }

}
