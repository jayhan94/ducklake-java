package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.Snapshot;
import lombok.ToString;

import java.io.Serializable;

import io.github.jayhan94.ducklake.api.DataFiles;
import io.github.jayhan94.ducklake.api.DeleteFiles;

@ToString
public class SnapshotImpl implements Snapshot, Serializable {
    private final long snapshotId;
    private final long timestamp;
    private final long schemaVersion;

    public SnapshotImpl(long snapshotId, long timestamp, long schemaVersion) {
        this.snapshotId = snapshotId;
        this.timestamp = timestamp;
        this.schemaVersion = schemaVersion;
    }

    @Override
    public long id() {
        return snapshotId;
    }

    @Override
    public long timestamp() {
        return timestamp;
    }

    @Override
    public long schemaVersion() {
        return schemaVersion;
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
