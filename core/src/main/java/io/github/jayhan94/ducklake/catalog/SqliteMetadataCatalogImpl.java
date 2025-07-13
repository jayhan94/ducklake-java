package io.github.jayhan94.ducklake.catalog;

public class SqliteMetadataCatalogImpl extends BaseMetadataCatalog {

    public SqliteMetadataCatalogImpl(String database, String address, String dataPath) {
        super(database, address, null, null, null, dataPath);
    }

    @Override
    protected void installPlugins() {
        execute("""
                INSTALL ducklake;
                INSTALL sqlite;
                """);
    }

    @Override
    protected void attach() {
        String attach = String.format("ATTACH 'ducklake:sqlite:%s' AS %s (DATA_PATH '%s');",
                address,
                database,
                dataPath);
        execute(attach);
    }
}
