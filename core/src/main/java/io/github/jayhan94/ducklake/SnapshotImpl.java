package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.Snapshot;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

import java.io.Serializable;

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
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
