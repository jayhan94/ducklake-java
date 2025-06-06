package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake table statistics entity class
 * Corresponds to table: ducklake_table_stats
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeTableStats {

    private Long tableId;
    private Long recordCount;
    private Long nextRowId;
    private Long fileSizeBytes;
}