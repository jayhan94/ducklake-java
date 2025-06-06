package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake snapshot changes entity class
 * Corresponds to table: ducklake_snapshot_changes
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeSnapshotChanges {

    private Long snapshotId;
    private String changesMade;
}