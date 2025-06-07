package io.github.jayhan94.ducklake.impl.catalogs;

import io.github.jayhan94.ducklake.impl.DuckLakeCatalog;

public class SqliteCatalogImpl extends DuckLakeCatalog {

    public SqliteCatalogImpl(String catalogName, String address, String dataPath) {
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
        String attach = String.format("""
                                              ATTACH 'ducklake:sqlite:%s' AS %s (DATA_PATH '%s');
                                              USE %s;
                                              """, address, catalogName, dataPath, catalogName);
        execute(attach);
    }
}
