package io.github.jayhan94.ducklake.datatype;

import java.util.Collections;
import java.util.List;

/**
 * Represents a decimal data type with precision and scale
 * Format: decimal(P, S) where P is precision and S is scale
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class DecimalType extends DataType {

    private final int precision;
    private final int scale;

    /**
     * Create a decimal data type
     * 
     * @param precision the total number of digits
     * @param scale     the number of digits after the decimal point
     */
    public DecimalType(int precision, int scale) {
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be positive");
        }
        if (scale < 0 || scale > precision) {
            throw new IllegalArgumentException("Scale must be non-negative and not greater than precision");
        }
        this.precision = precision;
        this.scale = scale;
    }

    @Override
    public String name() {
        return String.format("decimal(%d, %d)", precision, scale);
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public boolean isNested() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isStruct() {
        return false;
    }

    @Override
    public boolean isMap() {
        return false;
    }

    @Override
    public List<DataType> childrenTypes() {
        return Collections.emptyList();
    }

    /**
     * Get the precision (total number of digits)
     * 
     * @return the precision
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Get the scale (number of digits after decimal point)
     * 
     * @return the scale
     */
    public int getScale() {
        return scale;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DecimalType that = (DecimalType) obj;
        return precision == that.precision && scale == that.scale;
    }

    @Override
    public int hashCode() {
        return 31 * precision + scale;
    }
}