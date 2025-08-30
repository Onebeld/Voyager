package com.onebeld.voyager.repositories;

import com.onebeld.voyager.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Short> {
    boolean existsByNameWithinIgnoreCase(String name);

    Optional<Currency> getCurrencyByCode(String code);
}
