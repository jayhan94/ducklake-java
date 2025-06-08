package io.github.jayhan94.ducklake.catalog;

public class DuckDBMetadataCatalogImpl extends BaseMetadataCatalog {

    public DuckDBMetadataCatalogImpl(String catalogName, String address) {
        super(catalogName, address, null, null, null, null);
    }

    @Override
    protected void installPlugins() {
        execute("INSTALL ducklake;");
    }

    @Override
    protected void attach() {
        execute(String.format("ATTACH 'ducklake:%s' AS %s;", address, catalogName));
    }
}
