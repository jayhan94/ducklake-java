package io.github.jayhan94.ducklake.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * DuckLake snapshot entity class
 * Corresponds to table: ducklake_snapshot
 */
@Entity
@Table(name = "ducklake_snapshot")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeSnapshot {

    @Id
    @Column(name = "snapshot_id")
    private Long snapshotId;

    @Column(name = "snapshot_time")
    private Instant snapshotTime;

    @Column(name = "schema_version")
    private Long schemaVersion;

    @Column(name = "next_catalog_id")
    private Long nextCatalogId;

    @Column(name = "next_file_id")
    private Long nextFileId;
}