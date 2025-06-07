package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake column entity class
 * Corresponds to table: ducklake_column
 */
@Entity
@Table(name = "ducklake_column")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeColumn {

    @Column(name = "column_id")
    private Long columnId;

    @Column(name = "begin_snapshot")
    private Long beginSnapshot;

    @Column(name = "end_snapshot")
    private Long endSnapshot;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "column_order")
    private Long columnOrder;

    @Column(name = "column_name")
    private String columnName;

    @Column(name = "column_type")
    private String columnType;

    @Column(name = "initial_default")
    private String initialDefault;

    @Column(name = "default_value")
    private String defaultValue;

    @Column(name = "nulls_allowed")
    private Boolean nullsAllowed;

    @Column(name = "parent_column")
    private Long parentColumn;
}