package com.onebeld.voyager.utils;

import com.onebeld.voyager.dto.trips.TripLocationDto;
import com.onebeld.voyager.dto.trips.TripShortInfoDto;
import com.onebeld.voyager.entities.Trip;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public TripLocationDto mapToLocationDto(Trip trip) {
        TripLocationDto dto = new TripLocationDto();
        dto.setId(trip.getId());
        dto.setLongitude(trip.getLongitude());
        dto.setLatitude(trip.getLatitude());
        return dto;
    }

    public TripShortInfoDto mapToShortInfoDto(Trip trip) {
        TripShortInfoDto dto = new TripShortInfoDto();
        dto.setId(trip.getId());
        dto.setName(trip.getName());
        return dto;
    }
}
