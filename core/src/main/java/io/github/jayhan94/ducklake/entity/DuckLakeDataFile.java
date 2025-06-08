package io.github.jayhan94.ducklake.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake data file entity class
 * Corresponds to table: ducklake_data_file
 */
@Entity
@Table(name = "ducklake_data_file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeDataFile {

    @Id
    @Column(name = "data_file_id")
    private Long dataFileId;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "begin_snapshot")
    private Long beginSnapshot;

    @Column(name = "end_snapshot")
    private Long endSnapshot;

    @Column(name = "file_order")
    private Long fileOrder;

    @Column(name = "path")
    private String path;

    @Column(name = "path_is_relative")
    private Boolean pathIsRelative;

    @Column(name = "file_format")
    private String fileFormat;

    @Column(name = "record_count")
    private Long recordCount;

    @Column(name = "file_size_bytes")
    private Long fileSizeBytes;

    @Column(name = "footer_size")
    private Long footerSize;

    @Column(name = "row_id_start")
    private Long rowIdStart;

    @Column(name = "partition_id")
    private Long partitionId;

    @Column(name = "encryption_key")
    private String encryptionKey;

    @Column(name = "partial_file_info")
    private String partialFileInfo;
}