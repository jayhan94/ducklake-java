package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.DeleteFile;
import io.github.jayhan94.ducklake.api.FileFormat;

public class DeleteFileImpl implements DeleteFile {

    private final long deleteFileId;
    private final long dataFileId;
    private final String path;
    private final FileFormat fileFormat;
    private final long deleteCount;
    private final long fileSizeBytes;
    private final long footerSizeBytes;
    private final String encryptionKey;

    public DeleteFileImpl(long deleteFileId, long dataFileId, String path, FileFormat fileFormat,
            long deleteCount, long fileSizeBytes, long footerSizeBytes, String encryptionKey) {
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
    public long deleteFileId() {
        return deleteFileId;
    }

    @Override
    public long dataFileId() {
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
    public long deleteCount() {
        return deleteCount;
    }

    @Override
    public long fileSizeBytes() {
        return fileSizeBytes;
    }

    @Override
    public long footerSizeBytes() {
        return footerSizeBytes;
    }

    @Override
    public String encryptionKey() {
        return encryptionKey;
    }

}
