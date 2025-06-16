package io.github.jayhan94.ducklake.datatype;

import java.util.List;

/**
 * DataType is a class that represents a data type in the ducklake.
 * Supported data types are listed in the following link:
 * https://ducklake.select/docs/stable/specification/data_types
 */
public abstract class DataType {
    /**
     * Get the name of the data type
     * 
     * @return the name of the data type
     */
    public abstract String name();

    /**
     * Check if this is a primitive data type
     * 
     * @return true if this is a primitive data type, false otherwise
     */
    public abstract boolean isPrimitive();

    /**
     * Check if this is a nested data type
     * 
     * @return true if this is a nested data type, false otherwise
     */
    public abstract boolean isNested();

    /**
     * Check if this is an array type
     * 
     * @return true if this is an array type, false otherwise
     */
    public abstract boolean isArray();

    /**
     * Check if this is a struct type
     * 
     * @return true if this is a struct type, false otherwise
     */
    public abstract boolean isStruct();

    /**
     * Check if this is a map type
     * 
     * @return true if this is a map type, false otherwise
     */
    public abstract boolean isMap();

    /**
     * Get the children types of this data type
     * For example, for ARRAY<INT>, the children type is INT
     * For STRUCT<a:INT, b:STRING>, the children types are [INT, STRING]
     * 
     * @return the children types, or empty list if this is a primitive type
     */
    public abstract List<DataType> childrenTypes();

    @Override
    public String toString() {
        return name();
    }
}
