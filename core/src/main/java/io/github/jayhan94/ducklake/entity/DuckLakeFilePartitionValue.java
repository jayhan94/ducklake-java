package io.github.jayhan94.ducklake.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DuckLake file partition value entity class
 * Corresponds to table: ducklake_file_partition_value
 */
@Entity
@Table(name = "ducklake_file_partition_value")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DuckLakeFilePartitionValue {

    @Id
    @Column(name = "data_file_id")
    private Long dataFileId;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "partition_key_index")
    private Long partitionKeyIndex;

    @Column(name = "partition_value")
    private String partitionValue;
}