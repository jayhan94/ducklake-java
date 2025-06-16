package io.github.jayhan94.ducklake.api;

/**
 * Represents a schema, which acts as a namespace for tables.
 */
public interface Schema {
    /**
     * Gets the unique ID of the schema.
     * 
     * @return the schema ID
     */
    long schemaId();

    /**
     * Gets the name of the schema.
     * 
     * @return the schema name
     */
    String schemaName();
}
