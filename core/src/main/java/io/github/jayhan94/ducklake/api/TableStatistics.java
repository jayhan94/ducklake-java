package io.github.jayhan94.ducklake.api;

import java.util.List;

public interface TableStatistics extends PrettyPrint {
    @Override
    default String prettyString() {
        return "TableStatistics{" +
                "rowCount=" + rowCount() +
                ", totalSizeBytes=" + totalSizeBytes() +
                ", nextRowId=" + nextRowId() +
                ", columnStatistics=" + columnStatistics() +
                "}";
    }

    /**
     * Get the total number of rows in the table
     * 
     * @return the total number of rows
     */
    long rowCount();

    /**
     * Get the total size of the table in bytes
     * 
     * @return the total size of the table in bytes
     */
    long totalSizeBytes();

    /**
     * Get the next row id
     * 
     * @return the next row id
     */
    long nextRowId();

    /**
     * Get the column statistics
     * 
     * @return the column statistics
     */
    List<TableColumnStatistics> columnStatistics();
}
