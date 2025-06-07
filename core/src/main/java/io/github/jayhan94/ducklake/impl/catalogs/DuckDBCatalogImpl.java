package io.github.jayhan94.ducklake.impl.catalogs;

import io.github.jayhan94.ducklake.impl.DuckLakeCatalog;

public class DuckDBCatalogImpl extends DuckLakeCatalog {

    public DuckDBCatalogImpl(String catalogName, String address) {
        super(catalogName, address, null, null, null, null);
    }

    @Override
    protected void installPlugins() {
        execute("INSTALL ducklake;");
    }

    @Override
    protected void attach() {
        execute(String.format("""
                                      ATTACH 'ducklake:%s' AS %s;
                                      USE %s;
                                      """, address, catalogName, catalogName));
    }
}
