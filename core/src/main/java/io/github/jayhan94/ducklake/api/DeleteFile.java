package io.github.jayhan94.ducklake.api;

/**
 * DeleteFile corresponds to a DataFile and contains the row_ids that have been deleted from this DataFile
 */
public interface DeleteFile {
    long deleteFileId();

    long dataFileId();

    String path();

    FileFormat fileFormat();

    long deleteCount();

    long fileSizeBytes();

    long footerSizeBytes();

    String encryptionKey();
}
