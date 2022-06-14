package ru.boomearo.numberextractor.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.boomearo.numberextractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberextractor.dto.ExtractorCountryResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExtractorCountryServiceTest {

    @Mock
    private CountryCodeStorageService countryCodeStorageService;

    @InjectMocks
    private ExtractorCountryService countryService;

    @Test
    public void shouldReturnCorrectCountryName() {
        when(countryCodeStorageService.getCountryNameByCode(371)).thenReturn("Latvia");
        ExtractorCountryResponse response =
                this.countryService.extractCountry(new ExtractorCountryRequest("37112345678"));

        verify(countryCodeStorageService).getCountryNameByCode(371);

        ExtractorCountryResponse expected = new ExtractorCountryResponse("Latvia", "37112345678");

        assertEquals(response, expected);
    }
}
