package ru.boomearo.numberextractor.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.boomearo.numberextractor.dto.ExtractorCountryRequest;
import ru.boomearo.numberextractor.dto.ExtractorCountryResponse;
import ru.boomearo.numberextractor.parser.phone.PhonePrefixParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExtractorCountryServiceTest {

    @Mock
    private CountryCodeStorageService countryCodeStorageService;

    @Mock
    private PhonePrefixParser phonePrefixParser;

    @InjectMocks
    private ExtractorCountryService countryService;

    @Test
    public void shouldReturnCorrectCountryName() {
        when(countryCodeStorageService.getCountryNameByCode(371)).thenReturn("Latvia");
        when(phonePrefixParser.parsePhonePrefix("37112345678")).thenReturn(371);

        ExtractorCountryResponse response =
                this.countryService.extractCountry(new ExtractorCountryRequest("37112345678"));

        verify(countryCodeStorageService).getCountryNameByCode(371);
        verify(phonePrefixParser).parsePhonePrefix("37112345678");

        ExtractorCountryResponse expected = new ExtractorCountryResponse(new ExtractorCountryResponse.CountryData("Latvia", "37112345678", 371));

        assertEquals(expected, response);
    }
}
