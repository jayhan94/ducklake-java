package io.github.jayhan94.ducklake.catalog;

public class MySQLMetadataCatalogImpl extends BaseMetadataCatalog {

    public MySQLMetadataCatalogImpl(String catalogName, String address, Integer port, String username, String password,
                                    String dataPath) {
        super(catalogName, address, port, username, password, dataPath);
    }

    @Override
    protected void installPlugins() {
        execute("""
                        INSTALL ducklake;
                        INSTALL mysql;
                        """);
    }

    @Override
    protected void attach() {
        String attch = String.format(
                "ATTACH 'ducklake:mysql:db=%s host=%s port=%s user=%s password=%s' AS %s (DATA_PATH '%s');",
                catalogName,
                address,
                port,
                username,
                password,
                catalogName,
                dataPath);
        execute(attch);
    }
}
