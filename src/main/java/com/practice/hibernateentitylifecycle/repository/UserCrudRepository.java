package com.practice.hibernateentitylifecycle.repository;


import com.practice.hibernateentitylifecycle.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserCrudRepository extends CrudRepository<User, UUID> {
}
