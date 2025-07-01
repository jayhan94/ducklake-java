package io.github.jayhan94.ducklake.spark.catalog;

import java.util.Map;

import io.github.jayhan94.ducklake.catalog.BaseMetadataCatalog;
import io.github.jayhan94.ducklake.catalog.DuckDBMetadataCatalogImpl;

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
        String address = options.get("address");
        this.meta = new DuckDBMetadataCatalogImpl(name, address);
        meta.initialize();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Identifier[] listTables(String[] namespace) {
        throw new UnsupportedOperationException("Not implemented");
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