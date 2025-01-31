package com.practice.jpaentitiesserializable.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @EmbeddedId
    private UserId id;

    @Column(nullable = false, unique = false, updatable = true, length = 50)
    @NotBlank
    private String name;

    @Column(nullable = false, unique = false, updatable = true, length = 50)
    @NotBlank
    private String preferredName;

    public User() {
    }

    public User(UserId id, String name, String preferredName) {
        this.id = id;
        this.name = name;
        this.preferredName = preferredName;
    }

    public UserId getId() {
        return id;
    }

    public void setId(UserId id) {
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
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preferredName='" + preferredName + '\'' +
                '}';
    }
}
