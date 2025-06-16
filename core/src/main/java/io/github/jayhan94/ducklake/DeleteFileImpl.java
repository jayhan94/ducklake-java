package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.DeleteFile;
import io.github.jayhan94.ducklake.api.FileFormat;

public class DeleteFileImpl implements DeleteFile {

    private final Long deleteFileId;
    private final Long dataFileId;
    private final String path;
    private final FileFormat fileFormat;
    private final Long deleteCount;
    private final Long fileSizeBytes;
    private final Long footerSizeBytes;
    private final String encryptionKey;

    public DeleteFileImpl(
            Long deleteFileId, 
            Long dataFileId,
            String path,
            FileFormat fileFormat,
            Long deleteCount,
            Long fileSizeBytes,
            Long footerSizeBytes,
            String encryptionKey) {
        this.deleteFileId = deleteFileId;
        this.dataFileId = dataFileId;
        this.path = path;
        this.fileFormat = fileFormat;
        this.deleteCount = deleteCount;
        this.fileSizeBytes = fileSizeBytes;
        this.footerSizeBytes = footerSizeBytes;
        this.encryptionKey = encryptionKey;
    }

    @Override
    public String toString() {
        return prettyString();
    }

    @Override
    public Long deleteFileId() {
        return deleteFileId;
    }

    @Override
    public Long dataFileId() {
        return dataFileId;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public FileFormat fileFormat() {
        return fileFormat;
    }

    @Override
    public Long deleteCount() {
        return deleteCount;
    }

    @Override
    public Long fileSizeBytes() {
        return fileSizeBytes;
    }

    @Override
    public Long footerSizeBytes() {
        return footerSizeBytes;
    }

    @Override
    public String encryptionKey() {
        return encryptionKey;
    }

}
