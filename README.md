# Notes

1. Batching allows us to send a group of SQL statements to the database in a single network call. It helps optimize the
   network and memory usage of our application
2. When running our application, we will need to verify that insert/ update statements are indeed sent in batches. We
   can't tell from Hibernate logs whether the SQL statements are batched or not. We will need
   to [intercept the SQL logging with P6Spy](https://www.baeldung.com/java-p6spy-intercept-sql-logging)
3. Hibernate doesn't enable batching by default which means it'll send a separate SQL statement for each insert/ update
   operation
   ```plaintext
   insert into pods (name,id) values (?,?)
   insert into pods (name,id) values ('pod_1',1);
   Hibernate: insert into containers (image,name,pod_id,id) values (?,?,?,?)
   2025-02-02T20:15:02.185+05:30  INFO 18004 --- [spring-data-jpa] [    Test worker] p6spy                                    : #1738507502185 | took 2ms | statement | connection 3| url jdbc:postgresql://localhost:34087/test?loggerLevel=OFF
   insert into containers (image,name,pod_id,id) values (?,?,?,?)
   insert into containers (image,name,pod_id,id) values ('dummy','pod_1_container_1',1,1);
   Hibernate: insert into containers (image,name,pod_id,id) values (?,?,?,?)
   2025-02-02T20:15:02.189+05:30  INFO 18004 --- [spring-data-jpa] [    Test worker] p6spy                                    : #1738507502189 | took 1ms | statement | connection 3| url jdbc:postgresql://localhost:34087/test?loggerLevel=OFF
   insert into containers (image,name,pod_id,id) values (?,?,?,?)
   insert into containers (image,name,pod_id,id) values ('dummy','pod_1_container_2',1,2);
   Hibernate: insert into containers (image,name,pod_id,id) values (?,?,?,?)
   2025-02-02T20:15:02.190+05:30  INFO 18004 --- [spring-data-jpa] [    Test worker] p6spy                                    : #1738507502190 | took 1ms | statement | connection 3| url jdbc:postgresql://localhost:34087/test?loggerLevel=OFF
   insert into containers (image,name,pod_id,id) values (?,?,?,?)
   insert into containers (image,name,pod_id,id) values ('dummy','pod_1_container_3',1,3);
   Hibernate: insert into containers (image,name,pod_id,id) values (?,?,?,?)
   2025-02-02T20:15:02.190+05:30  INFO 18004 --- [spring-data-jpa] [    Test worker] p6spy                                    : #1738507502190 | took 1ms | statement | connection 3| url jdbc:postgresql://localhost:34087/test?loggerLevel=OFF
   insert into containers (image,name,pod_id,id) values (?,?,?,?)
   insert into containers (image,name,pod_id,id) values ('dummy','pod_1_container_4',1,4);
   Hibernate: insert into containers (image,name,pod_id,id) values (?,?,?,?)
   2025-02-02T20:15:02.198+05:30  INFO 18004 --- [spring-data-jpa] [    Test worker] p6spy                                    : #1738507502198 | took 1ms | statement | connection 3| url jdbc:postgresql://localhost:34087/test?loggerLevel=OFF
   insert into containers (image,name,pod_id,id) values (?,?,?,?)
   insert into containers (image,name,pod_id,id) values ('dummy','pod_1_container_5',1,5);
   Hibernate: insert into containers (image,name,pod_id,id) values (?,?,?,?)
   2025-02-02T20:15:02.198+05:30  INFO 18004 --- [spring-data-jpa] [    Test worker] p6spy                                    : #1738507502198 | took 1ms | statement | connection 3| url jdbc:postgresql://localhost:34087/test?loggerLevel=OFF
   insert into containers (image,name,pod_id,id) values (?,?,?,?)
   insert into containers (image,name,pod_id,id) values ('dummy','pod_1_container_6',1,6);
   Hibernate: insert into containers (image,name,pod_id,id) values (?,?,?,?)
   2025-02-02T20:15:02.204+05:30  INFO 18004 --- [spring-data-jpa] [    Test worker] p6spy                                    : #1738507502204 | took 1ms | statement | connection 3| url jdbc:postgresql://localhost:34087/test?loggerLevel=OFF
   insert into containers (image,name,pod_id,id) values (?,?,?,?)
   insert into containers (image,name,pod_id,id) values ('dummy','pod_1_container_7',1,7);
   Hibernate: insert into containers (image,name,pod_id,id) values (?,?,?,?)
   2025-02-02T20:15:02.204+05:30  INFO 18004 --- [spring-data-jpa] [    Test worker] p6spy                                    : #1738507502204 | took 1ms | statement | connection 3| url jdbc:postgresql://localhost:34087/test?loggerLevel=OFF
   insert into containers (image,name,pod_id,id) values (?,?,?,?)
   insert into containers (image,name,pod_id,id) values ('dummy','pod_1_container_8',1,8);
   ...
   ...
   ...
   insert into containers (image,name,pod_id,id) values (?,?,?,?)
   insert into containers (image,name,pod_id,id) values ('dummy','pod_10_container_10',10,100);
   ```
4. We should configure Hibernate to enable batching.
   1. Set the `hibernate.jdbc.batch_size` property to a number bigger than 0 (either programmatically or through the application.yml)
   2. 