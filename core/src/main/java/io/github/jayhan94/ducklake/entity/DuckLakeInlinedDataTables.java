package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake inlined data tables entity class
 * Corresponds to table: ducklake_inlined_data_tables
 */
@Entity
@Table(name = "ducklake_inlined_data_tables")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeInlinedDataTables {

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "schema_snapshot")
    private Long schemaSnapshot;
}