package com.onebeld.voyager.initializers;

import com.onebeld.voyager.repositories.CurrencyRepository;
import com.onebeld.voyager.services.interfaces.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCurrencyInitializer implements CommandLineRunner {
    private final CurrencyRepository currencyRepository;

    private final CurrencyService currencyService;

    public DatabaseCurrencyInitializer(CurrencyService currencyService, CurrencyRepository currencyRepository) {
        this.currencyService = currencyService;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeCurrencies();
    }

    private void initializeCurrencies() {
        if (currencyRepository.count() != 0)
            return;

        currencyService.update();
    }
}
