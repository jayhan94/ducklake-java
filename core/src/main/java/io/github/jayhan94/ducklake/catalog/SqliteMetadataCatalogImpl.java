package io.github.jayhan94.ducklake.catalog;

public class SqliteMetadataCatalogImpl extends BaseMetadataCatalog {

    public SqliteMetadataCatalogImpl(String catalogName, String address, String dataPath) {
        super(catalogName, address, null, null, null, dataPath);
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
                                      catalogName,
                                      dataPath);
        execute(attach);
    }
}
