package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DuckLake schema entity class
 * Corresponds to table: ducklake_schema
 */
@Entity
@Table(name = "ducklake_schema")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeSchema {

    @Id
    @Column(name = "schema_id")
    private Long schemaId;

    @Column(name = "schema_uuid")
    private UUID schemaUuid;

    @Column(name = "begin_snapshot")
    private Long beginSnapshot;

    @Column(name = "end_snapshot")
    private Long endSnapshot;

    @Column(name = "schema_name")
    private String schemaName;
}