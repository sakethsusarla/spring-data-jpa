# Notes

1. There are several events that an application can listen for during a JPA entity's lifecycle
    1. before persist is called on a new entity `@PrePersist`
    2. after persist is called on a new entity `@PostPersist`
    3. before an entity is removed `@PreRemove`
    4. after an entity is removed `@PostRemove`
    5. before an update operation `@PreUpdate`
    6. after an update operation `@PostUpdate`
    7. after an entity has been loaded `@PostLoad`
2. There are two approaches for using entity lifecycle event annotations:
   1. annotation methods in the entity
   2. creating an EntityListener with annotated callback methods
3. Both approaches can be used at the same time. Regardless of where they are used, callback methods are suppossed to have a void return type
4. If we're using `@GeneratedValue` to automatically generate our primary keys, we can expect the key to be available in the method annotated with `@PostPersist`
5. `@PreUpdate`, `@PreRemove` and `@PrePersist` can happen right after the operation occurs, after a flush or at the end of a transaction
6. `@PreUpdate` is called if the data has actually changed and `@PostUpdate` is called regardless of whether anything actually changed
7. If any of the callbacks for persisting or removing an entity throw an exception, the transaction will be rolled back
8. 