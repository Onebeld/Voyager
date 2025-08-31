package com.onebeld.voyager.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReviewDto {
    Long id;
    PersonReviewDto person;
    Timestamp time;
    String text;
    Short rating;
}
