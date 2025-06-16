package io.github.jayhan94.ducklake.datatype;

import java.util.Collections;
import java.util.List;

/**
 * Integer data types (signed and unsigned)
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class IntegerTypes {

    private IntegerTypes() {
        // Utility class
    }

    // Signed integer types
    public static final class Int8Type extends DataType {
        public static final Int8Type INSTANCE = new Int8Type();

        private Int8Type() {
        }

        @Override
        public String name() {
            return "int8";
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

    public static final class Int16Type extends DataType {
        public static final Int16Type INSTANCE = new Int16Type();

        private Int16Type() {
        }

        @Override
        public String name() {
            return "int16";
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

    public static final class Int32Type extends DataType {
        public static final Int32Type INSTANCE = new Int32Type();

        private Int32Type() {
        }

        @Override
        public String name() {
            return "int32";
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

    public static final class Int64Type extends DataType {
        public static final Int64Type INSTANCE = new Int64Type();

        private Int64Type() {
        }

        @Override
        public String name() {
            return "int64";
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

    // Unsigned integer types
    public static final class Uint8Type extends DataType {
        public static final Uint8Type INSTANCE = new Uint8Type();

        private Uint8Type() {
        }

        @Override
        public String name() {
            return "uint8";
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

    public static final class Uint16Type extends DataType {
        public static final Uint16Type INSTANCE = new Uint16Type();

        private Uint16Type() {
        }

        @Override
        public String name() {
            return "uint16";
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

    public static final class Uint32Type extends DataType {
        public static final Uint32Type INSTANCE = new Uint32Type();

        private Uint32Type() {
        }

        @Override
        public String name() {
            return "uint32";
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

    public static final class Uint64Type extends DataType {
        public static final Uint64Type INSTANCE = new Uint64Type();

        private Uint64Type() {
        }

        @Override
        public String name() {
            return "uint64";
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