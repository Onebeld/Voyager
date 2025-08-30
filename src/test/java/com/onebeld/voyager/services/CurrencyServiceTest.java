package com.onebeld.voyager.services;

import com.onebeld.voyager.entities.Currency;
import com.onebeld.voyager.repositories.CurrencyRepository;
import com.onebeld.voyager.responses.CurrencyApiResponse;
import com.onebeld.voyager.services.interfaces.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurrencyServiceTest {
    private CurrencyRepository currencyRepository;
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        currencyRepository = mock(CurrencyRepository.class);
        currencyService = new CurrencyServiceImpl(currencyRepository);
    }

    @Test
    void newCurrencyIsSaved() {
        CurrencyApiResponse response = getResponse("USD", 80.50);

        when(currencyRepository.getCurrencyByCode("USD")).thenReturn(Optional.empty());

        currencyService.updateCurrencies(response);

        ArgumentCaptor<Currency> captor = ArgumentCaptor.forClass(Currency.class);
        verify(currencyRepository).save(captor.capture());
        Currency saved = captor.getValue();

        assertEquals("USD", saved.getCode());
        assertEquals(BigDecimal.valueOf(80.50), saved.getRate());
    }

    @Test
    void existingCurrencyIsUpdated() {
        Currency existing = new Currency("USD",BigDecimal.valueOf(80.50));
        when(currencyRepository.getCurrencyByCode("USD")).thenReturn(Optional.of(existing));

        CurrencyApiResponse response = getResponse("USD", 80.50);

        currencyService.updateCurrencies(response);

        assertEquals(BigDecimal.valueOf(80.50), existing.getRate());
        verify(currencyRepository, never()).save(existing);
    }

    @Test
    void throwsExceptionWhenResponseEmpty() {
        CurrencyService serviceSpy = spy(currencyService);
        doReturn(Optional.empty()).when(serviceSpy).fetchCurrencyData();

        assertThrows(IllegalStateException.class, serviceSpy::update);
    }

    @Test
    void callsUpdateCurrencies() {
        CurrencyApiResponse response = getResponse("EUR", 100.50);

        CurrencyService serviceSpy = spy(currencyService);
        doReturn(Optional.of(response)).when(serviceSpy).fetchCurrencyData();

        serviceSpy.update();

        verify(serviceSpy).updateCurrencies(response);
    }

    @Test
    void convertUsdToEurByCode() {
        Currency usd = new Currency("USD", BigDecimal.valueOf(90.00));
        Currency eur = new Currency("EUR", BigDecimal.valueOf(100.00));

        when(currencyRepository.getCurrencyByCode("USD")).thenReturn(Optional.of(usd));
        when(currencyRepository.getCurrencyByCode("EUR")).thenReturn(Optional.of(eur));

        BigDecimal result = currencyService.convert("USD", "EUR", BigDecimal.ONE);

        assertEquals(BigDecimal.valueOf(0.9000).setScale(4, RoundingMode.HALF_EVEN), result);
    }

    @Test
    void convertSameCurrency() {
        Currency usd = new Currency("USD", BigDecimal.valueOf(90.00));

        when(currencyRepository.getCurrencyByCode("USD")).thenReturn(Optional.of(usd));

        BigDecimal result = currencyService.convert("USD", "USD", BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(50), result);
    }

    private CurrencyApiResponse getResponse(String code, double value) {
        CurrencyApiResponse response = new CurrencyApiResponse();
        CurrencyApiResponse.Currency currency =  new CurrencyApiResponse.Currency();

        currency.setValue(BigDecimal.valueOf(value));
        response.setCurrencies(Map.of(code, currency));

        return response;
    }
}
