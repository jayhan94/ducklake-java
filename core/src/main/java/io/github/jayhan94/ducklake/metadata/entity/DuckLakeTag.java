package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake tag entity class
 * Corresponds to table: ducklake_tag
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeTag {

    private Long objectId;
    private Long beginSnapshot;
    private Long endSnapshot;
    private String key;
    private String value;
}