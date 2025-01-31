package com.practice.jpadefaultcolumnvalues.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50, updatable = false, unique = true)
    @NotBlank
    private String name;

    @Column(nullable = false, updatable = true, unique = false, columnDefinition = "varchar(50) default 'Brown Dynamite'")
    //@Column(nullable = false, length = 50, updatable = true, unique = false)
    //@ColumnDefault("'Brown Dynamite'")
    @NotBlank
    private String preferredName;

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
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
