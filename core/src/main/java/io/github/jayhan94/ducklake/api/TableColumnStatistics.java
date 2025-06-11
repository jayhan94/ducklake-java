package io.github.jayhan94.ducklake.api;

public interface TableColumnStatistics {
    boolean containsNull();

    boolean containsNaN();

    String minValue();

    String maxValue();
}