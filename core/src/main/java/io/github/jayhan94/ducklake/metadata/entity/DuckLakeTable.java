package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DuckLake table entity class
 * Corresponds to table: ducklake_table
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeTable {

    private Long tableId;
    private UUID tableUuid;
    private Long beginSnapshot;
    private Long endSnapshot;
    private Long schemaId;
    private String tableName;
}