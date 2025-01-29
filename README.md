# Notes

1. Entities in JPA are nothing but POJOs representing data that can be persisted in the database.
2. An entity represents a table in the database
3. Every instance of an entity represents a row in the table
4. `@Entity` annotation is specified at the class level
5. Ensure that the class has a no-arg constructor and a primary key
6. The entity classes must not be declared final because various JPA implementations will try subclassing the entity to provide their functionality
7. Each JPA entity must have a primary key that uniquely identifies it. The `@Id` annotation defines the primary key.
8. The primary key/ unique identifier can be generated in different ways which are specified by the `@GeneratedValue` annotation
9. One can choose from 5 different generation strategies at the time of writing this notes : `AUTO`, `UUID`, `TABLE`, `IDENTITY`, `SEQUENCE` through the `strategy` attribute of `@GeneratedValue` annotation
10. Most of the time, the name of the table and the entity won't be the same, the table name can be specified using the `name` attribute of the `@Table` annotation
11. The schema (similar to namespaces) that the table belongs to in the database can be specified through the `schema` attribute of the `@Table` annotation. This is particularly useful when you want to organize your tables into different logical groups
12. The `@Column` annotation is used to specify the details of a column in the table. It has several attributes like `name`, `nullable`, `length`, `unique`, `columnDefinition` etc.
13. To not persist a specific attribute of the entity, the `@Transient` annotation could be used.
14. To store temporal values (relating to time), the `@Temporal` annotation could be used over an attribute. The type of the temporal data that needs to be saved, can be specified using the `TemporalType` enum. At the time of writing this notes, `TemporalType.DATE`, `TemporalType.TIME`, `TemporalType.TIMESTAMP` are supported
15. To persist an enum type, the `@Enumerated` annotation can be used. To persist the values as strings, the `EnumType.STRING` needs to be specified. The default is `EnumType.ORDINAL`