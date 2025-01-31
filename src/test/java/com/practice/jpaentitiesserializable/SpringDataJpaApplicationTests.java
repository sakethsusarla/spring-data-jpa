package com.practice.jpaentitiesserializable;

import com.practice.jpaentitiesserializable.model.User;
import com.practice.jpaentitiesserializable.model.UserId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
@Transactional
class SpringDataJpaApplicationTests {

    @PersistenceContext
    EntityManager entityManager;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    @BeforeAll
    static void setup() {
        postgres.start();
    }

    @AfterAll
    static void teardown() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void shouldSucceed_whenPersisted() {
        // setup
        UserId userId = new UserId("raj@browndynamite.com");
        User user = new User(userId, "raj", "brown dynamite");

        // execute
        entityManager.persist(user);

        // assert
        final User queriedUser = entityManager.find(User.class, userId);
        Assertions.assertEquals(user.getName(), queriedUser.getName());
        Assertions.assertEquals(user.getPreferredName(), queriedUser.getPreferredName());
    }

}
