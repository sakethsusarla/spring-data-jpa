package com.practice.jpaentitylifecycleevents.repository;


import com.practice.jpaentitylifecycleevents.model.Employee;
import jakarta.validation.constraints.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeCrudRepository extends CrudRepository<Employee, UUID> {
    Optional<Employee> findByEmail(@Email String email);
}
