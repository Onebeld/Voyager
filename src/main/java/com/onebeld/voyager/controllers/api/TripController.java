package com.onebeld.voyager.controllers.api;

import com.onebeld.voyager.dto.trips.TripLocationDto;
import com.onebeld.voyager.dto.trips.TripShortInfoDto;
import com.onebeld.voyager.services.interfaces.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller("/api/trips")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/get-locations")
    ResponseEntity<Set<TripLocationDto>> getAllTripLocations() {
        Set<TripLocationDto> locations = tripService.getTripsLocation();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/short/{id}")
    ResponseEntity<TripShortInfoDto> getTripShortInfo(@PathVariable Long id, @RequestParam("USD") String currencyCode) {
        TripShortInfoDto trip = tripService.getTripShortInfo(id, currencyCode);
        return ResponseEntity.ok(trip);
    }
}
