package io.github.jayhan94.ducklake.spark.catalog;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.github.jayhan94.ducklake.catalog.BaseMetadataCatalog;
import io.github.jayhan94.ducklake.catalog.DuckDBMetadataCatalogImpl;
import io.github.jayhan94.ducklake.catalog.MySQLMetadataCatalogImpl;
import io.github.jayhan94.ducklake.catalog.PostgresMetadataCatalogImpl;
import io.github.jayhan94.ducklake.catalog.SqliteMetadataCatalogImpl;

import org.apache.spark.sql.catalyst.analysis.NoSuchTableException;
import org.apache.spark.sql.connector.catalog.Identifier;
import org.apache.spark.sql.connector.catalog.Table;
import org.apache.spark.sql.connector.catalog.TableCatalog;
import org.apache.spark.sql.connector.catalog.TableChange;
import org.apache.spark.sql.connector.expressions.Transform;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.util.CaseInsensitiveStringMap;

public class SparkDuckLakeCatalog implements TableCatalog {

    private String name;
    private BaseMetadataCatalog meta;

    @Override
    public void initialize(String name, CaseInsensitiveStringMap options) {
        this.name = name;
        String ducklakeDatabase = options.get("database");
        String ducklakeDataPath = options.get("dataPath");
        String ducklakeMetadataDBType = options.get("metadata.db.type");
        String ducklakeMetadataDBAddress = options.get("metadata.db.address");
        if ("postgres".equalsIgnoreCase(ducklakeMetadataDBType)
                || "postgresql".equalsIgnoreCase(ducklakeMetadataDBType)) {
            Integer ducklakeMetadataDBPort = Integer.parseInt(options.get("metadata.db.port"));
            String ducklakeMetadataDBUsername = options.get("metadata.db.username");
            String ducklakeMetadataDBPassword = options.get("metadata.db.password");
            this.meta = new PostgresMetadataCatalogImpl(ducklakeDatabase, ducklakeMetadataDBAddress,
                    ducklakeMetadataDBPort, ducklakeMetadataDBUsername, ducklakeMetadataDBPassword, ducklakeDataPath);
        } else if ("mysql".equalsIgnoreCase(ducklakeMetadataDBType)) {
            Integer ducklakeMetadataDBPort = Integer.parseInt(options.get("metadata.db.port"));
            String ducklakeMetadataDBUsername = options.get("metadata.db.username");
            String ducklakeMetadataDBPassword = options.get("metadata.db.password");
            this.meta = new MySQLMetadataCatalogImpl(ducklakeDatabase, ducklakeMetadataDBAddress,
                    ducklakeMetadataDBPort, ducklakeMetadataDBUsername, ducklakeMetadataDBPassword,
                    ducklakeDataPath);
        } else if ("duckdb".equalsIgnoreCase(ducklakeMetadataDBType)) {
            this.meta = new DuckDBMetadataCatalogImpl(ducklakeDatabase, ducklakeMetadataDBAddress);
        } else if ("sqlite".equalsIgnoreCase(ducklakeMetadataDBType)
                || "sqlite3".equalsIgnoreCase(ducklakeMetadataDBType)) {
            this.meta = new SqliteMetadataCatalogImpl(ducklakeDatabase, ducklakeMetadataDBAddress,
                    ducklakeDataPath);
        } else {
            throw new IllegalArgumentException("Unsupported metadata database type: " + ducklakeMetadataDBType);
        }
        this.meta.initialize();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Identifier[] listTables(String[] namespace) {
        String schema = BaseMetadataCatalog.DEFAULT_TABLE_SCHEMA;
        if (namespace.length == 1) {
            schema = namespace[0];
        } else if (namespace.length > 1) {
            throw new IllegalArgumentException("Invalid namespace: " + Arrays.toString(namespace));
        }
        List<io.github.jayhan94.ducklake.api.Table> tables = meta.listTables(null, schema);
        return tables.stream().map(table -> Identifier.of(namespace, table.tableName())).toArray(Identifier[]::new);
    }

    @Override
    public Table loadTable(Identifier ident) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Table createTable(Identifier ident,
            StructType schema,
            Transform[] partitions,
            Map<String, String> props) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean tableExists(Identifier ident) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean dropTable(Identifier ident) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void renameTable(Identifier ident, Identifier newIdent) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Table alterTable(Identifier ident, TableChange... changes) throws NoSuchTableException {
        throw new UnsupportedOperationException("Not implemented");
    }
}