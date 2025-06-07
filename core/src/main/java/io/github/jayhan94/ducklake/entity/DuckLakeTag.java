package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake tag entity class
 * Corresponds to table: ducklake_tag
 */
@Entity
@Table(name = "ducklake_tag")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeTag {

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "begin_snapshot")
    private Long beginSnapshot;

    @Column(name = "end_snapshot")
    private Long endSnapshot;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;
}