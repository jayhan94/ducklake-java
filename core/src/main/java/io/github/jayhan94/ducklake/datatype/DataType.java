package io.github.jayhan94.ducklake.datatype;

import java.util.List;

/**
 * DataType is a class that represents a data type in the ducklake.
 * Supported data types are listed in the following link:
 * https://ducklake.select/docs/stable/specification/data_types
 */
public interface DataType {
    /**
     * Get the name of the data type
     * @return the name of the data type
     */
    String name();

    /**
     * Check if this is a primitive data type
     * @return true if this is a primitive data type, false otherwise
     */
    boolean isPrimitive();

    /**
     * Check if this is a nested data type
     * @return true if this is a nested data type, false otherwise
     */
    boolean isNested();

    /**
     * Check if this is an array type
     * @return true if this is an array type, false otherwise
     */
    boolean isArray();

    /**
     * Check if this is a struct type
     * @return true if this is a struct type, false otherwise
     */
    boolean isStruct();

    /**
     * Check if this is a map type
     * @return true if this is a map type, false otherwise
     */
    boolean isMap();

    /**
     * Get the children types of this data type
     * For example, for ARRAY<INT>, the children type is INT
     * For STRUCT<a:INT, b:STRING>, the children types are [INT, STRING]
     * @return the children types, or empty list if this is a primitive type
     */
    List<DataType> childrenTypes();
}
