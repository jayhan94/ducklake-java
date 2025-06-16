package io.github.jayhan94.ducklake.api;

import java.util.List;

/**
 * Represents statistics for a single data file, such as record counts and file
 * sizes.
 * <p>
 * This is often a component of a {@link DataFile} instance.
 */
public interface DataFileStatistics {
    /**
     * Gets the total number of records (rows) in the data file.
     * 
     * @return the record count
     */
    long recordCount();

    /**
     * Gets the total size of the data file in bytes.
     * 
     * @return the file size in bytes
     */
    long fileSizeBytes();

    /**
     * Gets the size of the file's metadata footer in bytes.
     * This is an optimization that can allow for faster reading of file metadata.
     * 
     * @return the footer size in bytes
     */
    long footerSizeBytes();

    /**
     * Gets the list of column-level statistics for this data file.
     * 
     * @return a list of {@link FileColumnStatistics}
     */
    List<FileColumnStatistics> fileColumnStatistics();

}
