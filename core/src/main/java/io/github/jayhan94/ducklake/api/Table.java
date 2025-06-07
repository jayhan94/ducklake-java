package io.github.jayhan94.ducklake.api;

import io.github.jayhan94.ducklake.type.Schema;

public interface Table {
    String id();

    String tableName();

    Schema schema();

    Snapshot currentSnapshot();

    Snapshot snapshot(long snapshotId);

    Scan scan(long snapshotId);

    Statistics statistics(long snapshotId);

    Append newAppend(DataFile dataFile);

    void refresh();
}
