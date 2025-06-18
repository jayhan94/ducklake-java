package io.github.jayhan94.ducklake.api;

import java.util.List;

/**
 * PartitionInfo represents the partitioning information for a table.
 */
public interface PartitionInfo {
    /**
     * Gets the unique ID of the partition.
     * 
     * @return the partition ID
     */
    long partitionId();

    /**
     * Gets the unique ID of the table.
     * 
     * @return the table ID
     */
    long tableId();

    /**
     * Gets the list of partition columns.
     * 
     * @return a list of {@link PartitionColumn}
     */
    List<PartitionColumn> partitionColumns();
}