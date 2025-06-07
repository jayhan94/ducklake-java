package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake delete file entity class
 * Corresponds to table: ducklake_delete_file
 */
@Entity
@Table(name = "ducklake_delete_file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeDeleteFile {

    @Id
    @Column(name = "delete_file_id")
    private Long deleteFileId;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "begin_snapshot")
    private Long beginSnapshot;

    @Column(name = "end_snapshot")
    private Long endSnapshot;

    @Column(name = "data_file_id")
    private Long dataFileId;

    @Column(name = "path")
    private String path;

    @Column(name = "path_is_relative")
    private Boolean pathIsRelative;

    @Column(name = "format")
    private String format;

    @Column(name = "delete_count")
    private Long deleteCount;

    @Column(name = "file_size_bytes")
    private Long fileSizeBytes;

    @Column(name = "footer_size")
    private Long footerSize;

    @Column(name = "encryption_key")
    private String encryptionKey;
}