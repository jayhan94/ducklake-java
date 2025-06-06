package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DuckLake view entity class
 * Corresponds to table: ducklake_view
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeView {

    private Long viewId;
    private UUID viewUuid;
    private Long beginSnapshot;
    private Long endSnapshot;
    private Long schemaId;
    private String viewName;
    private String dialect;
    private String sql;
    private String columnAliases;
}