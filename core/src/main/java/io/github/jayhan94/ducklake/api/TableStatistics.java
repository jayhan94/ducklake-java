package io.github.jayhan94.ducklake.api;

import java.util.List;

/**
 * Represents aggregated statistics for a table, such as total row count and
 * size.
 */
public interface TableStatistics {
    /**
     * Gets the total number of rows in the table.
     * 
     * @return the total number of rows
     */
    long recordCount();

    /**
     * Gets the total size of the table in bytes.
     * 
     * @return the total size of the table in bytes
     */
    long totalSizeBytes();

    /**
     * Gets the next available row ID for the table.
     * 
     * @return the next row id
     */
    long nextRowId();

    /**
     * Gets the list of column-level statistics for the table.
     * 
     * @return a list of {@link TableColumnStatistics}
     */
    List<TableColumnStatistics> columnStatistics();
}
