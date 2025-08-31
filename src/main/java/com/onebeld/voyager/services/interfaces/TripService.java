package com.onebeld.voyager.services.interfaces;

import com.onebeld.voyager.dto.trips.TripLocationDto;
import com.onebeld.voyager.dto.trips.TripShortInfoDto;

import java.util.Set;

public interface TripService {
    Set<TripLocationDto> getTripsLocation();

    TripShortInfoDto  getTripShortInfo(Long id, String currencyCode);
}
