package io.github.jayhan94.ducklake.api;

import io.github.jayhan94.ducklake.TableSchema;

/**
 * Table is a view of the table at a specific snapshot
 */
public interface Table {
    Long id();

    String tableName();

    TableSchema tableSchema();

    // get the snapshot of the table
    Snapshot snapshot();

    Schema schema();

    Scan scan();

    Statistics statistics();
}
