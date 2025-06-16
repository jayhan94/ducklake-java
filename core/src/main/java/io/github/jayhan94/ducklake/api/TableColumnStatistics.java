package io.github.jayhan94.ducklake.api;

import java.util.Optional;

/**
 * Represents aggregated statistics for a column at the table level.
 */
public interface TableColumnStatistics {
    /**
     * Gets the ID of the table this column statistics belongs to.
     * 
     * @return the table ID
     */
    long tableId();

    /**
     * Gets the ID of the column this statistics belongs to.
     * 
     * @return the column ID
     */
    long columnId();

    /**
     * Checks if the column contains any null values.
     * 
     * @return true if the column contains at least one null value, false otherwise
     */
    Optional<Boolean> containsNull();

    /**
     * Checks if the column contains any NaN (Not-a-Number) values.
     * 
     * @return true if the column contains at least one NaN value, false otherwise
     */
    Optional<Boolean> containsNaN();

    /**
     * Gets the minimum value for the column.
     * 
     * @return the minimum value as a string, or null if not available
     */
    Optional<String> minValue();

    /**
     * Gets the maximum value for the column.
     * 
     * @return the maximum value as a string, or null if not available
     */
    Optional<String> maxValue();
}