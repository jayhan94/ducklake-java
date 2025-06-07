package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake metadata entity class
 * Corresponds to table: ducklake_metadata
 * CREATE TABLE ducklake_metadata (key VARCHAR NOT NULL, value VARCHAR NOT
 * NULL);
 */
@Entity
@Table(name = "ducklake_metadata")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeMetadata {

    @Column(name = "key", nullable = false)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;
}