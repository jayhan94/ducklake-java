package io.github.jayhan94.ducklake.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake table column statistics entity class
 * Corresponds to table: ducklake_table_column_stats
 */
@Entity
@Table(name = "ducklake_table_column_stats")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeTableColumnStats {

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "column_id")
    private Long columnId;

    @Column(name = "contains_null")
    private Boolean containsNull;

    @Column(name = "contains_nan")
    private Boolean containsNan;

    @Column(name = "min_value")
    private String minValue;

    @Column(name = "max_value")
    private String maxValue;
}