package com.onebeld.voyager.services;

import com.onebeld.voyager.entities.Currency;
import com.onebeld.voyager.repositories.CurrencyRepository;
import com.onebeld.voyager.responses.CurrencyApiResponse;
import com.onebeld.voyager.services.interfaces.CurrencyService;
import com.onebeld.voyager.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
    private final RestTemplate restTemplate = Utils.restTemplateCb();

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Optional<CurrencyApiResponse> fetchCurrencyData() {
        String apiUrl = "https://www.cbr-xml-daily.ru/daily_json.js";

        try {
            return Optional.ofNullable(restTemplate.getForObject(apiUrl, CurrencyApiResponse.class));
        } catch (RestClientException e) {
            log.error("Error while trying to connect to API URL {}", apiUrl, e);
            return Optional.empty();
        }
    }

    public void updateCurrencies(CurrencyApiResponse response) {
        for (Map.Entry<String, CurrencyApiResponse.Currency> entry : response.getCurrencies().entrySet()) {
            Optional<Currency> currency = currencyRepository.getCurrencyByCode(entry.getKey());
            BigDecimal rate = entry.getValue().getValue();

            if (currency.isEmpty())
                currencyRepository.save(new Currency(entry.getKey(), rate));
            else if (!currency.get().getRate().equals(rate)) {
                Currency existing = currency.get();
                existing.setRate(rate);
            }
        }
    }

    public void update() {
        Optional<CurrencyApiResponse> response = fetchCurrencyData();

        if (response.isEmpty())
            throw new IllegalStateException("Currency data has not been fetched");

        updateCurrencies(response.get());
    }

    public BigDecimal convert(String fromCode, String toCode, BigDecimal amount) {
        Currency from = currencyRepository.getCurrencyByCode(fromCode).orElseThrow(() -> new IllegalArgumentException("Currency not found: " + fromCode));
        Currency to = currencyRepository.getCurrencyByCode(toCode).orElseThrow(() -> new IllegalArgumentException("Currency not found: " + toCode));

        return convert(from, to, amount);
    }

    public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        if (from.equals(to))
            return amount;

        BigDecimal fromRate = from.getRate();
        BigDecimal toRate = to.getRate();

        return amount.multiply(fromRate).divide(toRate, 4, RoundingMode.HALF_EVEN);
    }
}
