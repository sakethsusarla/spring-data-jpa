package com.practice.jpaentitylifecycleevents.model;

import com.practice.jpaentitylifecycleevents.entitylistener.EmployeeEntityListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@EntityListeners(value = {EmployeeEntityListener.class})
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 50, updatable = true, unique = false, insertable = true, nullable = false)
    @NotBlank
    private String name;

    @Column(name = "email_id", length = 50, updatable = false, unique = true, insertable = true, nullable = false)
    @Email
    private String email;

    @PastOrPresent
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createdAt;

    @PastOrPresent
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updatedAt;

    public Employee() {
    }

    public Employee(String name, String email) {
        this.name = name.trim();
        this.email = email.toLowerCase().trim();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(email, employee.email) && Objects.equals(createdAt, employee.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, createdAt);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
