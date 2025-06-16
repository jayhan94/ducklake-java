package io.github.jayhan94.ducklake;

import java.io.Serializable;

import io.github.jayhan94.ducklake.api.Schema;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

public class SchemaImpl implements Schema, Serializable {
    private final long schemaId;
    private final String schemaName;

    public SchemaImpl(long schemaId, String schemaName) {
        this.schemaId = schemaId;
        this.schemaName = schemaName;
    }

    @Override
    public long schemaId() {
        return schemaId;
    }

    @Override
    public String schemaName() {
        return schemaName;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}