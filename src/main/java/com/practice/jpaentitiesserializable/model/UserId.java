package com.practice.jpaentitiesserializable.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UserId implements Serializable {
    @Column(nullable = false, name = "group_id", updatable = false)
    @NotNull
    private UUID groupId;

    @Column(nullable = false, name = "email_id", updatable = false)
    @Email
    private String email;

    public UserId() {
    }

    public UserId(String email) {
        this.groupId = UUID.randomUUID();
        this.email = email;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(groupId, userId.groupId) && Objects.equals(email, userId.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, email);
    }

    @Override
    public String toString() {
        return "UserId{" +
                "groupId=" + groupId +
                ", email='" + email + '\'' +
                '}';
    }
}
