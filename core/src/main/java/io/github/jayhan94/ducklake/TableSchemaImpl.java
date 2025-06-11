package io.github.jayhan94.ducklake;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import io.github.jayhan94.ducklake.api.TableColumn;
import io.github.jayhan94.ducklake.api.TableSchema;
import lombok.ToString;

@ToString
public class TableSchemaImpl implements TableSchema, Serializable {
    private final List<TableColumn> columns;

    public TableSchemaImpl(List<TableColumn> columns) {
        this.columns = columns;
    }

    @Override
    public List<TableColumn> columns() {
        return columns;
    }

    @Override
    public String prettyString() {
        return columns.stream()
                .map(column -> column.columnName() + ":" + column.columnType())
                .collect(Collectors.joining(", "));
    }
}
