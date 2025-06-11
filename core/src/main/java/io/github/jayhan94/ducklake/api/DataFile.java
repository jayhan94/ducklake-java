package io.github.jayhan94.ducklake.api;

public interface DataFile {
    long dataFileId();

    long tableId();

    long snapshotId();

    DataFileStatistics dataFileStatistics();
}
