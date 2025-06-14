package io.github.jayhan94.ducklake.api;

public interface DataFile {
    long dataFileId();

    long tableId();

    DeleteFile deleteFile();

    DataFileStatistics dataFileStatistics();

    String path();

    FileFormat fileFormat();

    long rowCount();

    long fileSizeBytes();

    long footerSizeBytes();

    long startRowId();

    long fileOrder();

    // TODO PartitionInfo partitionInfo();
}
