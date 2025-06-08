package io.github.jayhan94.ducklake.catalog;

import io.github.jayhan94.ducklake.SnapshotImpl;
import io.github.jayhan94.ducklake.TableSchema;
import io.github.jayhan94.ducklake.api.Catalog;
import io.github.jayhan94.ducklake.api.Schema;
import io.github.jayhan94.ducklake.api.Snapshot;
import io.github.jayhan94.ducklake.api.Table;
import io.github.jayhan94.ducklake.catalog.duckdb.DuckDB;
import io.github.jayhan94.ducklake.entity.DuckLakeSnapshot;
import lombok.SneakyThrows;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseMetadataCatalog implements AutoCloseable, Catalog {
    private static final Logger logger = LoggerFactory.getLogger(BaseMetadataCatalog.class);
    // The real database name is CATALOG_DATABASE_PREFIX + catalogName
    private static final String CATALOG_DATABASE_PREFIX = "__ducklake_metadata_";
    protected String catalogName;
    protected String catalogDatabase;
    protected String address;
    protected Integer port;
    protected String username;
    protected String password;
    protected String dataPath;
    protected DuckDB catalogdb;

    public BaseMetadataCatalog(String catalogName,
                               String address,
                               Integer port,
                               String username,
                               String password,
                               String dataPath) {
        this.catalogName = catalogName;
        this.catalogDatabase = CATALOG_DATABASE_PREFIX + catalogName;
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
    protected void createConnection() {
        catalogdb = new DuckDB();
    }

    /**
     * Execute query and return single result
     */
    protected <T> T queryOne(String query, Class<T> resultType) {
        if (catalogdb == null) {
            throw new IllegalStateException("DuckDB connection not initialized");
        }
        return catalogdb.queryOne(query, resultType);
    }

    /**
     * Execute SQL statement (DDL/DML)
     */
    protected void execute(String sql) {
        if (catalogdb == null) {
            throw new IllegalStateException("DuckDB connection not initialized");
        }
        catalogdb.execute(sql);
        logger.debug("Executed SQL: {}", sql);
    }

    protected abstract void installPlugins();

    protected abstract void attach();

    private void useCatalogDatabase() {
        execute(String.format("USE %s", catalogDatabase));
    }

    public void initialize() {
        createConnection();
        installPlugins();
        attach();
        useCatalogDatabase();
        logger.info("DuckLake catalog '{}' initialized successfully", catalogName);
    }

    @Override
    @SneakyThrows
    public void close() {
        if (catalogdb != null && !catalogdb.isClosed()) {
            catalogdb.close();
            logger.info("DuckDB connection closed for catalog: {}", catalogName);
        }
    }

    @Override
    public Snapshot getSnapshot(Long snapshotId) {
        DuckLakeSnapshot duckLakeSnapshot;
        if (snapshotId == null) {
            duckLakeSnapshot = catalogdb.getLatestSnapshot();
        } else {
            duckLakeSnapshot = catalogdb.getSnapshot(snapshotId);
        }
        return new SnapshotImpl(catalogdb, duckLakeSnapshot);
    }

    @Override
    public List<Schema> listSchemas(Long snapshotId) {
        return null;
    }

    @Override
    public Table getTable(Long snapshotId, Long schemaId, String tableName) {
        return null;
    }

    @Override
    public Table createTable(String schemaName, String tableName, TableSchema schema) {
        return null;
    }

}
