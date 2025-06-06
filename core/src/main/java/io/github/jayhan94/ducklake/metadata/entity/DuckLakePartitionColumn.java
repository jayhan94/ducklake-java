package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake partition column entity class
 * Corresponds to table: ducklake_partition_column
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakePartitionColumn {

    private Long partitionId;
    private Long tableId;
    private Long partitionKeyIndex;
    private Long columnId;
    private String transform;
}