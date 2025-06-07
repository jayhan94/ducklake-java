package io.github.jayhan94.ducklake.api;

import io.github.jayhan94.ducklake.type.Schema;

public interface Catalog {
    Table getTable(String tableName);

    Table createTable(String tableName, Schema schema);

    Table updateTableSchema(String tableName, Schema schema);

    Table deleteTable(String tableName);
}
