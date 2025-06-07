package io.github.jayhan94.ducklake.api;

public interface Snapshot {
    long id();

    long createTimestamp();

    DataFiles dataFiles();

    DeleteFiles deleteFiles();
}
