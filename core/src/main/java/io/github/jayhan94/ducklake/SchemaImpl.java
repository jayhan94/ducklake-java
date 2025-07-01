package io.github.jayhan94.ducklake;

import java.io.Serializable;

import io.github.jayhan94.ducklake.api.Schema;
import io.github.jayhan94.ducklake.api.PathInfo;
import io.github.jayhan94.ducklake.util.json.JsonUtils;

public class SchemaImpl implements Schema, Serializable {
    private final long schemaId;
    private final String schemaName;
    private final PathInfo pathInfo;

    public SchemaImpl(long schemaId, String schemaName, PathInfo pathInfo) {
        this.schemaId = schemaId;
        this.schemaName = schemaName;
        this.pathInfo = pathInfo;
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
    public PathInfo pathInfo() {
        return pathInfo;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}