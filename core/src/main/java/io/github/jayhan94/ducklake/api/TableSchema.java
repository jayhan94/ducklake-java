package io.github.jayhan94.ducklake.api;

import java.util.List;

public interface TableSchema extends PrettyPrint {
    List<TableColumn> columns();
}
