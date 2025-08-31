package com.onebeld.voyager.dto.trips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripLocationDto {
    Long id;
    Float latitude;
    Float longitude;
}
