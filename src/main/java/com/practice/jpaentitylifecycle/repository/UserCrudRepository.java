package com.practice.jpaentitylifecycle.repository;


import com.practice.jpaentitylifecycle.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCrudRepository extends CrudRepository<User, UUID> {
    Optional<User> findUserByName(String name);
}
