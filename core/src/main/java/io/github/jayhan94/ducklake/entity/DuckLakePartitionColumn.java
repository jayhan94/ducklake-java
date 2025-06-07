package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake partition column entity class
 * Corresponds to table: ducklake_partition_column
 */
@Entity
@Table(name = "ducklake_partition_column")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakePartitionColumn {

    @Column(name = "partition_id")
    private Long partitionId;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "partition_key_index")
    private Long partitionKeyIndex;

    @Column(name = "column_id")
    private Long columnId;

    @Column(name = "transform")
    private String transform;
}