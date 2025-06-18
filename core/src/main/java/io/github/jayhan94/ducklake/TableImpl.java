package io.github.jayhan94.ducklake;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import io.github.jayhan94.ducklake.api.DataFile;
import io.github.jayhan94.ducklake.api.PartitionInfo;
import io.github.jayhan94.ducklake.api.Scan;
import io.github.jayhan94.ducklake.api.Schema;
import io.github.jayhan94.ducklake.api.Snapshot;
import io.github.jayhan94.ducklake.api.Table;
import io.github.jayhan94.ducklake.api.TableSchema;
import io.github.jayhan94.ducklake.api.TableStatistics;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

public class TableImpl implements Table, Serializable {
    private transient final Snapshot snapshot;
    private transient final Schema schema;
    private final long tableId;
    private final String tableName;
    private final TableSchema tableSchema;
    private final List<DataFile> dataFiles;
    private final Optional<PartitionInfo> partitionInfo;
    private final Optional<TableStatistics> tableStatistics;

    public TableImpl(
            Snapshot snapshot,
            Schema schema,
            long tableId,
            String tableName,
            TableSchema tableSchema,
            List<DataFile> dataFiles,
            Optional<PartitionInfo> partitionInfo,
            Optional<TableStatistics> tableStatistics) {
        this.snapshot = snapshot;
        this.schema = schema;
        this.tableId = tableId;
        this.tableName = tableName;
        this.tableSchema = tableSchema;
        this.dataFiles = dataFiles;
        this.partitionInfo = partitionInfo;
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
    public Optional<PartitionInfo> partitionInfo() {
        return partitionInfo;
    }

    @Override
    public Optional<TableStatistics> tableStatistics() {
        return tableStatistics;
    }

    @Override
    public Scan scan() {
        return null;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
