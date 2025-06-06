package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake column tag entity class
 * Corresponds to table: ducklake_column_tag
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeColumnTag {

    private Long tableId;
    private Long columnId;
    private Long beginSnapshot;
    private Long endSnapshot;
    private String key;
    private String value;
}