package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake data file entity class
 * Corresponds to table: ducklake_data_file
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeDataFile {

    private Long dataFileId;
    private Long tableId;
    private Long beginSnapshot;
    private Long endSnapshot;
    private Long fileOrder;
    private String path;
    private Boolean pathIsRelative;
    private String fileFormat;
    private Long recordCount;
    private Long fileSizeBytes;
    private Long footerSize;
    private Long rowIdStart;
    private Long partitionId;
    private String encryptionKey;
    private String partialFileInfo;
}