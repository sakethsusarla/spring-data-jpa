# Notes

1. `Serializable` is one of the few marker interfaces (no methods or constants) found in core Java
2. Serialization: converting POJOs into byte streams
3. Deserialization: converting byte streams into POJOs
4. To allow object serialization, a class must implement the `Serializable` interface. Otherwise, one could run into
   `java.io.NotSerializableException`
5. Entity classes must implement `Serializable` if:
    - They need to be passed by value as detached objects (e.g., through remote interfaces)
    - They are to leave the JVM's domain
    - They need to be stored in HTTP sessions
    - They are used in distributed caching scenarios
6. Entity fields must be one of:
    - Java primitives
    - Java serializable types
    - User-defined serializable types
7. For composite keys:
    - Classes annotated with @EmbeddedId or @IdClass must implement Serializable
    - This is a mandatory JPA specification requirement
    - Example: A composite key class combining UUID and email must be Serializable
8. Common use cases:
    - Detached Entities: When entities need to travel across JVM boundaries
    - HTTP Sessions: For storing entities in web session storage
    - Distributed Caching: When using distributed/clustered caching systems
    - Remote Interfaces: When passing entities through remote service calls