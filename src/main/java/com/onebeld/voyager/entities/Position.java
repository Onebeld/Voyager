package com.onebeld.voyager.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "positions")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short position;

    @NonNull
    @Column(name = "name")
    private String name;
}
