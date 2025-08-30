package com.onebeld.voyager.dto.trips;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class TripShortInfoDto {
    Long id;
    String name;
    BigDecimal price;
    String currencyCode;
    Set<String> imageUrls;
}
