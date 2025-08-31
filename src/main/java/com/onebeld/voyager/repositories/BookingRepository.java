package com.onebeld.voyager.repositories;

import com.onebeld.voyager.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
