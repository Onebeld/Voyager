package com.onebeld.voyager.controllers;

import com.onebeld.voyager.configs.SecurityConfig;
import com.onebeld.voyager.controllers.api.TripController;
import com.onebeld.voyager.dto.trips.TripLocationDto;
import com.onebeld.voyager.dto.trips.TripShortInfoDto;
import com.onebeld.voyager.services.interfaces.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TripController.class)
@Import(SecurityConfig.class)
public class TripControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TripService tripService;

    @Test
    void getAllTripLocations_returnsLocations() throws Exception {
        Set<TripLocationDto> mockLocations = Set.of(
                new TripLocationDto(1L, 5.742F, -4.821F)
        );

        when(tripService.getTripsLocation()).thenReturn(mockLocations);

        mockMvc.perform(get("/api/trips/get-locations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].latitude").value(5.742F))
                .andExpect(jsonPath("$[0].longitude").value(-4.821F));
    }

    @Test
    void getTripShortInfo_returnsTrip() throws Exception {
        TripShortInfoDto mockTrip = new TripShortInfoDto(1L, "Paris", BigDecimal.TEN, "EUR", Set.of());

        when(tripService.getTripShortInfo(1L, "EUR")).thenReturn(mockTrip);

        mockMvc.perform(get("/api/trips/short/1?currencyCode=EUR"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Paris"))
                .andExpect(jsonPath("$.price").value(BigDecimal.TEN))
                .andExpect(jsonPath("$.currencyCode").value("EUR"));
    }
}
