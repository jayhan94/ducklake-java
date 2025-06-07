package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake table statistics entity class
 * Corresponds to table: ducklake_table_stats
 */
@Entity
@Table(name = "ducklake_table_stats")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeTableStats {

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "record_count")
    private Long recordCount;

    @Column(name = "next_row_id")
    private Long nextRowId;

    @Column(name = "file_size_bytes")
    private Long fileSizeBytes;
}