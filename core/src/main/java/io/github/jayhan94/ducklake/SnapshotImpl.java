package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.DataFiles;
import io.github.jayhan94.ducklake.api.DeleteFiles;
import io.github.jayhan94.ducklake.api.Snapshot;
import io.github.jayhan94.ducklake.catalog.duckdb.DuckDB;
import io.github.jayhan94.ducklake.entity.DuckLakeSnapshot;

public class SnapshotImpl implements Snapshot {
    private final DuckLakeSnapshot duckLakeSnapshot;
    private final DuckDB duckdb;

    public SnapshotImpl(DuckDB duckdb,
                        DuckLakeSnapshot duckLakeSnapshot) {
        this.duckLakeSnapshot = duckLakeSnapshot;
        this.duckdb = duckdb;
    }

    @Override
    public long id() {
        return duckLakeSnapshot.getSnapshotId();
    }

    @Override
    public long timestamp() {
        return duckLakeSnapshot.getSnapshotTime().toEpochMilli();
    }

    @Override
    public long schemaVersion() {
        return duckLakeSnapshot.getSchemaVersion();
    }

    @Override
    public DataFiles tableDataFiles(long tableId) {
        return null;
    }

    @Override
    public DeleteFiles tableDeleteFiles(long tableId) {
        return null;
    }
}
