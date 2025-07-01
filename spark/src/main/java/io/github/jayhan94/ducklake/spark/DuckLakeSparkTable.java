package io.github.jayhan94.ducklake.spark;

import java.util.Map;
import java.util.Set;

import org.apache.spark.sql.connector.catalog.SupportsRead;
import org.apache.spark.sql.connector.catalog.TableCapability;
import org.apache.spark.sql.connector.expressions.Transform;
import org.apache.spark.sql.connector.read.ScanBuilder;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.util.CaseInsensitiveStringMap;

/**
 * Simplified DuckLake Spark Table implementation
 */
public class DuckLakeSparkTable implements SupportsRead {

    @Override
    public String name() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public StructType schema() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Map<String, String> properties() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Set<TableCapability> capabilities() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Transform[] partitioning() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ScanBuilder newScanBuilder(CaseInsensitiveStringMap options) {
        throw new UnsupportedOperationException("Not implemented");
    }
}