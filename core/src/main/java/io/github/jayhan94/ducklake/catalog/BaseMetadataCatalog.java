package io.github.jayhan94.ducklake.catalog;

import io.github.jayhan94.ducklake.SchemaImpl;
import io.github.jayhan94.ducklake.SnapshotImpl;
import io.github.jayhan94.ducklake.TableColumnImpl;
import io.github.jayhan94.ducklake.TableImpl;
import io.github.jayhan94.ducklake.TableSchemaImpl;
import io.github.jayhan94.ducklake.api.Catalog;
import io.github.jayhan94.ducklake.api.DataFiles;
import io.github.jayhan94.ducklake.api.DeleteFiles;
import io.github.jayhan94.ducklake.api.Schema;
import io.github.jayhan94.ducklake.api.Snapshot;
import io.github.jayhan94.ducklake.api.Table;
import io.github.jayhan94.ducklake.api.TableColumn;
import io.github.jayhan94.ducklake.api.Table.TableIdentifier;
import io.github.jayhan94.ducklake.api.TableSchema;
import io.github.jayhan94.ducklake.api.TableStatistics;
import io.github.jayhan94.ducklake.catalog.duckdb.CatalogDuckDB;
import io.github.jayhan94.ducklake.datatype.DataType;
import io.github.jayhan94.ducklake.datatype.DataTypes;
import io.github.jayhan94.ducklake.datatype.StructType;
import io.github.jayhan94.ducklake.entity.DuckLakeColumn;
import io.github.jayhan94.ducklake.entity.DuckLakeSchema;
import io.github.jayhan94.ducklake.entity.DuckLakeSnapshot;
import io.github.jayhan94.ducklake.entity.DuckLakeTable;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseMetadataCatalog implements AutoCloseable, Catalog {
    private static final Logger logger = LoggerFactory.getLogger(BaseMetadataCatalog.class);
    // The real database name is CATALOG_DATABASE_PREFIX + catalogName
    private static final String CATALOG_DATABASE_PREFIX = "__ducklake_metadata_";
    public static final String DEFAULT_TABLE_SCHEMA = "main";

    protected String catalogName;
    protected String catalogDatabase;
    protected String address;
    protected Integer port;
    protected String username;
    protected String password;
    protected String dataPath;
    protected CatalogDuckDB catalogdb;

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
        catalogdb = new CatalogDuckDB();
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

    /**
     * ==============================================
     * Catalog Interface Implementation
     * ==============================================
     */

    @Override
    public Snapshot getSnapshot(Long snapshotId) {
        DuckLakeSnapshot duckLakeSnapshot;
        if (snapshotId == null) {
            duckLakeSnapshot = catalogdb.getLatestSnapshot();
        } else {
            duckLakeSnapshot = catalogdb.getSnapshot(snapshotId);
        }
        return new SnapshotImpl(duckLakeSnapshot.getSnapshotId(), duckLakeSnapshot.getSnapshotTime().toEpochMilli(),
                duckLakeSnapshot.getSchemaVersion());
    }

    @Override
    public Schema getSchema(Long snapshotId, String schemaName) {
        DuckLakeSchema schemaEntity = catalogdb.getSchema(snapshotId, schemaName);
        if (schemaEntity == null) {
            throw new IllegalArgumentException("Schema doesn't exist: " + schemaName);
        }
        return new SchemaImpl(schemaEntity.getSchemaId(), schemaEntity.getSchemaName());
    }

    @Override
    public Table getTable(Long snapshotId, TableIdentifier tableIdentifier) {
        Snapshot snapshot = getSnapshot(snapshotId);
        if (snapshot == null) {
            throw new IllegalArgumentException(
                    "Snapshot not found for snapshotId: " + snapshotId + " and tableName: "
                            + tableIdentifier.toString());
        }

        String schemaName = tableIdentifier.hasSchemaName() ? tableIdentifier.getSchemaName() : DEFAULT_TABLE_SCHEMA;
        String tableName = tableIdentifier.getTableName();

        // Get schema information
        Schema schema = getSchema(snapshotId, schemaName);

        // Get table information
        DuckLakeTable tableEntity = catalogdb.getTable(snapshotId, schema.schemaId(), tableName);
        if (tableEntity == null) {
            throw new IllegalArgumentException("Table doesn't exist: " + tableIdentifier.toString());
        }

        long tableId = tableEntity.getTableId();

        // Get table columns
        TableSchema tableSchema = getTableSchema(snapshotId, tableId);

        // TODO Get table statistics
        TableStatistics tableStatistics = null;

        return new TableImpl(snapshot, schema, tableId, tableName, tableSchema, tableStatistics);
    }

    @Override
    public Table createTable(String schemaName, TableIdentifier tableIdentifier, TableSchema schema) {
        // TODO: implement this
        return null;
    }

    @Override
    public TableSchema getTableSchema(long snapshotId, long tableId) {
        List<DuckLakeColumn> columnsEntity = catalogdb.getTableColumns(snapshotId, tableId);
        // Group by parent column recursively
        Map<Long, List<DuckLakeColumn>> columnsByParent = new HashMap<>();
        // Group by parentColumnId
        for (DuckLakeColumn column : columnsEntity) {
            Long parentId = column.getParentColumn();
            columnsByParent.computeIfAbsent(parentId, k -> new ArrayList<>()).add(column);
        }

        // Get top level columns (columns with null parentColumnId)
        List<TableColumn> parentColumns = Objects
                .requireNonNull(columnsByParent.get(null), "Top level columns not found")
                .stream()
                .map(column -> buildTableColumn(column, columnsByParent))
                .collect(Collectors.toList());
        if (parentColumns.isEmpty()) {
            throw new IllegalStateException("Internal error: No top level columns found for tableId: " + tableId);
        }

        return new TableSchemaImpl(parentColumns);
    }

    /**
     * Recursively build TableColumn, handling nested types
     */
    private TableColumn buildTableColumn(DuckLakeColumn column, Map<Long, List<DuckLakeColumn>> columnsByParent) {
        List<DuckLakeColumn> children = columnsByParent.getOrDefault(column.getColumnId(), new ArrayList<>());

        // If no children, it's a primitive type
        if (children.isEmpty()) {
            return new TableColumnImpl(
                    column.getColumnId(),
                    column.getColumnName(),
                    DataTypes.parseType(column.getColumnType()),
                    column.getNullsAllowed(),
                    column.getDefaultValue(),
                    column.getInitialDefault(),
                    null // TODO: implement column statistics
            );
        }

        // Build complex type based on child columns
        DataType columnType;
        String typeName = column.getColumnType();

        if (typeName.equals("map")) {
            // Map type needs key and value child columns
            DuckLakeColumn keyColumn = children.get(0);
            DuckLakeColumn valueColumn = children.get(1);
            DataType keyType = buildTableColumn(keyColumn, columnsByParent).columnType();
            DataType valueType = buildTableColumn(valueColumn, columnsByParent).columnType();
            columnType = DataTypes.map(keyType, valueType);
        } else if (typeName.equals("list")) {
            // List type needs an element child column
            DuckLakeColumn elementColumn = children.get(0);
            DataType elementType = buildTableColumn(elementColumn, columnsByParent).columnType();
            columnType = DataTypes.list(elementType);
        } else if (typeName.equals("struct")) {
            // Struct type needs multiple child columns, each corresponding to a field
            List<StructType.StructField> fields = children.stream()
                    .map(child -> DataTypes.field(
                            child.getColumnName(),
                            buildTableColumn(child, columnsByParent).columnType()))
                    .collect(Collectors.toList());
            columnType = DataTypes.struct(fields);
        } else {
            throw new IllegalArgumentException("Unsupported nested type: " + typeName);
        }

        return new TableColumnImpl(
                column.getColumnId(),
                column.getColumnName(),
                columnType,
                column.getNullsAllowed(),
                column.getDefaultValue(),
                column.getInitialDefault(),
                null // TODO: implement column statistics
        );
    }

    @Override
    public TableStatistics getTableStatistics(long snapshotId, long tableId) {
        // TODO: implement this
        return null;
    }

    @Override
    public DataFiles getTableDataFiles(long snapshotId, long tableId) {
        // TODO: implement this
        return null;
    }

    @Override
    public DeleteFiles getTableDeleteFiles(long snapshotId, long tableId) {
        // TODO: implement this
        return null;
    }
}