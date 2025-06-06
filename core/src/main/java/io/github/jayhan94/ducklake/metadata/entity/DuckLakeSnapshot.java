package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * DuckLake snapshot entity class
 * Corresponds to table: ducklake_snapshot
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeSnapshot {

    private Long snapshotId;
    private Instant snapshotTime;
    private Long schemaVersion;
    private Long nextCatalogId;
    private Long nextFileId;
}