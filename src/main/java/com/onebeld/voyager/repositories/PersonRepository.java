package com.onebeld.voyager.repositories;

import com.onebeld.voyager.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
