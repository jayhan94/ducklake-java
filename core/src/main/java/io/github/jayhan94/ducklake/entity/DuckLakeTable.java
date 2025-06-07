package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DuckLake table entity class
 * Corresponds to table: ducklake_table
 */
@Entity
@Table(name = "ducklake_table")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeTable {

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "table_uuid")
    private UUID tableUuid;

    @Column(name = "begin_snapshot")
    private Long beginSnapshot;

    @Column(name = "end_snapshot")
    private Long endSnapshot;

    @Column(name = "schema_id")
    private Long schemaId;

    @Column(name = "table_name")
    private String tableName;
}