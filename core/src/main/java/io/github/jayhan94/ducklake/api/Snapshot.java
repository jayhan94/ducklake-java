package io.github.jayhan94.ducklake.api;

public interface Snapshot {
    long id();

    long timestamp();

    long schemaVersion();
}
