package io.github.jayhan94.ducklake.api;

public interface Scan {
    Scan withSnapshot(long snapshotId);

    Snapshot snapshot();

    Table table();

    DataFiles dataFiles();

    DeleteFiles deleteFiles();
}
