package io.github.jayhan94.ducklake.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake snapshot changes entity class
 * Corresponds to table: ducklake_snapshot_changes
 */
@Entity
@Table(name = "ducklake_snapshot_changes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeSnapshotChanges {

    @Id
    @Column(name = "snapshot_id")
    private Long snapshotId;

    @Column(name = "changes_made")
    private String changesMade;
}