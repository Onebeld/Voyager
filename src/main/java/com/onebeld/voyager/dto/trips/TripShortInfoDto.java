package com.onebeld.voyager.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripShortInfoDto {
    Long id;
    String name;
    BigDecimal price;
    String currencyCode;
    Set<String> imageUrls;
}
