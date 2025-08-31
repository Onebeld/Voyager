package com.onebeld.voyager.repositories;

import com.onebeld.voyager.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
