package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.DataFile;
import io.github.jayhan94.ducklake.api.DataFileStatistics;
import io.github.jayhan94.ducklake.api.DeleteFile;
import io.github.jayhan94.ducklake.api.FileFormat;

public class DataFileImpl implements DataFile {
    private final long dataFileId;
    private final long tableId;
    private final DeleteFile deleteFile;
    private final DataFileStatistics dataFileStatistics;
    private final String path;
    private final FileFormat fileFormat;
    private final long rowCount;
    private final long fileSizeBytes;
    private final long footerSizeBytes;
    private final long startRowId;
    private final long fileOrder;

    public DataFileImpl(
            long dataFileId,
            long tableId,
            DeleteFile deleteFile,
            DataFileStatistics dataFileStatistics,
            String path,
            FileFormat fileFormat,
            long rowCount,
            long fileSizeBytes,
            long footerSizeBytes,
            long startRowId,
            long fileOrder) {
        this.dataFileId = dataFileId;
        this.tableId = tableId;
        this.deleteFile = deleteFile;
        this.dataFileStatistics = dataFileStatistics;
        this.path = path;
        this.fileFormat = fileFormat;
        this.rowCount = rowCount;
        this.fileSizeBytes = fileSizeBytes;
        this.footerSizeBytes = footerSizeBytes;
        this.startRowId = startRowId;
        this.fileOrder = fileOrder;
    }

    @Override
    public long dataFileId() {
        return dataFileId;
    }

    @Override
    public long tableId() {
        return tableId;
    }

    @Override
    public DeleteFile deleteFile() {
        return deleteFile;
    }

    @Override
    public DataFileStatistics dataFileStatistics() {
        return dataFileStatistics;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public FileFormat fileFormat() {
        return fileFormat;
    }

    @Override
    public long rowCount() {
        return rowCount;
    }

    @Override
    public long fileSizeBytes() {
        return fileSizeBytes;
    }

    @Override
    public long footerSizeBytes() {
        return footerSizeBytes;
    }

    @Override
    public long startRowId() {
        return startRowId;
    }

    @Override
    public long fileOrder() {
        return fileOrder;
    }

}