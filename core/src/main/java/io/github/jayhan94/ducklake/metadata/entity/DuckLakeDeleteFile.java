package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake delete file entity class
 * Corresponds to table: ducklake_delete_file
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeDeleteFile {

    private Long deleteFileId;
    private Long tableId;
    private Long beginSnapshot;
    private Long endSnapshot;
    private Long dataFileId;
    private String path;
    private Boolean pathIsRelative;
    private String format;
    private Long deleteCount;
    private Long fileSizeBytes;
    private Long footerSize;
    private String encryptionKey;
}