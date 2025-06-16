package io.github.jayhan94.ducklake;

import java.util.Optional;

import io.github.jayhan94.ducklake.api.DataFile;
import io.github.jayhan94.ducklake.api.DataFileStatistics;
import io.github.jayhan94.ducklake.api.DeleteFile;
import io.github.jayhan94.ducklake.api.FileFormat;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

public class DataFileImpl implements DataFile {
    private final Long dataFileId;
    private final Long tableId;
    private final DeleteFile deleteFile;
    private final DataFileStatistics dataFileStatistics;
    private final String path;
    private final FileFormat fileFormat;
    private final Long startRowId;
    private final Long fileOrder;

    public DataFileImpl(
            long dataFileId,
            long tableId,
            DeleteFile deleteFile,
            String path,
            FileFormat fileFormat,
            Long startRowId,
            Long fileOrder,
            DataFileStatistics dataFileStatistics) {
        this.dataFileId = dataFileId;
        this.tableId = tableId;
        this.deleteFile = deleteFile;
        this.path = path;
        this.fileFormat = fileFormat;
        this.startRowId = startRowId;
        this.fileOrder = fileOrder;
        this.dataFileStatistics = dataFileStatistics;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
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
    public Optional<DeleteFile> deleteFile() {
        return Optional.ofNullable(deleteFile);
    }

    @Override
    public Long startRowId() {
        return startRowId;
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
    public Optional<Long> fileOrder() {
        return Optional.ofNullable(fileOrder);
    }

    @Override
    public DataFileStatistics dataFileStatistics() {
        return dataFileStatistics;
    }

}