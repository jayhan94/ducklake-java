package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake table column statistics entity class
 * Corresponds to table: ducklake_table_column_stats
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeTableColumnStats {

    private Long tableId;
    private Long columnId;
    private Boolean containsNull;
    private Boolean containsNan;
    private String minValue;
    private String maxValue;
}