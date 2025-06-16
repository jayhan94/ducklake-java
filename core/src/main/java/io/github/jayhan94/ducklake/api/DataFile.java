package io.github.jayhan94.ducklake.api;

import java.util.Optional;

/**
 * Represents a data file that contains rows for a table. It is a component of a
 * {@link Snapshot}.
 */
public interface DataFile {

    /**
     * Gets the unique ID for this data file.
     * 
     * @return the data file ID
     */
    Long dataFileId();

    /**
     * Gets the ID of the table this data file belongs to.
     * 
     * @return the table ID
     */
    Long tableId();

    /**
     * Gets the corresponding delete file, if any.
     * 
     * @return a {@link DeleteFile} instance, or null if no deletions are associated
     *         with this data file
     */
    Optional<DeleteFile> deleteFile();

    /**
     * Gets the path to the data file.
     * 
     * @return the file path, which can be absolute or relative
     */
    String path();

    /**
     * Gets the format of the data file (e.g., PARQUET, ORC).
     * 
     * @return the file format
     */
    FileFormat fileFormat();

    /**
     * Gets the starting logical row ID in the file.
     * 
     * @return the starting row ID
     */
    Long startRowId();

    /**
     * Gets the order of this file within the table, used for sorting and
     * processing.
     * 
     * @return the file order
     */
    Optional<Long> fileOrder();

    // TODO PartitionInfo partitionInfo();

    /**
     * Gets the statistics for this data file.
     * 
     * @return a {@link DataFileStatistics} instance, or null if statistics are not
     *         available
     */
    DataFileStatistics dataFileStatistics();
}
