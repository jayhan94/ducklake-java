package io.github.jayhan94.ducklake.api;

import java.util.Objects;

/**
 * Table is a view of the table at a specific snapshot
 */
public interface Table {
    long tableId();

    String tableName();

    TableSchema tableSchema();

    // get the snapshot of the table
    Snapshot snapshot();

    Schema schema();

    Scan scan();

    TableStatistics tableStatistics();

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
