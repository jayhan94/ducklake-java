package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.FileColumnStatistics;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

public class FileColumnStatisticsImpl implements FileColumnStatistics {
    private final long dataFileId;
    private final long tableId;
    private final long columnId;
    private final long columnSizeBytes;
    private final long valueCount;
    private final long nullCount;
    private final long nanCount;
    private final String minValue;
    private final String maxValue;
    private final boolean containsNaN;

    public FileColumnStatisticsImpl(
            long dataFileId,
            long tableId,
            long columnId,
            long columnSizeBytes,
            long valueCount,
            long nullCount,
            long nanCount,
            String minValue,
            String maxValue,
            boolean containsNaN) {
        this.dataFileId = dataFileId;
        this.tableId = tableId;
        this.columnId = columnId;
        this.columnSizeBytes = columnSizeBytes;
        this.valueCount = valueCount;
        this.nullCount = nullCount;
        this.nanCount = nanCount;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.containsNaN = containsNaN;
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
    public long columnId() {
        return columnId;
    }

    @Override
    public long columnSizeBytes() {
        return columnSizeBytes;
    }

    @Override
    public long valueCount() {
        return valueCount;
    }

    @Override
    public long nullCount() {
        return nullCount;
    }

    @Override
    public long nanCount() {
        return nanCount;
    }

    @Override
    public String minValue() {
        return minValue;
    }

    @Override
    public String maxValue() {
        return maxValue;
    }

    @Override
    public boolean containsNaN() {
        return containsNaN;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}