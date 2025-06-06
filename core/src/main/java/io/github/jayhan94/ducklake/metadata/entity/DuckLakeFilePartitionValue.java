package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake file partition value entity class
 * Corresponds to table: ducklake_file_partition_value
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeFilePartitionValue {

    private Long dataFileId;
    private Long tableId;
    private Long partitionKeyIndex;
    private String partitionValue;
}