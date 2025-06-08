package io.github.jayhan94.ducklake.api;

public interface Transaction {
    void start();

    void commit();

    void rollback();
}
