package io.github.jayhan94.ducklake.metadata.impl;

import io.github.jayhan94.ducklake.metadata.DuckLakeCatalog;

public class PGCatalogImpl extends DuckLakeCatalog {

    public PGCatalogImpl(String catalogName, String address, Integer port, String username, String password,
                         String dataPath) {
        super(catalogName, address, port, username, password, dataPath);
    }

    @Override
    protected void installPlugins() {
        execute("""
                        INSTALL ducklake;
                        INSTALL postgres;
                        """);
    }

    @Override
    protected void attach() {
        String attch = String.format("""
                                             ATTACH 'ducklake:postgres:dbname=%s host=%s port=%s user=%s password=%s' AS %s (DATA_PATH '%s');
                                             USE %s;
                                             """,
                                     catalogName,
                                     address,
                                     port,
                                     username,
                                     password,
                                     catalogName,
                                     dataPath,
                                     catalogName);
        execute(attch);
    }
}
