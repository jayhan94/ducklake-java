package io.github.jayhan94.ducklake.duckdb;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * DuckDB connection management class
 * Uses HikariCP connection pool for connection management
 */
public class DuckDB implements Closeable {
    private static final Logger logger = LoggerFactory.getLogger(DuckDB.class);

    private final Jdbi duckdb;
    private final HikariDataSource dataSource;

    /**
     * Create an in-memory DuckDB instance with default connection pool configuration
     */
    public DuckDB() {
        this(null, createDefaultConfig());
    }

    /**
     * Create DuckDB instance with default connection pool configuration
     *
     * @param file DuckDB database file path
     */
    public DuckDB(String file) {
        this(file, createDefaultConfig());
    }

    /**
     * Create DuckDB instance with custom connection pool configuration
     *
     * @param file   DuckDB database file path
     * @param config HikariCP configuration
     */
    public DuckDB(String file, HikariConfig config) {
        if (file == null) {
            file = "";
        }
        String jdbcUrl = "jdbc:duckdb:" + file;
        config.setJdbcUrl(jdbcUrl);

        // Ensure DuckDB driver is loaded
        config.setDriverClassName("org.duckdb.DuckDBDriver");

        this.dataSource = new HikariDataSource(config);
        this.duckdb = Jdbi.create(dataSource);
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
        return duckdb.withHandle(handle -> {
            // Inside this lambda, connections are automatically managed
            // Connections will be properly closed regardless of exceptions
            return handle.createQuery(sql).mapTo(clazz).one();
        });
    }

    /**
     * Query multiple results
     * Also uses withHandle to ensure automatic connection recycling
     */
    public <T> List<T> query(String sql, Class<T> clazz) {
        return duckdb.withHandle(handle -> handle.createQuery(sql).mapTo(clazz).list());
    }

    /**
     * Execute SQL statement
     * Uses withHandle to ensure connection recycling
     */
    public void execute(String sql) {
        duckdb.withHandle(handle -> {
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
}
