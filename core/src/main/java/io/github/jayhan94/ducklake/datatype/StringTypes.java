package io.github.jayhan94.ducklake.datatype;

import java.util.Collections;
import java.util.List;

/**
 * String data types
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class StringTypes {

    private StringTypes() {
        // Utility class
    }

    public static final class VarcharType implements DataType {
        public static final VarcharType INSTANCE = new VarcharType();

        private VarcharType() {
        }

        @Override
        public String name() {
            return "varchar";
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

    public static final class UuidType implements DataType {
        public static final UuidType INSTANCE = new UuidType();

        private UuidType() {
        }

        @Override
        public String name() {
            return "uuid";
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
}