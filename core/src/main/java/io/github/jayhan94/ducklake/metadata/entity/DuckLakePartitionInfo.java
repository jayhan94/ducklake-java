package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake partition information entity class
 * Corresponds to table: ducklake_partition_info
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakePartitionInfo {

    private Long partitionId;
    private Long tableId;
    private Long beginSnapshot;
    private Long endSnapshot;
}