package ru.boomearo.numberextractor.controllets.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class RestExtractorCountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnCorrectJsonResponseLatvia() throws Exception {
        mockMvc.perform(get("/api/country?phone=37112345678").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value((Object) null))
                .andExpect(jsonPath("$.data.countryName").value("Latvia"))
                .andExpect(jsonPath("$.data.phoneNumber").value("37112345678"))
                .andExpect(jsonPath("$.data.countryCode").value(371));
    }

    @Test
    public void shouldReturnCorrectJsonResponseRussia() throws Exception {
        mockMvc.perform(get("/api/country?phone=77112345678").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").value((Object) null))
                .andExpect(jsonPath("$.data.countryName").value("Russia"))
                .andExpect(jsonPath("$.data.phoneNumber").value("77112345678"))
                .andExpect(jsonPath("$.data.countryCode").value(7));
    }

    @Test
    public void shouldReturnErrorIfWrongPhoneName() throws Exception {
        mockMvc.perform(get("/api/country?phone=WrongPhone").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors[0].error").value("Error type: NOT_A_NUMBER. The string supplied did not seem to be a phone number."))
                .andExpect(jsonPath("$.data").value((Object) null));
    }
}
