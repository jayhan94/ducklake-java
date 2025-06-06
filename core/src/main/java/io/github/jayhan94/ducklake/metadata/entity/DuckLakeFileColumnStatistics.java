package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake file column statistics entity class
 * Corresponds to table: ducklake_file_column_statistics
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeFileColumnStatistics {

    private Long dataFileId;
    private Long tableId;
    private Long columnId;
    private Long columnSizeBytes;
    private Long valueCount;
    private Long nullCount;
    private String minValue;
    private String maxValue;
    private Boolean containsNan;
}