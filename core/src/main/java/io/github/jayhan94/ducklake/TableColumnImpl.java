package io.github.jayhan94.ducklake;

import java.io.Serializable;

import io.github.jayhan94.ducklake.api.TableColumn;
import io.github.jayhan94.ducklake.api.TableColumnStatistics;
import io.github.jayhan94.ducklake.datatype.DataType;
import lombok.ToString;

@ToString
public class TableColumnImpl implements TableColumn, Serializable {
    private final long columnId;
    private final String columnName;
    private final DataType columnType;
    private final Boolean isNullable;
    private final Object defaultValue;
    private final Object initialDefault;
    private final TableColumnStatistics columnStatistics;

    public TableColumnImpl(long columnId, String columnName, DataType columnType, Boolean isNullable,
            Object defaultValue, Object initialDefault, TableColumnStatistics columnStatistics) {
        this.columnId = columnId;
        this.columnName = columnName;
        this.columnType = columnType;
        this.isNullable = isNullable;
        this.defaultValue = defaultValue;
        this.initialDefault = initialDefault;
        this.columnStatistics = columnStatistics;
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
    public Object defaultValue() {
        return defaultValue;
    }

    @Override
    public Object initialDefault() {
        return initialDefault;
    }

    @Override
    public TableColumnStatistics columnStatistics() {
        return columnStatistics;
    }


}
