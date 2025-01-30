# Notes

1. Entities in JPA can exist in one of the following states: `new/ transient`, `managed/ persistent`, `detached`, `removed` 
2. These states have a clear sequence of transitions.
   1. An entity starts as `new/ transient` when first created
   2. It becomes `managed/ persistent` when associated with a persistence context, for example, after being persisted or retrieved from the database
   3. An entity can be `detached` from the context which means it's no longer tracked for changes
   4. An entity can be marked as `removed` indicating it will be deleted from the database
3. When an entity is first created using the new keyword, it's in the `new/ transient` state. It's not associated to any persistence context and any changes made to it won't have any implication at the database level unless it is explicitly persisted (See `UserService#create`)
4. A `managed/ persistent` entity is associated with a persistence context and any changes made to it will be automatically synchronized in the database once the transaction completes. An explicit call to save or update isn't necessary (See `UserService#update`)
5. Entities can become `detached` from the persistence context meaning they're no longer monitored for changes by the entity manager (interface to the persistence context; analogy: persistence context is the shopping cart, entity manager is the shopping interface - lets you add, remove or check what's in the cart). Changes made to a detached entity won't be automatically synchronized with the database. The entity must be merged back into the persistence context. Entities can become detached in several ways:
   1. When a transaction completes, all managed entities associated with the EntityManager become `detached`
   2. When an entity manager is closed (In Spring applications, the EntityManager closing is typically managed by the Spring IoC container)