package io.github.jayhan94.ducklake.metadata;

import io.github.jayhan94.ducklake.impl.DuckLakeCatalog;
import io.github.jayhan94.ducklake.impl.catalogs.DuckDBCatalogImpl;
import io.github.jayhan94.ducklake.impl.catalogs.MySQLCatalogImpl;
import io.github.jayhan94.ducklake.impl.catalogs.PGCatalogImpl;
import io.github.jayhan94.ducklake.impl.catalogs.SqliteCatalogImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.nio.file.attribute.PosixFilePermissions.asFileAttribute;
import static java.nio.file.attribute.PosixFilePermissions.fromString;

public class DuckLakeCatalogTest {
    private Path tempDataPath;

    @Before
    public void createTempDataPath() throws IOException {
        tempDataPath = Files.createTempDirectory("ducklake_data_files", asFileAttribute(fromString("rw-rw-rw-")));
        File f = tempDataPath.toFile();
        f.delete();
        if (!f.mkdirs()) {
            throw new RuntimeException(tempDataPath + " could not be created");
        } else {
            tempDataPath.toFile().deleteOnExit();
        }
    }

    @After
    public void deleteTempDataPath() {
        tempDataPath.toFile().delete();
    }

    @Test
    public void testAttachPostgresqlAsMeta() throws SQLException {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")) {
            postgres.withDatabaseName("ducklake_catalog");
            postgres.start();
            try (DuckLakeCatalog catalog = new PGCatalogImpl("ducklake_catalog",
                                                             postgres.getHost(),
                                                             postgres.getMappedPort(5432),
                                                             postgres.getUsername(),
                                                             postgres.getPassword(),
                                                             tempDataPath.toString())) {
                catalog.initialize();
                try (Connection connection = DriverManager.getConnection(postgres.getJdbcUrl(),
                                                                         postgres.getUsername(),
                                                                         postgres.getPassword());
                     Statement statement = connection.createStatement()) {
                    ResultSet resultSet = statement.executeQuery(
                            "SELECT COUNT(table_name) FROM information_schema.tables WHERE table_name LIKE " +
                                    "'ducklake_%'");
                    Assert.assertTrue(resultSet.next());
                    Assert.assertEquals(19, resultSet.getInt(1));
                }
            } finally {
                postgres.stop();
            }
        }
    }

    @Test
    public void testAttachMysqlAsMeta() throws SQLException {
        try (MySQLContainer<?> mysql = new MySQLContainer<>("mysql:5.7.34")) {
            mysql.withDatabaseName("ducklake_catalog");
            mysql.start();
            try (DuckLakeCatalog catalog = new MySQLCatalogImpl("ducklake_catalog",
                                                                mysql.getHost(),
                                                                mysql.getMappedPort(3306),
                                                                mysql.getUsername(),
                                                                mysql.getPassword(),
                                                                tempDataPath.toString())) {

                catalog.initialize();
                try (Connection connection = DriverManager.getConnection(mysql.getJdbcUrl(),
                                                                         mysql.getUsername(),
                                                                         mysql.getPassword());
                     Statement statement = connection.createStatement()) {
                    ResultSet resultSet = statement.executeQuery(
                            "SELECT COUNT(table_name) FROM information_schema.tables WHERE table_name LIKE " +
                                    "'ducklake_%'");
                    Assert.assertTrue(resultSet.next());
                    Assert.assertEquals(19, resultSet.getInt(1));
                }
            } finally {
                mysql.stop();
            }
        }
    }

    @Test
    public void testAttachDuckDBAsMeta() {
        String dbFile = tempDataPath.toString() + "/metadata.duckdb";
        try (DuckLakeCatalog catalog = new DuckDBCatalogImpl("ducklake_catalog", dbFile)) {
            catalog.initialize();
        }

    }

    @Test
    public void testAttachSqliteAsMeta() throws SQLException {
        String dbFile = tempDataPath.toString() + "/metadata.sqlite";
        try (DuckLakeCatalog catalog = new SqliteCatalogImpl("ducklake_catalog",
                                                             dbFile,
                                                             tempDataPath.toString())) {
            catalog.initialize();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT COUNT(name) FROM sqlite_master WHERE type='table' AND name LIKE 'ducklake_%'");
            Assert.assertTrue(resultSet.next());
            Assert.assertEquals(19, resultSet.getInt(1));
        }
    }
}
