package io.github.jayhan94.ducklake;

import java.util.Optional;

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

    @Override
    public long tableId() {
        return tableId;
    }

    @Override
    public long columnId() {
        return columnId;
    }

    @Override
    public Optional<Boolean> containsNull() {
        return Optional.ofNullable(containsNull);
    }

    @Override
    public Optional<Boolean> containsNaN() {
        return Optional.ofNullable(containsNaN);
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
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
