package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.TableColumnStatistics;
import io.github.jayhan94.ducklake.api.TableStatistics;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

import java.util.List;

public class TableStatisticsImpl implements TableStatistics {
    private final long rowCount;
    private final long totalSizeBytes;
    private final long nextRowId;
    private final List<TableColumnStatistics> columnStatistics;

    public TableStatisticsImpl(
            long rowCount,
            long totalSizeBytes,
            long nextRowId,
            List<TableColumnStatistics> columnStatistics) {
        this.rowCount = rowCount;
        this.totalSizeBytes = totalSizeBytes;
        this.nextRowId = nextRowId;
        this.columnStatistics = columnStatistics;
    }

    @Override
    public long rowCount() {
        return rowCount;
    }

    @Override
    public long totalSizeBytes() {
        return totalSizeBytes;
    }

    @Override
    public long nextRowId() {
        return nextRowId;
    }

    @Override
    public List<TableColumnStatistics> columnStatistics() {
        return columnStatistics;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}