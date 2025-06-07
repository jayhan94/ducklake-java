/**
 * DuckLake metadata entity classes package
 * <p>
 * This package contains Java entity classes corresponding to the 19 metadata
 * tables
 * defined in the DuckLake specification:
 * <p>
 * Core metadata tables:
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeMetadata} - Metadata
 * key-value pairs
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeSnapshot} - Snapshot
 * information
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeSnapshotChanges} -
 * Snapshot change records
 * <p>
 * Schema and table management:
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeSchema} - Schema
 * definitions
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeTable} - Table
 * definitions
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeView} - View definitions
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeColumn} - Column
 * definitions
 * <p>
 * Tagging system:
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeTag} - Object tags
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeColumnTag} - Column tags
 * <p>
 * File management:
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeDataFile} - Data files
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeDeleteFile} - Delete
 * files
 * -
 * {@link io.github.jayhan94.ducklake.entity.DuckLakeFilesScheduledForDeletion}
 * - Files scheduled for deletion
 * <p>
 * Statistics:
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeFileColumnStatistics} -
 * File column statistics
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeTableStats} - Table
 * statistics
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeTableColumnStats} - Table
 * column statistics
 * <p>
 * Partitioning:
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakePartitionInfo} -
 * Partition information
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakePartitionColumn} -
 * Partition columns
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeFilePartitionValue} -
 * File partition values
 * <p>
 * Special tables:
 * - {@link io.github.jayhan94.ducklake.entity.DuckLakeInlinedDataTables} -
 * Inlined data tables
 * <p>
 * All entity classes use Lombok annotations to reduce boilerplate code:
 * - @Data: getter/setter/toString/equals/hashCode methods
 * - @Builder: builder pattern implementation
 * - @NoArgsConstructor/@AllArgsConstructor: constructor methods
 *
 * @author DuckLake Java Team
 * @version 1.0
 * @since 1.0
 */
package io.github.jayhan94.ducklake.entity;