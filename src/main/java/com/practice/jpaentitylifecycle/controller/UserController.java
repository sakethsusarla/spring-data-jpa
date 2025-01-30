package com.practice.jpaentitylifecycle.controller;

import com.practice.jpaentitylifecycle.model.User;
import com.practice.jpaentitylifecycle.service.UserService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    ResponseEntity<User> create(@RequestParam @NotBlank String name) {
        try {
            return ResponseEntity.ok(this.userService.create(name.trim()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    ResponseEntity<User> update(@RequestParam @NotBlank String name) {
        try {
            final Optional<User> updated = this.userService.update(name);
            return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
