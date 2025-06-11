package io.github.jayhan94.ducklake.api;

import java.util.List;

public interface TableStatistics {
    long rowCount();

    long totalSizeBytes();

    long nextRowId();

    List<TableColumnStatistics> columnStatistics();

    List<DataFileStatistics> dataFileStatistics();
}
