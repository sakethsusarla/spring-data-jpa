# Notes

1. JPA default table name generation is specific to its implementation
2. In Hibernate, the default table name is the name of the class with the first letter capitalized
3. The easiest way to set a custom SQL table name is to annotate the entity with `@Table` and define the `name` attribute
4. The variable can also be stored in a `static final` variable
5. In JPQL queries, we use the entity class name. This name can be changed by defining the `name` attribute of the `@Entity` annotation