package io.github.jayhan94.ducklake.spark;

import org.apache.spark.sql.connector.catalog.Table;
import org.apache.spark.sql.connector.catalog.TableProvider;
import org.apache.spark.sql.connector.expressions.Transform;
import org.apache.spark.sql.sources.DataSourceRegister;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.util.CaseInsensitiveStringMap;

import java.util.Map;

/**
 * DuckLake DataSourceV2 implementation providing entry point for Spark to access DuckLake tables
 */
public class DuckLakeDataSource implements TableProvider, DataSourceRegister {

    @Override
    public String shortName() {
        return "ducklake";
    }

    @Override
    public StructType inferSchema(CaseInsensitiveStringMap options) {
        // For DuckLake, schema is always explicitly obtained from table metadata
        throw new UnsupportedOperationException("DuckLake table schema must be obtained from table metadata, schema inference is not supported");
    }

    @Override
    public Table getTable(
            StructType schema,
            Transform[] partitioning,
            Map<String, String> properties) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean supportsExternalMetadata() {
        return true;
    }
}