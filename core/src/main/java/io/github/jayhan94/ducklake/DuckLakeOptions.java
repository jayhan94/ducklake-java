package io.github.jayhan94.ducklake;

import lombok.Getter;

@Getter
public class DuckLakeOptions {
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final String metadataDb;
    private final String metadataSchema;
    private final String metadataPath;
    private final String dataPath;
    private final AccessMode accessMode = AccessMode.AUTOMATIC;
    private final DuckLakeEncryption encryption = DuckLakeEncryption.AUTOMATIC;

    public DuckLakeOptions(
            String jdbcUrl,
            String username,
            String password,
            String metadataDb,
            String metadataSchema,
            String metadataPath,
            String dataPath) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.metadataDb = metadataDb;
        this.metadataSchema = metadataSchema;
        this.metadataPath = metadataPath;
        this.dataPath = dataPath;
    }
}
