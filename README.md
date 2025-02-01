# Notes

1. JPA supports various Java data types as persistable fields of an entity, often known as the basic types
2. A basic type maps directly to a column in the database. These include Java primitives and their wrapper classes,
   String, java.math.BigInteger, java.math.BigDecimal, various available date-time classes, enums and any other type
   that implements java.io.Serializable
3. Hibernate maintains a registry of basic types and uses it to resolve a column's specific org.hibernate.type.Type
4. We can use the `@Basic` annotation to mark a basic type property. The `@Basic` annotation on a field or a property
   signifies that it is a basic type and Hibernate should use the standard mapping for its persistence
5. **Note**: It is an optional annotation
6. We don't specify the `@Basic` annotation for basic type attribute, it is implicitly assumed, and the default values
   of this annotation apply
7. The `@Basic` annotation has two attributes, `_optional_` and `_fetch_`
    1. The `_optional_` attribute is a boolean parameter that defines whether the marked field or property allows null,
       defaults to true. So, if the underlying column is not a primitive type, the underlying column is assumed to be
       nullable by default
    2. The `_fetch_` attribute accepts a member of the enumeration `FetchType` which specifies whether the marked field
       or
       property should be lazily loaded or eagerly fetched, defaults to `FetchType.EAGER` but we can permit lazy loading
       by setting it to `FetchType.LAZY`
    3. Lazy loading will only make sense when we have a large `Serializable` object mapped as a basic type
8. We should explicitly use the `@Basic` annotation when willing to deviate from the default values of `_optional_` and
   `_fetch_` parameters
9. `@Basic` vs `@Column` annotations
    1. `@Basic` controls how JPA provider handles the field in memory and during persistence operations (affects
       persistence provider behavior)
    2. `@Column` specifies the mapping details between the field and database column (database level)

# Additional Notes

1. `@NotNull` (Bean Validation)

   - Part of Jakarta Bean Validation framework
   - Application-level validation
   - Validates before hitting persistence layer
   - Throws `ConstraintViolationException`
   - Most flexible - can be used with `@Valid` anywhere in application
   - Example: Service layer validation before saving

2. `@Basic(optional = false)` (JPA Level)

   - JPA/Persistence layer validation
   - Validates during persistence operations
   - Throws `PropertyValueException` with Hibernate
   - Least commonly used among the three
   - Redundant when using `@NotNull` and `@Column(nullable = false)`

3. `@Column(nullable = false)` (Database Level)

   - Database schema constraint (NOT NULL)
   - Final validation during database operations
   - Throws `SQLException` or `DataIntegrityViolationException`
   - Last line of defense for data integrity
   - DDL: Creates NOT NULL constraint in database

4. Recommended Combination
    ```java
    
    @NotNull
    @Column(nullable = false)
    private String name;
    ```