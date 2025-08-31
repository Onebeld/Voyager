package com.onebeld.voyager.repositories;

import com.onebeld.voyager.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Short> {
}
