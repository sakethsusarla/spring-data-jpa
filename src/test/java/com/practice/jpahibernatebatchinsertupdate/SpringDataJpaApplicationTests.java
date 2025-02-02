package com.practice.jpahibernatebatchinsertupdate;

import com.practice.jpahibernatebatchinsertupdate.model.Container;
import com.practice.jpahibernatebatchinsertupdate.model.Pod;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Testcontainers
class SpringDataJpaApplicationTests {

    static final int BATCH_SIZE = 5;
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    @PersistenceContext
    EntityManager entityManager;

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
        registry.add("spring.jpa.properties.hibernate.jdbc.batch_size", () -> BATCH_SIZE);
    }

    @Test
    void contextLoads() {
    }

//    @Test
//    @Transactional
//    void shouldNotInsertInBatch_whenBatchingIsNotConfigured() {
//        for (int i = 0; i < 10; ++i) {
//            final int podNumber = i + 1;
//            final Pod pod = new Pod("pod_%s".formatted(podNumber));
//            final List<Container> containerList = new ArrayList<>(10);
//
//            for (int j = 0; j < 10; ++j) {
//                final int containerNumber = j + 1;
//                final Container container = new Container("dummy", "pod_%s_container_%s".formatted(podNumber, containerNumber));
//
//                container.setPod(pod);
//                containerList.add(container);
//            }
//
//            pod.setContainers(containerList);
//
//            entityManager.persist(pod);
//        }
//
//        entityManager.flush();
//    }

    @Test
    @Transactional
    void shouldInsertInBatch_whenExplicitFlushIsNotDone() {
        for (int i = 1; i <= 10; ++i) {
            final Pod pod = new Pod("pod_%s".formatted(i));
            final List<Container> containerList = new ArrayList<>(10);

            for (int j = 1; j <= 10; ++j) {
                final Container container = new Container("dummy", "pod_%s_container_%s".formatted(i, j));

                container.setPod(pod);
                containerList.add(container);
                entityManager.persist(container);
            }

            pod.setContainers(containerList);

            entityManager.persist(pod);

            if (i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}

