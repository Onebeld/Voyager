package com.onebeld.voyager.initializers;

import com.onebeld.voyager.entities.Position;
import com.onebeld.voyager.repositories.PositionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabasePositionInitializer implements CommandLineRunner {
    private final PositionRepository positionRepository;

    public DatabasePositionInitializer(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializePositions();
    }

    private void initializePositions() {
        if (positionRepository.count() != 0)
            return;

        List<Position> positions = List.of(
                new Position("CEO"),
                new Position("DEPUTY"),
                new Position("MANAGER"),
                new Position("MODERATOR")
        );

        positionRepository.saveAll(positions);
    }
}
