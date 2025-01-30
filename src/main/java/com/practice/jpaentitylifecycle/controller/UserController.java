package com.practice.jpaentitylifecycle.controller;

import com.practice.jpaentitylifecycle.model.User;
import com.practice.jpaentitylifecycle.service.UserService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<User> update(@RequestParam @NotBlank String name, @RequestParam @NotBlank String preferredName) {
        try {
            final Optional<User> updated = this.userService.update(name, preferredName);
            return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity<User> delete(@RequestParam @NotBlank String name) {
        try {
            final Optional<User> deleted = this.userService.delete(name);
            return deleted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
