package com.practice.jpabasicannotation.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Basic(optional = false)
    @Column(nullable = false, name = "name", length = 50, unique = true, updatable = false)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false, name = "preferred_name", length = 50, updatable = true, unique = false)
    private String preferredName;

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

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", preferredName='" + preferredName + '\'' +
                '}';
    }
}
