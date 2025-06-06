package io.github.jayhan94.ducklake.metadata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake metadata entity class
 * Corresponds to table: ducklake_metadata
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeMetadata {

    private String key;
    private String value;
}