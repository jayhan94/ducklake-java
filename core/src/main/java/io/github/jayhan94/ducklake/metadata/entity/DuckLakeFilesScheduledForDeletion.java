package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * DuckLake files scheduled for deletion entity class
 * Corresponds to table: ducklake_files_scheduled_for_deletion
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeFilesScheduledForDeletion {

    private Long dataFileId;
    private String path;
    private Boolean pathIsRelative;
    private Instant scheduleStart;
}