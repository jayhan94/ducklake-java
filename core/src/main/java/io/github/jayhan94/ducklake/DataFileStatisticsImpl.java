package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.DataFileStatistics;
import io.github.jayhan94.ducklake.api.FileColumnStatistics;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

import java.util.List;

public class DataFileStatisticsImpl implements DataFileStatistics {
    private final long recordCount;
    private final long fileSizeBytes;
    private final long footerSizeBytes;
    private final long startRowId;
    private final List<FileColumnStatistics> fileColumnStatistics;

    public DataFileStatisticsImpl(
            long recordCount,
            long fileSizeBytes,
            long footerSizeBytes,
            long startRowId,
            List<FileColumnStatistics> fileColumnStatistics) {
        this.recordCount = recordCount;
        this.fileSizeBytes = fileSizeBytes;
        this.footerSizeBytes = footerSizeBytes;
        this.startRowId = startRowId;
        this.fileColumnStatistics = fileColumnStatistics;
    }

    @Override
    public long recordCount() {
        return recordCount;
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
    public List<FileColumnStatistics> fileColumnStatistics() {
        return fileColumnStatistics;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}