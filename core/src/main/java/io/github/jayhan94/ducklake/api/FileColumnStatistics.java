package io.github.jayhan94.ducklake.api;

import java.util.Optional;

/**
 * Represents statistics for a single column within a specific data file.
 */
public interface FileColumnStatistics {
    /**
     * Gets the ID of the data file.
     * 
     * @return the data file ID
     */
    long dataFileId();

    /**
     * Gets the ID of the table this data file belongs to.
     * 
     * @return the table ID
     */
    long tableId();

    /**
     * Gets the ID of the column.
     * 
     * @return the column ID
     */
    long columnId();

    /**
     * Gets the total size of the column's data within the file in bytes.
     * 
     * @return the column size in bytes
     */
    long columnSizeBytes();

    /**
     * Gets the total number of valid values in the column.
     * 
     * @return the total value count
     */
    Optional<Long> valueCount();

    /**
     * Gets the number of null values in the column.
     * 
     * @return the null value count
     */
    Optional<Long> nullCount();

    /**
     * Gets the number of NaN (Not-a-Number) values in the column.
     * 
     * @return the NaN value count
     */
    Optional<Long> nanCount();

    /**
     * Gets the minimum value for the column in this file.
     * 
     * @return the minimum value as a string
     */
    Optional<String> minValue();

    /**
     * Gets the maximum value for the column in this file.
     * 
     * @return the maximum value as a string
     */
    Optional<String> maxValue();

    /**
     * Checks if the column in this file contains any NaN values.
     * 
     * @return true if the column contains at least one NaN value, false otherwise
     */
    Optional<Boolean> containsNaN();
}
