package io.github.jayhan94.ducklake.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake partition information entity class
 * Corresponds to table: ducklake_partition_info
 */
@Entity
@Table(name = "ducklake_partition_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakePartitionInfo {

    @Column(name = "partition_id")
    private Long partitionId;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "begin_snapshot")
    private Long beginSnapshot;

    @Column(name = "end_snapshot")
    private Long endSnapshot;
}