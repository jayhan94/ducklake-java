package io.github.jayhan94.ducklake;

import java.io.Serializable;
import java.util.List;

import io.github.jayhan94.ducklake.api.DataFile;
import io.github.jayhan94.ducklake.api.Scan;
import io.github.jayhan94.ducklake.api.Schema;
import io.github.jayhan94.ducklake.api.Snapshot;
import io.github.jayhan94.ducklake.api.Table;
import io.github.jayhan94.ducklake.api.TableSchema;
import io.github.jayhan94.ducklake.api.TableStatistics;
import lombok.ToString;

@ToString
public class TableImpl implements Table, Serializable {
    private final Snapshot snapshot;
    private final Schema schema;
    private final long tableId;
    private final String tableName;
    private final TableSchema tableSchema;
    private final List<DataFile> dataFiles;
    private final TableStatistics tableStatistics;

    public TableImpl(
            Snapshot snapshot,
            Schema schema,
            long tableId,
            String tableName,
            TableSchema tableSchema,
            List<DataFile> dataFiles,
            TableStatistics tableStatistics) {
        this.snapshot = snapshot;
        this.schema = schema;
        this.tableId = tableId;
        this.tableName = tableName;
        this.tableSchema = tableSchema;
        this.dataFiles = dataFiles;
        this.tableStatistics = tableStatistics;
    }

    @Override
    public long tableId() {
        return tableId;
    }

    @Override
    public String tableName() {
        return tableName;
    }

    @Override
    public TableSchema tableSchema() {
        return tableSchema;
    }

    @Override
    public List<DataFile> dataFiles() {
        return dataFiles;
    }

    @Override
    public Snapshot snapshot() {
        return snapshot;
    }

    @Override
    public Schema schema() {
        return schema;
    }

    @Override
    public Scan scan() {
        return null;
    }

    @Override
    public TableStatistics tableStatistics() {
        return tableStatistics;
    }
}
