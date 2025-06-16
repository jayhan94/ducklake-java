package io.github.jayhan94.ducklake.datatype;

import java.util.Collections;
import java.util.List;

/**
 * Floating point data types
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class FloatTypes {

    private FloatTypes() {
        // Utility class
    }

    public static final class Float32Type extends DataType {
        public static final Float32Type INSTANCE = new Float32Type();

        private Float32Type() {
        }

        @Override
        public String name() {
            return "float32";
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
    }

    public static final class Float64Type extends DataType {
        public static final Float64Type INSTANCE = new Float64Type();

        private Float64Type() {
        }

        @Override
        public String name() {
            return "float64";
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
    }
}