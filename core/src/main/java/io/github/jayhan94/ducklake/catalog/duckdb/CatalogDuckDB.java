package io.github.jayhan94.ducklake.catalog.duckdb;

import com.google.common.base.Function;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.github.jayhan94.ducklake.catalog.duckdb.sql.SQLTransaction;
import io.github.jayhan94.ducklake.entity.DuckLakeColumn;
import io.github.jayhan94.ducklake.entity.DuckLakeDataFile;
import io.github.jayhan94.ducklake.entity.DuckLakeDeleteFile;
import io.github.jayhan94.ducklake.entity.DuckLakeFileColumnStatistics;
import io.github.jayhan94.ducklake.entity.DuckLakeSchema;
import io.github.jayhan94.ducklake.entity.DuckLakeSnapshot;
import io.github.jayhan94.ducklake.entity.DuckLakeTable;
import io.github.jayhan94.ducklake.entity.DuckLakeTableColumnStats;
import io.github.jayhan94.ducklake.entity.DuckLakeTableStats;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.jpa.JpaMapperFactory;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * DuckDB connection management class
 * Uses HikariCP connection pool for connection management
 */
public class CatalogDuckDB implements Closeable, SQLTransaction {
    private static final Logger logger = LoggerFactory.getLogger(CatalogDuckDB.class);

    private final Jdbi jdbi;
    private final HikariDataSource dataSource;

    /**
     * Create an in-memory DuckDB instance with default connection pool
     * configuration
     */
    public CatalogDuckDB() {
        this(null, createDefaultConfig());
    }

    /**
     * Create DuckDB instance with default connection pool configuration
     *
     * @param file DuckDB database file path
     */
    public CatalogDuckDB(String file) {
        this(file, createDefaultConfig());
    }

    /**
     * Create DuckDB instance with custom connection pool configuration
     *
     * @param file   DuckDB database file path
     * @param config HikariCP configuration
     */
    public CatalogDuckDB(String file, HikariConfig config) {
        if (file == null) {
            file = "";
        }
        String jdbcUrl = "jdbc:duckdb:" + file;
        config.setJdbcUrl(jdbcUrl);

        // Ensure DuckDB driver is loaded
        config.setDriverClassName("org.duckdb.DuckDBDriver");

        this.dataSource = new HikariDataSource(config);
        this.jdbi = Jdbi.create(dataSource)
                .registerRowMapper(new JpaMapperFactory())
                .installPlugin(new SqlObjectPlugin());
    }

    /**
     * Create default HikariCP configuration
     * Simplified config: connection count = CPU cores, connection timeout = 1
     * second
     */
    private static HikariConfig createDefaultConfig() {
        HikariConfig config = new HikariConfig();

        // Set connection pool size based on CPU cores
        int coreCount = Runtime.getRuntime().availableProcessors();
        config.setMaximumPoolSize(coreCount); // Max connections = CPU cores
        config.setMinimumIdle(1); // Min idle connections = 1

        // Timeout configuration
        config.setConnectionTimeout(1000); // Connection timeout: 1 second
        config.setIdleTimeout(300000); // Idle timeout: 5 minutes
        config.setMaxLifetime(600000); // Max connection lifetime: 10 minutes

        // Connection pool name
        config.setPoolName("DuckDB-Pool");

        return config;
    }

    /**
     * Query single result
     * withHandle automatically manages connection lifecycle, ensuring proper
     * connection recycling
     */
    public <T> T queryOne(String sql, Class<T> clazz) {
        return jdbi.withHandle(handle -> {
            // Inside this lambda, connections are automatically managed
            // Connections will be properly closed regardless of exceptions
            return handle.createQuery(sql).mapTo(clazz).one();
        });
    }

    /**
     * Execute SQL statement
     * Uses withHandle to ensure connection recycling
     */
    public void execute(String sql) {
        jdbi.withHandle(handle -> {
            handle.execute(sql);
            return null; // withHandle requires return value
        });
    }

    /**
     * Get connection pool status information for monitoring
     */
    public String getPoolStatus() {
        if (dataSource != null && !dataSource.isClosed()) {
            return String.format("Pool[%s] - Active: %d, Idle: %d, Total: %d, Waiting: %d",
                    dataSource.getPoolName(),
                    dataSource.getHikariPoolMXBean().getActiveConnections(),
                    dataSource.getHikariPoolMXBean().getIdleConnections(),
                    dataSource.getHikariPoolMXBean().getTotalConnections(),
                    dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection());
        }
        return "Pool is closed or not initialized";
    }

    /**
     * Check if the connection pool is closed
     */
    public boolean isClosed() {
        return dataSource == null || dataSource.isClosed();
    }

    @Override
    public void close() throws IOException {
        if (dataSource != null && !dataSource.isClosed()) {
            try {
                // Log connection pool status before closing
                logger.info("Connection pool status before closing: {}", getPoolStatus());

                // Close connection pool, this will close all active and idle connections
                dataSource.close();

                logger.info("Connection pool closed successfully");
            } catch (Exception e) {
                logger.error("Error occurred while closing connection pool", e);
                throw new IOException("Error occurred while closing connection pool", e);
            }
        }
    }

    /**
     * ==============================================
     * SQL Transaction Related Methods
     * ==============================================
     */
    private <T> T withTransaction(Function<SQLTransaction, T> transaction) {
        return jdbi.withExtension(SQLTransaction.class, dao -> transaction.apply(dao));
    }

    @Override
    public DuckLakeSnapshot getLatestSnapshot() {
        return withTransaction(transaction -> transaction.getLatestSnapshot());
    }

    @Override
    public DuckLakeSnapshot getSnapshot(long snapshotId) {
        return withTransaction(transaction -> transaction.getSnapshot(snapshotId));
    }

    @Override
    public List<DuckLakeSchema> listSchemas(long snapshotId) {
        return withTransaction(transaction -> transaction.listSchemas(snapshotId));
    }

    @Override
    public DuckLakeSchema getSchema(long snapshotId, String schemaName) {
        return withTransaction(transaction -> transaction.getSchema(snapshotId, schemaName));
    }

    @Override
    public DuckLakeTable getTable(long snapshotId, long schemaId, String tableName) {
        return withTransaction(transaction -> transaction.getTable(snapshotId, schemaId, tableName));
    }

    @Override
    public boolean createTable(DuckLakeTable duckLakeTable) {
        return withTransaction(transaction -> transaction.createTable(duckLakeTable));
    }

    @Override
    public List<DuckLakeColumn> getTableColumns(long snapshotId, long tableId) {
        return withTransaction(transaction -> transaction.getTableColumns(snapshotId, tableId));
    }

    @Override
    public List<DuckLakeDataFile> getTableDataFiles(long snapshotId, long tableId) {
        return withTransaction(transaction -> transaction.getTableDataFiles(snapshotId, tableId));
    }

    @Override
    public List<DuckLakeDeleteFile> getTableDeleteFiles(long snapshotId, long tableId) {
        return withTransaction(transaction -> transaction.getTableDeleteFiles(snapshotId, tableId));
    }

    @Override
    public List<DuckLakeTableColumnStats> getTableColumnStats(long tableId) {
        return withTransaction(transaction -> transaction.getTableColumnStats(tableId));
    }

    @Override
    public DuckLakeTableStats getTableStats(long tableId) {
        return withTransaction(transaction -> transaction.getTableStats(tableId));
    }

    @Override
    public List<DuckLakeFileColumnStatistics> getFileColumnStatistics(long tableId, long dataFileId) {
        return withTransaction(transaction -> transaction.getFileColumnStatistics(tableId, dataFileId));
    }
}
