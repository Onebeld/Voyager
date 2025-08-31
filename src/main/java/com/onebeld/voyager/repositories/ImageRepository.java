package com.onebeld.voyager.repositories;

import com.onebeld.voyager.entities.Image;
import com.onebeld.voyager.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Set<Image> findImagesByTrip(Trip trip);
}
