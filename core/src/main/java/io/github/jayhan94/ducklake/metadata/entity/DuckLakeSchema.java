package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DuckLake schema entity class
 * Corresponds to table: ducklake_schema
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeSchema {

    private Long schemaId;
    private UUID schemaUuid;
    private Long beginSnapshot;
    private Long endSnapshot;
    private String schemaName;
}