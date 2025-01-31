# Notes

1. The first (and the most obvious) way to set a default column value is to set it directly as an entity property value.
   For example, `preferredName = "Brown Dynamite"`
2. The drawback to this approach is that this has no effect over the SQL table definition. There won't be any default
   value in it.
    ```postgresql
    create table public.users
    (
        uuid           uuid        not null primary key,
        name           varchar(50) not null constraint uk3g1j96g94xpk3lpxl2qbl985x unique,
        preferred_name varchar(50) not null
    );
    ```
3. If you look at the above table definition, it doesn't reflect our default `preferredName` as "Brown Dynamite"
4. To create a default value directly in the SQL table definition, we can use the `@Column` annotation and set its
   `columnDefinition` parameter. Let's look at the update table definition
   ```postgresql
   create table public.users
   (
       uuid           uuid                                                     not null primary key,
       name           varchar(50)                                              not null constraint uk3g1j96g94xpk3lpxl2qbl985x unique,
       preferred_name varchar(50) default 'Brown Dynamite'::character varying  not null
   );
   ```
5. It is worth noting that we won't be able to set a given column to null when saving the entity for the first time. The
   default one will be set automatically if we don't provide any value.
6. Another approach to specify default values for columns is to use the `@ColumnDefault` annotation. This sets the
   default at the JPA level and the same is reflected in the SQL schema. _**Warning:** The catch here is that this comes from
   Hibernate and not Jakarta Persistence, so now the code is tightly coupled to Hibernate_. Here are some gotchas for
   `@ColumnDefault`,
    1. Default values will be applied automatically only if we omit them in the `INSERT` statement
    2. If we include columns in our `INSERT` statement, fields that aren't set will be left as `null` and the database
       won't apply the default values
   ```postgresql
      create table public.users
      (
         uuid           uuid                                                    not null primary key,
         name           varchar(50)                                             not null constraint uk3g1j96g94xpk3lpxl2qbl985x unique,
         preferred_name varchar(50) default 'Brown Dynamite'::character varying not null
      );
      ```
7. One other way to set default values is by leveraging the JPA entity lifecycle callbacks. [Using the @PrePersist
   annotation](https://github.com/sakethsusarla/spring-data-jpa/tree/jpa-entity-lifecycle-events), we could set the values programmatically before the entity is persisted. 