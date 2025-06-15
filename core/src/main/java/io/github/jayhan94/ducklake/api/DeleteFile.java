package io.github.jayhan94.ducklake.api;

/**
 * DeleteFile corresponds to a DataFile and contains the row_ids that have been deleted from this DataFile
 */
public interface DeleteFile {
    Long deleteFileId();

    Long dataFileId();

    String path();

    FileFormat fileFormat();

    Long deleteCount();

    Long fileSizeBytes();

    Long footerSizeBytes();

    String encryptionKey();
}
