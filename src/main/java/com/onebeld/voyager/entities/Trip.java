package com.onebeld.voyager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "trips")
@Getter
@Setter
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "modified_by_id", referencedColumnName = "id")
    private Employee modifiedBy;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR")
    private String description;

    @Column(name = "latitude", nullable = false)
    private float latitude;

    @Column(name = "longitude", nullable = false)
    private float longitude;

    @Column(name = "created", nullable = false)
    private Timestamp created;

    @Column(name = "updated")
    private Timestamp updated;

    @Column(name = "start")
    private Timestamp start;

    @Column(name = "end")
    private Timestamp end;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "trip",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private Collection<Review> reviews;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL,  orphanRemoval = true)
    private Collection<Booking>  bookings;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Image> images;
}
