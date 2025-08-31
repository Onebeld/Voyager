package com.onebeld.voyager.repositories;

import com.onebeld.voyager.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
