package io.github.jayhan94.ducklake.metadata;

import lombok.Getter;
import lombok.SneakyThrows;
import org.duckdb.DuckDBConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public abstract class DuckLakeCatalog implements AutoCloseable {
    @Getter
    protected String catalogName;
    protected String address;
    protected Integer port;
    protected String username;
    protected String password;
    protected String dataPath;
    private DuckDBConnection conn;

    public DuckLakeCatalog(String catalogName,
                           String address,
                           Integer port,
                           String username,
                           String password,
                           String dataPath) {
        this.catalogName = catalogName;
        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
        this.dataPath = dataPath;
    }

    @SneakyThrows
    private void createConnection() {
        conn = DuckDBConnection.newConnection("jdbc:duckdb:", false, new Properties());
    }

    @SneakyThrows
    protected ResultSet executeQuery(String query) {
        try (Statement stmt = conn.createStatement()) {
            return stmt.executeQuery(query);
        }
    }

    @SneakyThrows
    protected boolean execute(String sql) {
        try (Statement stmt = conn.createStatement()) {
            return stmt.execute(sql);
        }
    }

    protected abstract void installPlugins();

    protected abstract void attach();

    public void initialize() {
        createConnection();
        installPlugins();
        attach();
    }

    @Override
    @SneakyThrows
    public void close() {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }


}
