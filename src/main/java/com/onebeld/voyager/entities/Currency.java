package com.onebeld.voyager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "currency")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @NonNull
    @Column(name = "code", length = 3, nullable = false)
    private String code;

    @NonNull
    @Column(name = "rate", nullable = false)
    private BigDecimal rate;
}
