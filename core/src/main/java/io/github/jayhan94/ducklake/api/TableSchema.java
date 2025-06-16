package io.github.jayhan94.ducklake.api;

import java.util.List;

/**
 * Represents the complete schema of a table, defined as a list of columns.
 */
public interface TableSchema {
    /**
     * Gets the list of columns that make up the schema.
     *
     * @return a list of {@link TableColumn}
     */
    List<TableColumn> columns();
}
