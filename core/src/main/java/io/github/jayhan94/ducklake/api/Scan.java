package io.github.jayhan94.ducklake.api;

import java.util.List;

public interface Scan {
    Scan withSnapshot(long snapshotId);

    Snapshot snapshot();

    Table table();

    List<DataFile> dataFiles();
}
