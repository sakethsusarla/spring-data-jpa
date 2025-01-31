package com.practice.jpadefaultcolumnvalues;

import com.practice.jpadefaultcolumnvalues.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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

import java.util.UUID;

@SpringBootTest
@Testcontainers
@Transactional
class SpringDataJpaApplicationTests {

    @PersistenceContext
    private EntityManager entityManager;

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
    void shouldSaveWithDefaultValues_whenSavedUsingQuery() {

        // setup
        final Query query = entityManager.createNativeQuery("INSERT INTO public.users (id, name) VALUES (?, ?)");
        final UUID id = UUID.randomUUID();
        query.setParameter(1, id);
        query.setParameter(2, "raj");

        // execute
        query.executeUpdate();

        // assert
        final User user = entityManager.find(User.class, id);
        Assertions.assertEquals("raj", user.getName());
        Assertions.assertEquals("Brown Dynamite", user.getPreferredName());
    }
}
