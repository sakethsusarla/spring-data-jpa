package com.practice.jpaentitylifecycleevents.controller;

import com.practice.jpaentitylifecycleevents.model.Employee;
import com.practice.jpaentitylifecycleevents.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get")
    public ResponseEntity<Employee> get(@Valid @RequestParam @Email String email) {
        final Optional<Employee> employee = this.employeeService.get(email);

        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/create")
    public ResponseEntity<Employee> create(@Valid @RequestParam @NotBlank String name, @Valid @RequestParam @Email String email) {
        try {
            final Employee employee = this.employeeService.create(name, email);

            return ResponseEntity.ok(employee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    ResponseEntity<Employee> update(@Valid @RequestParam @Email String email, @Valid @RequestParam @NotBlank String newName) {
        try {
            final Optional<Employee> updatedEmployee = this.employeeService.update(email, newName);

            return updatedEmployee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity<Employee> delete(@Valid @RequestParam @Email String email) {
        try {
            final Optional<Employee> deletedEmployee = this.employeeService.delete(email);

            return deletedEmployee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
