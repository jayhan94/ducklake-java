package io.github.jayhan94.ducklake.impl;

import io.github.jayhan94.ducklake.api.Catalog;
import io.github.jayhan94.ducklake.api.Table;
import io.github.jayhan94.ducklake.duckdb.DuckDB;
import io.github.jayhan94.ducklake.type.Schema;
import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DuckLakeCatalog implements AutoCloseable, Catalog {
    private static final Logger logger = LoggerFactory.getLogger(DuckLakeCatalog.class);

    @Getter
    protected String catalogName;
    protected String address;
    protected Integer port;
    protected String username;
    protected String password;
    protected String dataPath;
    protected DuckDB duckdb;

    public DuckLakeCatalog(String catalogName,
                           String address,
                           Integer port,
                           String username,
                           String password,
                           String dataPath) {
        this.catalogName = catalogName;
        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dataPath = dataPath;
    }

    /**
     * Create DuckDB connection using the custom DuckDB class
     */
    @SneakyThrows
    private void createConnection() {
        duckdb = new DuckDB();
    }

    /**
     * Execute query and return single result
     */
    protected <T> T queryOne(String query, Class<T> resultType) {
        if (duckdb == null) {
            throw new IllegalStateException("DuckDB connection not initialized");
        }
        return duckdb.queryOne(query, resultType);
    }

    /**
     * Execute SQL statement (DDL/DML)
     */
    protected void execute(String sql) {
        if (duckdb == null) {
            throw new IllegalStateException("DuckDB connection not initialized");
        }
        duckdb.execute(sql);
        logger.debug("Executed SQL: {}", sql);
    }

    protected abstract void installPlugins();

    protected abstract void attach();

    public void initialize() {
        createConnection();
        installPlugins();
        attach();
        logger.info("DuckLake catalog '{}' initialized successfully", catalogName);
    }

    @Override
    @SneakyThrows
    public void close() {
        if (duckdb != null && !duckdb.isClosed()) {
            duckdb.close();
            logger.info("DuckDB connection closed for catalog: {}", catalogName);
        }
    }

    @Override
    public Table getTable(String tableName) {
        return null;
    }

    @Override
    public Table createTable(String tableName, Schema schema) {
        return null;
    }

    @Override
    public Table updateTableSchema(String tableName, Schema schema) {
        return null;
    }

    @Override
    public Table deleteTable(String tableName) {
        return null;
    }
}
