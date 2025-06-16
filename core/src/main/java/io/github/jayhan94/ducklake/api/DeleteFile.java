package io.github.jayhan94.ducklake.api;

/**
 * Represents a delete file, which marks specific rows in a data file as
 * deleted.
 */
public interface DeleteFile {
    /**
     * Gets the unique ID for this delete file.
     * 
     * @return the delete file ID
     */
    Long deleteFileId();

    /**
     * Gets the ID of the data file from which rows were deleted.
     * 
     * @return the corresponding data file ID
     */
    Long dataFileId();

    /**
     * Gets the path to the delete file.
     * 
     * @return the file path, which can be absolute or relative
     */
    String path();

    /**
     * Gets the format of the delete file.
     * 
     * @return the file format
     */
    FileFormat fileFormat();

    /**
     * Gets the number of rows marked as deleted in this file.
     * 
     * @return the count of deleted rows
     */
    Long deleteCount();

    /**
     * Gets the total size of the delete file in bytes.
     * 
     * @return the file size in bytes
     */
    Long fileSizeBytes();

    /**
     * Gets the size of the file's metadata footer in bytes.
     * 
     * @return the footer size in bytes
     */
    Long footerSizeBytes();

    /**
     * Gets the encryption key used for the delete file, if any.
     * 
     * @return the encryption key as a string, or null if not encrypted
     */
    String encryptionKey();
}
