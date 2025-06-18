package io.github.jayhan94.ducklake.api;

/**
 * PartitionColumn represents a column in a partition.
 */
public interface PartitionColumn {
    /**
     * Gets the unique ID of the column.
     * 
     * @return the column ID
     */
    long columnId();

    /**
     * Defines where in the partition key the column is. For example, in a
     * partitioning by (a, b, c) the partition_key_index of b would be 1.
     * 
     * @return the partition key index
     */
    long partitionKeyIndex();

    /**
     * Defines a SQL-level expression to transform the column value, e.g. hashing.
     * 
     * @return the transform function
     */
    String transform();
}
