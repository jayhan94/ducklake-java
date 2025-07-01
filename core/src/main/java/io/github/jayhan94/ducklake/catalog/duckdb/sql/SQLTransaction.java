package io.github.jayhan94.ducklake.catalog.duckdb.sql;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import io.github.jayhan94.ducklake.entity.DuckLakeColumn;
import io.github.jayhan94.ducklake.entity.DuckLakeDataFile;
import io.github.jayhan94.ducklake.entity.DuckLakeDeleteFile;
import io.github.jayhan94.ducklake.entity.DuckLakeFileColumnStatistics;
import io.github.jayhan94.ducklake.entity.DuckLakePartitionInfo;
import io.github.jayhan94.ducklake.entity.DuckLakePartitionColumn;
import io.github.jayhan94.ducklake.entity.DuckLakeSchema;
import io.github.jayhan94.ducklake.entity.DuckLakeSnapshot;
import io.github.jayhan94.ducklake.entity.DuckLakeTable;
import io.github.jayhan94.ducklake.entity.DuckLakeTableColumnStats;
import io.github.jayhan94.ducklake.entity.DuckLakeTableStats;

public interface SQLTransaction {
    /**
     * Get the latest snapshot
     * 
     * @return the latest snapshot
     */
    @SqlQuery("""
            SELECT snapshot_id, snapshot_time, schema_version, next_catalog_id, next_file_id
            FROM ducklake_snapshot
            WHERE snapshot_id = (SELECT max(snapshot_id) FROM ducklake_snapshot)
            """)
    DuckLakeSnapshot getLatestSnapshot();

    /**
     * Get a snapshot by ID
     * 
     * @param snapshotId the snapshot ID
     * @return the snapshot
     */
    @SqlQuery("""
            SELECT snapshot_id, snapshot_time, schema_version, next_catalog_id, next_file_id
            FROM ducklake_snapshot
            WHERE snapshot_id = :snapshotId
            """)
    DuckLakeSnapshot getSnapshot(@Bind("snapshotId") long snapshotId);

    /**
     * Get schema information by snapshot ID
     * 
     * @param snapshotId snapshot ID
     * @return schema information
     */
    @SqlQuery("""
            SELECT schema_id, schema_uuid, begin_snapshot, end_snapshot, schema_name, path, path_is_relative
            FROM ducklake_schema
            WHERE begin_snapshot <= :snapshotId
            AND (end_snapshot > :snapshotId OR end_snapshot IS NULL)
            """)
    List<DuckLakeSchema> listSchemas(@Bind("snapshotId") long snapshotId);

    /**
     * Get schema information by schema name and snapshot ID
     * 
     * @param snapshotId snapshot ID
     * @param schemaName schema name
     * @return schema information
     */
    @SqlQuery("""
            SELECT schema_id, schema_uuid, begin_snapshot, end_snapshot, schema_name, path, path_is_relative
            FROM ducklake_schema
            WHERE schema_name = :schemaName
            AND begin_snapshot <= :snapshotId
            AND (end_snapshot > :snapshotId OR end_snapshot IS NULL)
            """)
    DuckLakeSchema getSchema(@Bind("snapshotId") long snapshotId, @Bind("schemaName") String schemaName);

    /**
     * List tables by schema name and snapshot ID
     * 
     * @param snapshotId snapshot ID
     * @param schemaId   schema ID
     * @return list of tables
     */
    @SqlQuery("""
            SELECT table_id, table_uuid, begin_snapshot, end_snapshot, schema_id, table_name, path, path_is_relative
            FROM ducklake_table
            WHERE schema_id = :schemaId
            AND begin_snapshot <= :snapshotId
            AND (end_snapshot > :snapshotId OR end_snapshot IS NULL)
            """)
    List<DuckLakeTable> listTables(@Bind("snapshotId") long snapshotId, @Bind("schemaId") long schemaId);

    /**
     * Get table information by table name and snapshot ID
     * 
     * @param table_name table name
     * @param snapshotId snapshot ID
     * @return table information
     */
    @SqlQuery("""
            SELECT table_id, table_uuid, begin_snapshot, end_snapshot, schema_id, table_name, path, path_is_relative
            FROM ducklake_table
            WHERE table_name = :tableName
            AND begin_snapshot <= :snapshotId
            AND schema_id = :schemaId
            AND (end_snapshot > :snapshotId OR end_snapshot is NULL)
            """)
    DuckLakeTable getTable(@Bind("snapshotId") long snapshotId, @Bind("schemaId") long schemaId,
            @Bind("tableName") String tableName);

    /**
     * Creates a new table record in the database.
     *
     * @param duckLakeTable The DuckLakeTable object containing the table
     *                      information
     * @return The created DuckLakeTable object
     */
    @SqlUpdate("""
            INSERT INTO ducklake_table(table_id, table_uuid, begin_snapshot, end_snapshot, schema_id, table_name, path, path_is_relative)
            VALUES (:tableId, :tableUuid, :beginSnapshot, :endSnapshot, :schemaId, :tableName, :path, :pathIsRelative)
            """)
    boolean createTable(@BindBean DuckLakeTable duckLakeTable);

