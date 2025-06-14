package io.github.jayhan94.ducklake.catalog.duckdb.sql;

import java.util.List;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import io.github.jayhan94.ducklake.entity.DuckLakeColumn;
import io.github.jayhan94.ducklake.entity.DuckLakeDataFile;
import io.github.jayhan94.ducklake.entity.DuckLakeDeleteFile;
import io.github.jayhan94.ducklake.entity.DuckLakeSchema;
import io.github.jayhan94.ducklake.entity.DuckLakeSnapshot;
import io.github.jayhan94.ducklake.entity.DuckLakeTable;

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
            SELECT schema_id, schema_name
            FROM ducklake_schema
            WHERE snapshot_id >= begin_snapshot
                    AND (snapshot_id < end_snapshot OR end_snapshot IS NULL)
            """)
    List<DuckLakeSchema> listSchemas(@Bind("snapshotId") long snapshotId);

    @SqlQuery("""
            SELECT schema_id, schema_name
            FROM ducklake_schema
            WHERE schema_name = :schemaName
            AND begin_snapshot <= :snapshotId
            AND (end_snapshot > :snapshotId OR end_snapshot IS NULL)
            """)
    DuckLakeSchema getSchema(@Bind("snapshotId") long snapshotId, @Bind("schemaName") String schemaName);

    /**
     * Get table information by table name and snapshot ID
     * 
     * @param table_name table name
     * @param snapshotId snapshot ID
     * @return table information
     */
    @SqlQuery("""
            SELECT table_id, table_uuid, begin_snapshot, end_snapshot, schema_id, table_name
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
            INSERT INTO ducklake_table(table_id, table_uuid, begin_snapshot, end_snapshot, schema_id, table_name)
            VALUES (:tableId, :tableUuid, :beginSnapshot, :endSnapshot, :schemaId, :tableName)
            """)
    boolean createTable(@BindBean DuckLakeTable duckLakeTable);

    /**
     * Get all column information for a specific snapshot and table ID
     * 
     * @param snapshotId snapshot ID
     * @param tableId table ID
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
     * @param tableId table ID
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
     * Get all delete files for a specific table at a snapshot
     * 
     * @param snapshotId snapshot ID
     * @param tableId table ID
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
}
