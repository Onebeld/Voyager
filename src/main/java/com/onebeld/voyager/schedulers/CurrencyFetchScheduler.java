package com.onebeld.voyager.schedulers;

import com.onebeld.voyager.services.interfaces.CurrencyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CurrencyFetchScheduler {
    private final CurrencyService currencyService;

    public CurrencyFetchScheduler(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Scheduled(cron = "0 0 0 * * ?", zone = "PST")
    void scheduleCurrenciesUpdate() {
        currencyService.update();
    }
}