    /**
     * Get all column information for a specific snapshot and table ID
     * 
     * @param snapshotId snapshot ID
     * @param tableId    table ID
     * @return list of column information
     */
    @SqlQuery("""
            SELECT column_id, begin_snapshot, end_snapshot, table_id, column_order, column_name, column_type, initial_default, default_value, nulls_allowed, parent_column
            FROM ducklake_column
            WHERE table_id = :tableId
            AND begin_snapshot <= :snapshotId
            AND (end_snapshot > :snapshotId OR end_snapshot IS NULL)
            ORDER BY column_order ASC
            """)
    List<DuckLakeColumn> getTableColumns(@Bind("snapshotId") long snapshotId, @Bind("tableId") long tableId);

    /**
     * Get all data file information for a specific snapshot and table ID
     * 
     * @param snapshotId snapshot ID
     * @param tableId    table ID
     * @return list of data file information
     */
    @SqlQuery("""
            SELECT data_file_id, table_id, begin_snapshot, end_snapshot, file_order, path, path_is_relative, file_format, record_count, file_size_bytes, footer_size, row_id_start, partition_id, encryption_key, partial_file_info
            FROM ducklake_data_file
            WHERE table_id = :tableId
            AND begin_snapshot <= :snapshotId
            AND (end_snapshot > :snapshotId OR end_snapshot IS NULL)
            """)
    List<DuckLakeDataFile> getTableDataFiles(@Bind("snapshotId") long snapshotId, @Bind("tableId") long tableId);

    /**
     * Get all file column statistics for a specific table and data file at a
     * snapshot
     * 
     * @param snapshotId snapshot ID
     * @param tableId    table ID
     * @param dataFileId data file ID
     * @return list of file column statistics
     */
    @SqlQuery("""
            SELECT data_file_id, table_id, column_id, column_size_bytes, value_count, null_count, min_value, max_value, contains_nan
            FROM ducklake_file_column_statistics
            WHERE table_id = :tableId AND data_file_id = :dataFileId
            """)
    List<DuckLakeFileColumnStatistics> getFileColumnStatistics(@Bind("tableId") long tableId, @Bind("dataFileId") long dataFileId);

    /**
     * Get all delete files for a specific table at a snapshot
     * 
     * @param snapshotId snapshot ID
     * @param tableId    table ID
     * @return list of delete files
     */
    @SqlQuery("""
            SELECT delete_file_id, table_id, begin_snapshot, end_snapshot, data_file_id, path, path_is_relative, format, delete_count, file_size_bytes, footer_size, encryption_key
            FROM ducklake_delete_file
            WHERE table_id = :tableId
            AND begin_snapshot <= :snapshotId
            AND (end_snapshot > :snapshotId OR end_snapshot IS NULL)
            """)
    List<DuckLakeDeleteFile> getTableDeleteFiles(@Bind("snapshotId") long snapshotId, @Bind("tableId") long tableId);

    /**
     * Get all table column statistics for a specific table
     * 
     * @param snapshotId snapshot ID
     * @param tableId    table ID
     * @return list of table column statistics
     */
    @SqlQuery("""
            SELECT table_id, column_id, contains_null, contains_nan, min_value, max_value
            FROM ducklake_table_column_stats
            WHERE table_id = :tableId
            """)
    List<DuckLakeTableColumnStats> getTableColumnStats(@Bind("tableId") long tableId);

    /**
     * Get all table statistics for a specific table at a snapshot
     * 
     * @param tableId    table ID
     * @return table statistics
     */
    @SqlQuery("""
            SELECT table_id, record_count, next_row_id, file_size_bytes
            FROM ducklake_table_stats
            WHERE table_id = :tableId
            """)
    DuckLakeTableStats getTableStats(@Bind("tableId") long tableId);

    /**
     * Get partition information for a specific table at a snapshot
     * 
     * @param snapshotId snapshot ID
     * @param tableId    table ID
     * @return partition information
     */
    @SqlQuery("""
            SELECT partition_id, table_id, begin_snapshot, end_snapshot
            FROM ducklake_partition_info
            WHERE table_id = :tableId
            AND begin_snapshot <= :snapshotId
            AND (end_snapshot > :snapshotId OR end_snapshot IS NULL)
            """)
    DuckLakePartitionInfo getPartitionInfo(@Bind("snapshotId") long snapshotId, @Bind("tableId") long tableId);

    /**
     * Get all partition columns for a specific partition
     * 
     * @param partitionId partition ID
     * @param tableId     table ID
     * @return list of partition columns
     */
    @SqlQuery("""
            SELECT partition_id, table_id, partition_key_index, column_id, transform
            FROM ducklake_partition_column
            WHERE partition_id = :partitionId
            AND table_id = :tableId
            """)
    List<DuckLakePartitionColumn> getPartitionColumns(@Bind("tableId") long tableId, @Bind("partitionId") long partitionId);
}
