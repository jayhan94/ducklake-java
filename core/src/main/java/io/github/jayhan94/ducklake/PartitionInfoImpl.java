package io.github.jayhan94.ducklake;

import java.util.List;

import io.github.jayhan94.ducklake.api.PartitionColumn;
import io.github.jayhan94.ducklake.api.PartitionInfo;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

public class PartitionInfoImpl implements PartitionInfo {
    private final long partitionId;
    private final long tableId;
    private final List<PartitionColumn> partitionColumns;

    public PartitionInfoImpl(long partitionId, long tableId, List<PartitionColumn> partitionColumns) {
        this.partitionId = partitionId;
        this.tableId = tableId;
        this.partitionColumns = partitionColumns;
    }

    @Override
    public long partitionId() {
        return partitionId;
    }

    @Override
    public long tableId() {
        return tableId;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    @Override
    public List<PartitionColumn> partitionColumns() {
        return partitionColumns;
    }

    public static class PartitionColumnImpl implements PartitionColumn {
        private final long columnId;
        private final long partitionKeyIndex;
        private final String transform;

        public PartitionColumnImpl(long columnId, long partitionKeyIndex, String transform) {
            this.columnId = columnId;
            this.partitionKeyIndex = partitionKeyIndex;
            this.transform = transform;
        }

        @Override
        public long columnId() {
            return columnId;
        }

        @Override
        public long partitionKeyIndex() {
            return partitionKeyIndex;
        }

        @Override
        public String transform() {
            return transform;
        }

        @Override
        public String toString() {
            return JsonUtils.toJson(this);
        }
    }
}
