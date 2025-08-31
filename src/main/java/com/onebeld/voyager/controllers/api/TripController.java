package com.onebeld.voyager.controllers.api;

import com.onebeld.voyager.dto.trips.TripLocationDto;
import com.onebeld.voyager.dto.trips.TripShortInfoDto;
import com.onebeld.voyager.services.interfaces.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/trips")
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
    ResponseEntity<TripShortInfoDto> getTripShortInfo(@PathVariable Long id, @RequestParam(defaultValue = "USD") String currencyCode) {
        TripShortInfoDto trip = tripService.getTripShortInfo(id, currencyCode);
        return ResponseEntity.ok(trip);
    }


}
