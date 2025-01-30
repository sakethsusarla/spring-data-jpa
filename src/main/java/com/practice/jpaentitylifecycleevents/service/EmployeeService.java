package com.practice.jpaentitylifecycleevents.service;

import com.practice.jpaentitylifecycleevents.model.Employee;
import com.practice.jpaentitylifecycleevents.repository.EmployeeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeCrudRepository employeeCrudRepository;

    @Autowired
    public EmployeeService(EmployeeCrudRepository employeeCrudRepository) {
        this.employeeCrudRepository = employeeCrudRepository;
    }

    @Transactional
    public Employee create(String name, String email) throws IllegalArgumentException {
        if (this.employeeCrudRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Employee already registered with this email");
        }
        return this.employeeCrudRepository.save(new Employee(name, email));
    }

    @Transactional
    public Optional<Employee> update(String email, String newName) {
        final Optional<Employee> employee = this.employeeCrudRepository.findByEmail(email);

        employee.ifPresent(emp -> emp.setName(newName));

        return employee;
    }

    @Transactional
    public Optional<Employee> delete(String email) {
        final Optional<Employee> employee = this.employeeCrudRepository.findByEmail(email);

        employee.ifPresent(this.employeeCrudRepository::delete);

        return employee;
    }

    @Transactional(readOnly = true)
    public Optional<Employee> get(String email) {
        return this.employeeCrudRepository.findByEmail(email);
    }
}
