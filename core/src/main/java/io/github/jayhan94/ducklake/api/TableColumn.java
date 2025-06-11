package io.github.jayhan94.ducklake.api;

import io.github.jayhan94.ducklake.datatype.DataType;

public interface TableColumn {
    long columnId();

    String columnName();

    DataType columnType();

    boolean isNullable();

    Object defaultValue();

    Object initialDefault();

    TableColumnStatistics columnStatistics();
}