package io.github.jayhan94.ducklake.api;

/**
 * Represents a snapshot of a table's state at a specific point in time.
 */
public interface Snapshot {
    /**
     * Gets the unique ID of the snapshot.
     * 
     * @return the snapshot ID
     */
    long id();

    /**
     * Gets the timestamp when the snapshot was created.
     * 
     * @return the creation timestamp
     */
    long timestamp();

    /**
     * Gets the version of the schema associated with this snapshot.
     * 
     * @return the schema version
     */
    long schemaVersion();
}
