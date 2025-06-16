package io.github.jayhan94.ducklake;

import java.io.Serializable;
import java.util.Optional;

import io.github.jayhan94.ducklake.api.TableColumn;
import io.github.jayhan94.ducklake.datatype.DataType;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

public class TableColumnImpl implements TableColumn, Serializable {
    private final long columnId;
    private final String columnName;
    private final DataType columnType;
    private final Boolean isNullable;
    private final Object defaultValue;
    private final Object initialDefault;

    public TableColumnImpl(
            long columnId,
            String columnName,
            DataType columnType,
            Boolean isNullable,
            Object defaultValue,
            Object initialDefault) {
        this.columnId = columnId;
        this.columnName = columnName;
        this.columnType = columnType;
        this.isNullable = isNullable;
        this.defaultValue = defaultValue;
        this.initialDefault = initialDefault;
    }

    @Override
    public long columnId() {
        return columnId;
    }

    @Override
    public String columnName() {
        return columnName;
    }

    @Override
    public DataType columnType() {
        return columnType;
    }

    @Override
    public boolean isNullable() {
        return isNullable == null || isNullable;
    }

    @Override
    public Optional<Object> defaultValue() {
        return Optional.ofNullable(defaultValue);
    }

    @Override
    public Optional<Object> initialDefault() {
        return Optional.ofNullable(initialDefault);
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
