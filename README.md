# Notes

1. Entities in JPA can exist in one of the following states: `new/ transient`, `managed/ persistent`, `detached`, `removed` 
2. These states have a clear sequence of transitions.
   1. An entity starts as `new/ transient` when first created
   2. It becomes `managed/ persistent` when associated with a persistence context, for example, after being persisted or retrieved from the database
   3. An entity can be `detached` from the context which means it's no longer tracked for changes
   4. An entity can be marked as `removed` indicating it will be deleted from the database