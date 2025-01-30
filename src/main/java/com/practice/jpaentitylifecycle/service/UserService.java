package com.practice.jpaentitylifecycle.service;

import com.practice.jpaentitylifecycle.model.User;
import com.practice.jpaentitylifecycle.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Optional<User> update(String name, String preferredName) {
        final Optional<User> userByName = this.userCrudRepository.findUserByName(name);

        userByName.ifPresent(user -> user.setPreferredName(preferredName));

        return userByName;
    }

    @Transactional
    public Optional<User> delete(String name) {
        final Optional<User> userByName = this.userCrudRepository.findUserByName(name);

        userByName.ifPresent(userCrudRepository::delete);

        return userByName;
    }
}
