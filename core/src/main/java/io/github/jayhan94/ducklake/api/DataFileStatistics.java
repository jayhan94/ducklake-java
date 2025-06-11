package io.github.jayhan94.ducklake.api;

import java.util.List;

public interface DataFileStatistics {
    // The number of records in the data file
    long recordCount();

    // The size of the data file in bytes
    long fileSizeBytes();

    // The size of the file metadata footer, in the case of Parquet the Thrift data.
    // This is an optimization that allows for faster reading of the file.
    long footerSizeBytes();

    // The first logical row id in the file. (Every row has a unique row-id that is
    // maintained.)
    long startRowId();

    // Column-level statistics for a single data file.
    List<FileColumnStatistics> fileColumnStatistics();

}
