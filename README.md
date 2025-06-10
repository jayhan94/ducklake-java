# ducklake-java
A java implementation of [ducklake](https://ducklake.select/).

# Java version
Java 17 or above is required.

# examples
## Snapshot
```java
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
    System.out.println("snapshot info");
    System.out.println(snapshot.id());
    System.out.println(snapshot.timestamp());
    System.out.println(snapshot.schemaVersion());
    catalog.close();
}
```

