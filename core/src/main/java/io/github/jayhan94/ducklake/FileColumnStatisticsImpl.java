package io.github.jayhan94.ducklake;

import java.util.Optional;

import io.github.jayhan94.ducklake.api.FileColumnStatistics;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

public class FileColumnStatisticsImpl implements FileColumnStatistics {
    private final long dataFileId;
    private final long tableId;
    private final long columnId;
    private final Long columnSizeBytes;
    private final Long valueCount;
    private final Long nullCount;
    private final Long nanCount;
    private final String minValue;
    private final String maxValue;
    private final Boolean containsNaN;

    public FileColumnStatisticsImpl(
            long dataFileId,
            long tableId,
            long columnId,
            Long columnSizeBytes,
            Long valueCount,
            Long nullCount,
            Long nanCount,
            String minValue,
            String maxValue,
            Boolean containsNaN) {
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
    public Optional<Long> valueCount() {
        return Optional.ofNullable(valueCount);
    }

    @Override
    public Optional<Long> nullCount() {
        return Optional.ofNullable(nullCount);
    }

    @Override
    public Optional<Long> nanCount() {
        return Optional.ofNullable(nanCount);
    }

    @Override
    public Optional<String> minValue() {
        return Optional.ofNullable(minValue);
    }

    @Override
    public Optional<String> maxValue() {
        return Optional.ofNullable(maxValue);
    }

    @Override
    public Optional<Boolean> containsNaN() {
        return Optional.ofNullable(containsNaN);
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}