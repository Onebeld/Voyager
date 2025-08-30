package com.onebeld.voyager.services.interfaces;

import com.onebeld.voyager.entities.Currency;
import com.onebeld.voyager.responses.CurrencyApiResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    Optional<CurrencyApiResponse> fetchCurrencyData();

    void updateCurrencies(CurrencyApiResponse response);

    void update();

    BigDecimal convert(String fromCode, String toCode, BigDecimal amount);

    BigDecimal convert(com.onebeld.voyager.entities.Currency from, Currency to, BigDecimal amount);
}
