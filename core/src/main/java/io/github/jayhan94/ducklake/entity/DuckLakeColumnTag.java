package io.github.jayhan94.ducklake.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake column tag entity class
 * Corresponds to table: ducklake_column_tag
 */
@Entity
@Table(name = "ducklake_column_tag")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeColumnTag {

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "column_id")
    private Long columnId;

    @Column(name = "begin_snapshot")
    private Long beginSnapshot;

    @Column(name = "end_snapshot")
    private Long endSnapshot;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;
}