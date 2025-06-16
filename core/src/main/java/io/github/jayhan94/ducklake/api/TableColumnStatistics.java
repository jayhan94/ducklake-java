package io.github.jayhan94.ducklake.api;

public interface TableColumnStatistics extends PrettyPrint {
    @Override
    default String prettyString() {
        return "TableColumnStatistics{" +
                "containsNull=" + containsNull() +
                ", containsNaN=" + containsNaN() +
                ", minValue=" + minValue() +
                ", maxValue=" + maxValue() +
                "}";
    }

    /**
     * Check if the column contains null values
     * 
     * @return true if the column contains null values
     */
    boolean containsNull();

    /**
     * Check if the column contains NaN values
     * 
     * @return true if the column contains NaN values
     */
    boolean containsNaN();

    /**
     * Get the minimum value of the column
     * 
     * @return the minimum value of the column
     */
    String minValue();

    /**
     * Get the maximum value of the column
     * 
     * @return the maximum value of the column
     */
    String maxValue();
}