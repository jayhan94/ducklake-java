package io.github.jayhan94.ducklake.datatype;

import java.util.Collections;
import java.util.List;

/**
 * Binary data types
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class BinaryTypes {

    private BinaryTypes() {
        // Utility class
    }

    public static final class BlobType extends DataType {
        public static final BlobType INSTANCE = new BlobType();

        private BlobType() {
        }

        @Override
        public String name() {
            return "blob";
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