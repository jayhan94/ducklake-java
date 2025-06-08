package io.github.jayhan94.ducklake.api;

import java.util.List;

import io.github.jayhan94.ducklake.TableSchema;

public interface Catalog {
    // pass null if you want to get the latest snapshot
    Snapshot getSnapshot(Long snapshotId);

    List<Schema> listSchemas(Long snapshotId);

    Table getTable(Long snapshotId, Long schemaId, String tableName);

    Table createTable(String schemaName, String tableName, TableSchema schema);
}
