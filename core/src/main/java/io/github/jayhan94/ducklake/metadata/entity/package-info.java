/**
 * DuckLake metadata entity classes package
 * 
 * This package contains Java entity classes corresponding to the 19 metadata
 * tables
 * defined in the DuckLake specification:
 * 
 * Core metadata tables:
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeMetadata} - Metadata
 * key-value pairs
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeSnapshot} - Snapshot
 * information
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeSnapshotChanges} -
 * Snapshot change records
 * 
 * Schema and table management:
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeSchema} - Schema
 * definitions
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeTable} - Table
 * definitions
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeView} - View definitions
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeColumn} - Column
 * definitions
 * 
 * Tagging system:
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeTag} - Object tags
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeColumnTag} - Column tags
 * 
 * File management:
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeDataFile} - Data files
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeDeleteFile} - Delete
 * files
 * -
 * {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeFilesScheduledForDeletion}
 * - Files scheduled for deletion
 * 
 * Statistics:
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeFileColumnStatistics} -
 * File column statistics
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeTableStats} - Table
 * statistics
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeTableColumnStats} - Table
 * column statistics
 * 
 * Partitioning:
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakePartitionInfo} -
 * Partition information
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakePartitionColumn} -
 * Partition columns
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeFilePartitionValue} -
 * File partition values
 * 
 * Special tables:
 * - {@link io.github.jayhan94.ducklake.metadata.entity.DuckLakeInlinedDataTables} -
 * Inlined data tables
 * 
 * All entity classes use Lombok annotations to reduce boilerplate code:
 * - @Data: getter/setter/toString/equals/hashCode methods
 * - @Builder: builder pattern implementation
 * - @NoArgsConstructor/@AllArgsConstructor: constructor methods
 * 
 * @author DuckLake Java Team
 * @version 1.0
 * @since 1.0
 */
package io.github.jayhan94.ducklake.metadata.entity;