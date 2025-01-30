package com.practice.jpaentitylifecycle.service;

import com.practice.jpaentitylifecycle.model.User;
import com.practice.jpaentitylifecycle.repository.UserCrudRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserCrudRepository userCrudRepository;

    @Autowired
    public UserService(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Transactional
    public User create(String name) {
        return this.userCrudRepository.save(new User(name));
    }

    @Transactional
    public Optional<User> update(String name) {
        final Optional<User> userByName = this.userCrudRepository.findUserByName(name);

        userByName.ifPresent(user -> user.setName(name));

        return userByName;
    }
}
