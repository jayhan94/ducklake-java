package io.github.jayhan94.ducklake.datatype;

import java.util.Collections;
import java.util.List;

/**
 * Represents a list data type - collection of values with a single child type
 * Example: INT[] is represented as list with child type int32
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class ListType implements DataType {

    private final DataType elementType;

    /**
     * Create a list data type
     * 
     * @param elementType the type of elements in the list
     */
    public ListType(DataType elementType) {
        if (elementType == null) {
            throw new IllegalArgumentException("Element type cannot be null");
        }
        this.elementType = elementType;
    }

    @Override
    public String name() {
        return "list<" + elementType.name() + ">";
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public boolean isNested() {
        return true;
    }

    @Override
    public boolean isArray() {
        return true;
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
        return Collections.singletonList(elementType);
    }

    /**
     * Get the element type of this list
     * 
     * @return the element type
     */
    public DataType getElementType() {
        return elementType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ListType listType = (ListType) obj;
        return elementType.equals(listType.elementType);
    }

    @Override
    public int hashCode() {
        return elementType.hashCode();
    }

    @Override
    public String toString() {
        return name();
    }
}