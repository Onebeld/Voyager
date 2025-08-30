package com.onebeld.voyager.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyDto {
    String code;
    BigDecimal rate;
}
