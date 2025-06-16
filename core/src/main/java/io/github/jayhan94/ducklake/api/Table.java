package io.github.jayhan94.ducklake.api;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a DuckLake table. It provides access to the table's metadata,
 * schema,
 * and data files for a specific {@link Snapshot}.
 */
public interface Table {
    /**
     * Gets the unique ID of the table.
     * 
     * @return the table ID
     */
    long tableId();

    /**
     * Gets the name of the table.
     * 
     * @return the table name
     */
    String tableName();

    /**
     * Gets the schema of the table.
     * 
     * @return the table schema as a {@link TableSchema} instance
     */
    TableSchema tableSchema();

    /**
     * Gets the list of data files for the current snapshot of the table.
     * 
     * @return a list of {@link DataFile}
     */
    List<DataFile> dataFiles();

    /**
     * Gets the snapshot that this table view is based on.
     * 
     * @return the {@link Snapshot} instance
     */
    Snapshot snapshot();

    /**
     * Gets the schema (namespace) this table belongs to.
     * 
     * @return the {@link Schema} instance
     */
    Schema schema();

    /**
     * Creates a new scan for this table.
     * 
     * @return a {@link Scan} builder to configure the scan
     */
    Scan scan();

    /**
     * Gets the statistics for this table.
     * 
     * @return a {@link TableStatistics} instance
     */
    Optional<TableStatistics> tableStatistics();

    /**
     * TableIdentifier uniquely identifies a table within a schema
     * Used for table lookup and reference operations
     */
    public static class TableIdentifier {
        private final String schemaName;
        private final String tableName;

        /**
         * Create a table identifier
         * 
         * @param schemaName the schema name
         * @param tableName  the table name
         */
        public TableIdentifier(String schemaName, String tableName) {
            if (tableName == null || tableName.trim().isEmpty()) {
                throw new IllegalArgumentException("Table name cannot be null or empty");
            }
            this.schemaName = schemaName;
            this.tableName = tableName.trim();
        }

        /**
         * Create a table identifier with default schema
         * 
         * @param tableName the table name
         */
        public TableIdentifier(String tableName) {
            this(null, tableName);
        }

        /**
         * Get the schema name
         * 
         * @return the schema name, or null if using default schema
         */
        public String getSchemaName() {
            return schemaName;
        }

        /**
         * Get the table name
         * 
         * @return the table name
         */
        public String getTableName() {
            return tableName;
        }

        /**
         * Check if this identifier has a schema name
         * 
         * @return true if schema name is specified
         */
        public boolean hasSchemaName() {
            return schemaName != null && !schemaName.trim().isEmpty();
        }

        /**
         * Get the fully qualified table name
         * 
         * @return schema.table or just table if no schema specified
         */
        public String getFullyQualifiedName() {
            if (hasSchemaName()) {
                return schemaName + "." + tableName;
            }
            return tableName;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            TableIdentifier that = (TableIdentifier) obj;
            return Objects.equals(schemaName, that.schemaName) &&
                    tableName.equals(that.tableName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(schemaName, tableName);
        }

        @Override
        public String toString() {
            return getFullyQualifiedName();
        }

        /**
         * Parse a table identifier from a string
         * Supports both "table" and "schema.table" formats
         * 
         * @param identifier the identifier string
         * @return the parsed TableIdentifier
         */
        public static TableIdentifier parse(String identifier) {
            if (identifier == null || identifier.trim().isEmpty()) {
                throw new IllegalArgumentException("Table identifier cannot be null or empty");
            }

            String trimmed = identifier.trim();
            int dotIndex = trimmed.indexOf('.');

            if (dotIndex == -1) {
                // No schema specified
                return new TableIdentifier(trimmed);
            } else {
                // Schema.table format
                String schema = trimmed.substring(0, dotIndex).trim();
                String table = trimmed.substring(dotIndex + 1).trim();

                if (schema.isEmpty() || table.isEmpty()) {
                    throw new IllegalArgumentException("Invalid table identifier format: " + identifier);
                }

                return new TableIdentifier(schema, table);
            }
        }
    }
}
