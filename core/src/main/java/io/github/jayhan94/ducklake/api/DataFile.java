package io.github.jayhan94.ducklake.api;

public interface DataFile {
    Long dataFileId();

    Long tableId();

    DeleteFile deleteFile();

    DataFileStatistics dataFileStatistics();

    String path();

    FileFormat fileFormat();

    Long rowCount();

    Long fileSizeBytes();

    Long footerSizeBytes();

    Long startRowId();

    Long fileOrder();

    // TODO PartitionInfo partitionInfo();
}
