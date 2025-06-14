package io.github.jayhan94.ducklake;

import io.github.jayhan94.ducklake.api.DataFiles;
import io.github.jayhan94.ducklake.api.DataFile;
import java.util.List;

public class DataFilesImpl implements DataFiles {
    private final List<DataFile> dataFiles;

    public DataFilesImpl(List<DataFile> dataFiles) {
        this.dataFiles = dataFiles;
    }

    @Override
    public List<DataFile> dataFiles() {
        return dataFiles;
    }

}
