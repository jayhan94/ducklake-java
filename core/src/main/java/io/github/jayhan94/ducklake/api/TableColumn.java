package io.github.jayhan94.ducklake.api;

import java.util.Optional;

import io.github.jayhan94.ducklake.datatype.DataType;

/**
 * Represents a column within a table's schema.
 */
public interface TableColumn {
    /**
     * Gets the unique ID of the column.
     * 
     * @return the column ID
     */
    long columnId();

    /**
     * Gets the name of the column.
     * 
     * @return the column name
     */
    String columnName();

    /**
     * Gets the data type of the column.
     * 
     * @return the {@link DataType} of the column
     */
    DataType columnType();

    /**
     * Checks if the column can contain null values.
     * 
     * @return true if nulls are allowed, false otherwise
     */
    boolean isNullable();

    /**
     * Gets the current default value for the column.
     * 
     * @return the default value, or null if not set
     */
    Optional<Object> defaultValue();

    /**
     * Gets the initial default value for the column at the time of creation.
     * 
     * @return the initial default value, or null if not set
     */
    Optional<Object> initialDefault();
}