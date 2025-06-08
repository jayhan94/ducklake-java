package io.github.jayhan94.ducklake.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake file column statistics entity class
 * Corresponds to table: ducklake_file_column_statistics
 */
@Entity
@Table(name = "ducklake_file_column_statistics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeFileColumnStatistics {

    @Column(name = "data_file_id")
    private Long dataFileId;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "column_id")
    private Long columnId;

    @Column(name = "column_size_bytes")
    private Long columnSizeBytes;

    @Column(name = "value_count")
    private Long valueCount;

    @Column(name = "null_count")
    private Long nullCount;

    @Column(name = "min_value")
    private String minValue;

    @Column(name = "max_value")
    private String maxValue;

    @Column(name = "contains_nan")
    private Boolean containsNan;
}