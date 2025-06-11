package io.github.jayhan94.ducklake.datatype;

import java.util.Collections;
import java.util.List;

/**
 * Boolean data type
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class BooleanType implements DataType {

    public static final BooleanType INSTANCE = new BooleanType();

    private BooleanType() {
        // Singleton
    }

    @Override
    public String name() {
        return "boolean";
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

    @Override
    public String toString() {
        return name();
    }
}