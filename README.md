# Notes

1. `Serializable` is one of the few marker interfaces (no methods or constants) found in core Java.
2. Serialization: converting POJOs into byte streams
3. Deserialization: converting byte streams into POJOs
4. To allow object serialization, a class must implement the `Serializable` interface. Otherwise, one could run into `java.io.NotSerializableException`
5. The JPA specification says _If an entity instance is to be passed by value as a detached object (e.g., through a remote interface), the entity class must implement the `Serializable` interface._ which means if the object is to leave the domain of the JVM, it'll require serialization
6. Entity classes consist of persistent fields. The JPA specification requires that fields on entity be Java primitives, Java serializable types or user-defined serializable types
7. An entity class can have a primitive or composite primary key. Multiple rules apply to a composite key, one of which is that a composite key is required to be serializable
8. While using the `@JoinColumn` annotation, the referenced field (_referencedColumnName_) must be serializable