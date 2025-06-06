package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake inlined data tables entity class
 * Corresponds to table: ducklake_inlined_data_tables
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeInlinedDataTables {

    private Long tableId;
    private String tableName;
    private Long schemaSnapshot;
}