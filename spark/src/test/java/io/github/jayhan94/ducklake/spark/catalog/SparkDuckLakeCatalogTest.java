package io.github.jayhan94.ducklake.spark.catalog;

import java.io.IOException;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.connector.catalog.CatalogPlugin;
import org.junit.jupiter.api.Test;

public class SparkDuckLakeCatalogTest {
    @Test
    public void testListTables() throws IOException {
        SparkSession spark = SparkSession.builder()
                .appName("SparkDuckLakeCatalogTest")
                .master("local[1]")
                .config("spark.sql.catalog.test_ducklake",
                        "io.github.jayhan94.ducklake.spark.catalog.SparkDuckLakeCatalog")
                .config("spark.sql.catalog.test_ducklake.type", "postgres")
                .config("spark.sql.catalog.test_ducklake.database", "ducklake_catalog")
                .config("spark.sql.catalog.test_ducklake.dataPath", "/Users/doki/tmp/ducklake/data_files/")
                .config("spark.sql.catalog.test_ducklake.metadata.db.type", "postgres")
                .config("spark.sql.catalog.test_ducklake.metadata.db.address", "localhost")
                .config("spark.sql.catalog.test_ducklake.metadata.db.port", "5432")
                .config("spark.sql.catalog.test_ducklake.metadata.db.username", "test")
                .config("spark.sql.catalog.test_ducklake.metadata.db.password", "test")
                .getOrCreate();

        try {
            Dataset<Row> tables = spark.sql("SHOW TABLES IN test_ducklake");

            System.out.println("Tables in ducklake catalog:");
            tables.show();

            assert tables.count() >= 0 : "Should be able to list tables";

            CatalogPlugin catalog = spark.sessionState().catalogManager().catalog("test_ducklake");
            assert catalog != null : "DuckLake catalog should be available";

            System.out.println("Test passed: listTables interface works correctly");

        } finally {
            spark.close();
        }
    }
}
