package com.onebeld.voyager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.Book;
import java.util.Collection;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "person",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Review> reviews;

    @OneToMany(mappedBy = "person",   cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Booking> bookings;
}
