package io.github.jayhan94.ducklake.api;

import java.util.List;

import io.github.jayhan94.ducklake.api.Table.TableIdentifier;

public interface Catalog {
    /**
     * Get the current snapshot
     * 
     * @return the current snapshot
     */
    default Snapshot getCurrentSnapshot() {
        return getSnapshot(null);
    }

    /**
     * Get a snapshot by ID
     * If the snapshotId is null, the latest snapshot will be returned.
     * 
     * @param snapshotId snapshot ID
     * @return the snapshot
     */
    Snapshot getSnapshot(Long snapshotId);

    /**
     * Get a schema by name and snapshot ID
     * 
     * @param snapshotId snapshot ID
     * @param schemaName schema name
     * @return the schema
     */
    Schema getSchema(Long snapshotId, String schemaName);

    /**
     * List all schemas in the catalog at the given snapshot
     * 
     * @param snapshotId snapshot ID
     * @return list of schemas
     */
    List<Schema> listSchemas(Long snapshotId);

    /**
     * List tables by schema name and snapshot ID
     * 
     * @param snapshotId
     * @param schemaName
     * @return
     */
    List<Table> listTables(Long snapshotId, String schemaName);

    /**
     * Get a table by name and snapshot ID
     * If the tableName doesn't have a specific schema, the default schema 'main'
     * will be used.
     * 
     * @param snapshotId snapshot ID
     * @param tableName  table name
     * @return the table
     */
    Table getTable(Long snapshotId, TableIdentifier tableIdentifier);

    /**
     * Create a table in the catalog
     * 
     * @param schemaName schema name
     * @param tableName  table name
     * @param schema     table schema
     * @return the created table
     */
    Table createTable(String schemaName, TableIdentifier tableIdentifier, TableSchema schema);

    /**
     * Get a table schema by table name and snapshot ID
     * 
     * @param snapshotId snapshot ID
     * @param tableName  table name
     * @return the schema
     */
    TableSchema getTableSchema(long snapshotId, long tableId);
}
