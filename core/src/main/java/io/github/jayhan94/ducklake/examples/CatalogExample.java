package io.github.jayhan94.ducklake.examples;

import io.github.jayhan94.ducklake.api.Snapshot;
import io.github.jayhan94.ducklake.api.Table.TableIdentifier;
import io.github.jayhan94.ducklake.catalog.BaseMetadataCatalog;
import io.github.jayhan94.ducklake.catalog.PostgresMetadataCatalogImpl;

public class CatalogExample {
    public static void main(String[] args) {
        // You can use the following command to start a postgres instance for testing.
        // `docker run -d \
        //  -e POSTGRES_USER=test \
        //  -e POSTGRES_PASSWORD=test \
        //  -e POSTGRES_DB=ducklake_catalog \
        //  -p 5432:5432 \
        //  --name postgres \
        //  postgres:16-alpine`
        
        // You can also use other metadata catalogs, such as MySQLMetadataCatalogImpl, DuckDBMetadataCatalogImpl, etc.
        BaseMetadataCatalog catalog = new PostgresMetadataCatalogImpl("ducklake_catalog",
                                                                      "localhost",
                                                                      5432,
                                                                      "test",
                                                                      "test",
                                                                      ".tmp/data_files/");
        // After initializing, the catalog will be ready to use. You can query the metadata tables from the postgres instance.
        catalog.initialize();
        Snapshot snapshot = catalog.getSnapshot(null);
        System.out.println("------------snapshot------------");
        System.out.println("snapshot:\n" + snapshot);
        System.out.println("-------------schemas--------------");
        System.out.println("schemas:\n" + catalog.listSchemas(snapshot.id()));
        System.out.println("-------------tables--------------");
        System.out.println("tables:\n" + catalog.listTables(snapshot.id(), "main"));
        System.out.println("-------------table--------------");
        System.out.println("table:\n" + catalog.getTable(snapshot.id(), new TableIdentifier("main", "a")));
        catalog.close();
    }
}
