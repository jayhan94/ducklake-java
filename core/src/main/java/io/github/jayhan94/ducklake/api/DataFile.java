package io.github.jayhan94.ducklake.api;

public interface DataFile extends PrettyPrint {

    /**
     * Pretty print the data file
     * 
     * @return the pretty string
     */
    @Override
    default String prettyString() {
        return "DataFile{" +
                "dataFileId=" + dataFileId() +
                ", tableId=" + tableId() +
                ", deleteFile=" + deleteFile() +
                ", dataFileStatistics=" + dataFileStatistics() +
                ", path='" + path() + '\'' +
                ", fileFormat=" + fileFormat() +
                ", rowCount=" + rowCount() +
                ", fileSizeBytes=" + fileSizeBytes() +
                ", footerSizeBytes=" + footerSizeBytes() +
                ", startRowId=" + startRowId() +
                ", fileOrder=" + fileOrder() +
                "}";
    }

    Long dataFileId();

    Long tableId();

    DeleteFile deleteFile();

    DataFileStatistics dataFileStatistics();

    String path();

    FileFormat fileFormat();

    Long rowCount();

    Long fileSizeBytes();

    Long footerSizeBytes();

    Long startRowId();

    Long fileOrder();

    // TODO PartitionInfo partitionInfo();
}
