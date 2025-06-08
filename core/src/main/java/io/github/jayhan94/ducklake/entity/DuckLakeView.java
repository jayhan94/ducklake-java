package io.github.jayhan94.ducklake.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DuckLake view entity class
 * Corresponds to table: ducklake_view
 */
@Entity
@Table(name = "ducklake_view")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeView {

    @Column(name = "view_id")
    private Long viewId;

    @Column(name = "view_uuid")
    private UUID viewUuid;

    @Column(name = "begin_snapshot")
    private Long beginSnapshot;

    @Column(name = "end_snapshot")
    private Long endSnapshot;

    @Column(name = "schema_id")
    private Long schemaId;

    @Column(name = "view_name")
    private String viewName;

    @Column(name = "dialect")
    private String dialect;

    @Column(name = "sql")
    private String sql;

    @Column(name = "column_aliases")
    private String columnAliases;
}