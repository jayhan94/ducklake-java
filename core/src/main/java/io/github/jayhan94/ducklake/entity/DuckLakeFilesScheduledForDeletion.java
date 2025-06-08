package io.github.jayhan94.ducklake.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * DuckLake files scheduled for deletion entity class
 * Corresponds to table: ducklake_files_scheduled_for_deletion
 */
@Entity
@Table(name = "ducklake_files_scheduled_for_deletion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeFilesScheduledForDeletion {

    @Column(name = "data_file_id")
    private Long dataFileId;

    @Column(name = "path")
    private String path;

    @Column(name = "path_is_relative")
    private Boolean pathIsRelative;

    @Column(name = "schedule_start")
    private Instant scheduleStart;
}