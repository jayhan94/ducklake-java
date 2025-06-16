package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.TableColumnStatistics;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

public class TableColumnStatisticsImpl implements TableColumnStatistics {
    private final long tableId;
    private final long columnId;
    private final boolean containsNull;
    private final boolean containsNaN;
    private final String minValue;
    private final String maxValue;

    public TableColumnStatisticsImpl(
            long tableId,
            long columnId,
            boolean containsNull,
            boolean containsNaN,
            String minValue,
            String maxValue) {
        this.tableId = tableId;
        this.columnId = columnId;
        this.containsNull = containsNull;
        this.containsNaN = containsNaN;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public long tableId() {
        return tableId;
    }

    public long columnId() {
        return columnId;
    }

    @Override
    public boolean containsNull() {
        return containsNull;
    }

    @Override
    public boolean containsNaN() {
        return containsNaN;
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
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
