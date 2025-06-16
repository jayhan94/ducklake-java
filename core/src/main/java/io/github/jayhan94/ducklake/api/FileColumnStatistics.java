package io.github.jayhan94.ducklake.api;

public interface FileColumnStatistics {
    /**
     * The id of the data file
     */
    long dataFileId();

    /**
     * The id of the table
     */
    long tableId();

    /**
     * The id of the column
     */
    long columnId();

    /**
     * The size of the column
     */
    long columnSizeBytes();

    /**
     * The number of top level values in the column
     */
    long valueCount();

    /**
     * The number of nulls in the column
     */
    long nullCount();

    /**
     * The number of NaN values in the column
     */
    long nanCount();

    /**
     * The minimum value in the column
     */
    String minValue();

    /**
     * The maximum value in the column
     */
    String maxValue();

    /**
     * Whether the column contains NaN values
     */
    boolean containsNaN();
}
