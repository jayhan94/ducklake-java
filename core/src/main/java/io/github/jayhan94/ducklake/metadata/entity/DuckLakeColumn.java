package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake column entity class
 * Corresponds to table: ducklake_column
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeColumn {

    private Long columnId;
    private Long beginSnapshot;
    private Long endSnapshot;
    private Long tableId;
    private Long columnOrder;
    private String columnName;
    private String columnType;
    private String initialDefault;
    private String defaultValue;
    private Boolean nullsAllowed;
    private Long parentColumn;
}