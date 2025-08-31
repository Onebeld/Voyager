package com.onebeld.voyager.repositories;

import com.onebeld.voyager.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
