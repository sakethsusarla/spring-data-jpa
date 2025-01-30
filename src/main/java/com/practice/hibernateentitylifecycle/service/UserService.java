package com.practice.hibernateentitylifecycle.service;

import com.practice.hibernateentitylifecycle.model.User;
import com.practice.hibernateentitylifecycle.repository.UserCrudRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
