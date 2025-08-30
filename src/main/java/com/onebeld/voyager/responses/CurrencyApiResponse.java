package com.onebeld.voyager.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyApiResponse {
    @JsonProperty("Timestamp")
    private Timestamp timestamp;

    @JsonProperty("Valute")
    private Map<String, Currency> currencies;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Currency {
        @JsonProperty("CharCode")
        private String charCode;
        @JsonProperty("Value")
        private BigDecimal value;
    }
}
