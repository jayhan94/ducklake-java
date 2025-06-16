package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.DataFile;
import io.github.jayhan94.ducklake.api.DataFileStatistics;
import io.github.jayhan94.ducklake.api.DeleteFile;
import io.github.jayhan94.ducklake.api.FileFormat;

public class DataFileImpl implements DataFile {
    private final Long dataFileId;
    private final Long tableId;
    private final DeleteFile deleteFile;
    private final DataFileStatistics dataFileStatistics;
    private final String path;
    private final FileFormat fileFormat;
    private final Long rowCount;
    private final Long fileSizeBytes;
    private final Long footerSizeBytes;
    private final Long startRowId;
    private final Long fileOrder;

    public DataFileImpl(
            long dataFileId,
            long tableId,
            DeleteFile deleteFile,
            DataFileStatistics dataFileStatistics,
            String path,
            FileFormat fileFormat,
            Long rowCount,
            Long fileSizeBytes,
            Long footerSizeBytes,
            Long startRowId,
            Long fileOrder) {
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
    public String toString() {
        return prettyString();
    }

    @Override
    public Long dataFileId() {
        return dataFileId;
    }

    @Override
    public Long tableId() {
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
    public Long rowCount() {
        return rowCount;
    }

    @Override
    public Long fileSizeBytes() {
        return fileSizeBytes;
    }

    @Override
    public Long footerSizeBytes() {
        return footerSizeBytes;
    }

    @Override
    public Long startRowId() {
        return startRowId;
    }

    @Override
    public Long fileOrder() {
        return fileOrder;
    }

}