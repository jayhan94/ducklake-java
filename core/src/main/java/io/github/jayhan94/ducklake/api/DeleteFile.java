package io.github.jayhan94.ducklake.api;

/**
 * DeleteFile corresponds to a DataFile and contains the row_ids that have been deleted from this DataFile
 */
public interface DeleteFile extends PrettyPrint {
    @Override
    default String prettyString() {
        return "DeleteFile(" +
                "deleteFileId=" + deleteFileId() +
                ", dataFileId=" + dataFileId() +
                ", path=" + path() +
                ", fileFormat=" + fileFormat() +
                ", deleteCount=" + deleteCount() +
                ", fileSizeBytes=" + fileSizeBytes() +
                ", footerSizeBytes=" + footerSizeBytes() +
                ", encryptionKey=" + encryptionKey() +
                ")";
    }

    /**
     * The id of the delete file
     */
    Long deleteFileId();

    /**
     * The id of the data file
     */
    Long dataFileId();

    /**
     * The path of the data file
     */
    String path();

    /**
     * The format of the data file
     */
    FileFormat fileFormat();

    /**
     * The number of rows that have been deleted from the data file
     */
    Long deleteCount();

    /**
     * The size of the data file
     */
    Long fileSizeBytes();

    /**
     * The size of the footer of the data file
     */
    Long footerSizeBytes();

    /**
     * The encryption key of the data file
     */
    String encryptionKey();
}
