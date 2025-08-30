package com.onebeld.voyager.services;

import com.onebeld.voyager.dto.trips.TripLocationDto;
import com.onebeld.voyager.dto.trips.TripShortInfoDto;
import com.onebeld.voyager.entities.Image;
import com.onebeld.voyager.entities.Trip;
import com.onebeld.voyager.repositories.ImageRepository;
import com.onebeld.voyager.repositories.TripRepository;
import com.onebeld.voyager.services.interfaces.CurrencyService;
import com.onebeld.voyager.services.interfaces.TripService;
import com.onebeld.voyager.utils.MappingUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final ImageRepository imageRepository;

    private final CurrencyService  currencyService;

    private final MappingUtils mappingUtils;

    public TripServiceImpl(TripRepository tripRepository, ImageRepository imageRepository, CurrencyService currencyService, MappingUtils mappingUtils) {
        this.tripRepository = tripRepository;
        this.imageRepository = imageRepository;
        this.currencyService = currencyService;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public Set<TripLocationDto> getTripsLocation() {
        return tripRepository.findAll().stream().map(mappingUtils::mapToLocationDto).collect(Collectors.toSet());
    }

    @Override
    public TripShortInfoDto getTripShortInfo(Long id, String currencyCode) {
        Trip trip = tripRepository.getReferenceById(id);
        TripShortInfoDto tripShortInfoDto = mappingUtils.mapToShortInfoDto(trip);

        BigDecimal price = currencyService.convert(trip.getCurrency().getCode(), currencyCode, trip.getPrice());

        tripShortInfoDto.setPrice(price);
        tripShortInfoDto.setCurrencyCode(currencyCode);

        Set<Image> images = imageRepository.findImagesByTrip(trip);
        tripShortInfoDto.setImageUrls(images.stream().map(Image::getUrl).collect(Collectors.toSet()));

        return tripShortInfoDto;
    }
}
