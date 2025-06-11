package io.github.jayhan94.ducklake.datatype;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a map data type - a collection of key-value pairs
 * Maps have a key type and a value type
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class MapType implements DataType {

    private final DataType keyType;
    private final DataType valueType;

    /**
     * Create a map data type
     * 
     * @param keyType   the type of keys in the map
     * @param valueType the type of values in the map
     */
    public MapType(DataType keyType, DataType valueType) {
        if (keyType == null) {
            throw new IllegalArgumentException("Key type cannot be null");
        }
        if (valueType == null) {
            throw new IllegalArgumentException("Value type cannot be null");
        }
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public String name() {
        return "map<" + keyType.name() + ", " + valueType.name() + ">";
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
        return false;
    }

    @Override
    public boolean isStruct() {
        return false;
    }

    @Override
    public boolean isMap() {
        return true;
    }

    @Override
    public List<DataType> childrenTypes() {
        return Arrays.asList(keyType, valueType);
    }

    /**
     * Get the key type of this map
     * 
     * @return the key type
     */
    public DataType getKeyType() {
        return keyType;
    }

    /**
     * Get the value type of this map
     * 
     * @return the value type
     */
    public DataType getValueType() {
        return valueType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MapType mapType = (MapType) obj;
        return keyType.equals(mapType.keyType) && valueType.equals(mapType.valueType);
    }

    @Override
    public int hashCode() {
        return 31 * keyType.hashCode() + valueType.hashCode();
    }

    @Override
    public String toString() {
        return name();
    }
}