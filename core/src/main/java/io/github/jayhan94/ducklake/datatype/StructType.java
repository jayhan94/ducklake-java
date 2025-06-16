package io.github.jayhan94.ducklake.datatype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a struct data type - a tuple of typed values
 * Each field has a name and a data type
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class StructType extends DataType {

    private final List<StructField> fields;

    /**
     * Create a struct data type
     * 
     * @param fields the list of fields in the struct
     */
    public StructType(List<StructField> fields) {
        if (fields == null || fields.isEmpty()) {
            throw new IllegalArgumentException("Struct must have at least one field");
        }
        this.fields = Collections.unmodifiableList(new ArrayList<>(fields));
    }

    @Override
    public String name() {
        String fieldsStr = fields.stream()
                .map(field -> field.getName() + ": " + field.getType().name())
                .collect(Collectors.joining(", "));
        return "struct<" + fieldsStr + ">";
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
        return true;
    }

    @Override
    public boolean isMap() {
        return false;
    }

    @Override
    public List<DataType> childrenTypes() {
        return fields.stream()
                .map(StructField::getType)
                .collect(Collectors.toList());
    }

    /**
     * Get the list of fields in this struct
     * 
     * @return unmodifiable list of fields
     */
    public List<StructField> getFields() {
        return fields;
    }

    /**
     * Get a field by name
     * 
     * @param name the field name
     * @return the field, or null if not found
     */
    public StructField getField(String name) {
        return fields.stream()
                .filter(field -> field.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        StructType that = (StructType) obj;
        return fields.equals(that.fields);
    }

    @Override
    public int hashCode() {
        return fields.hashCode();
    }

    /**
     * Represents a field in a struct
     */
    public static final class StructField {
        private final String name;
        private final DataType type;

        /**
         * Create a struct field
         * 
         * @param name the field name
         * @param type the field data type
         */
        public StructField(String name, DataType type) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Field name cannot be null or empty");
            }
            if (type == null) {
                throw new IllegalArgumentException("Field type cannot be null");
            }
            this.name = name.trim();
            this.type = type;
        }

        /**
         * Get the field name
         * 
         * @return the field name
         */
        public String getName() {
            return name;
        }

        /**
         * Get the field data type
         * 
         * @return the field data type
         */
        public DataType getType() {
            return type;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            StructField that = (StructField) obj;
            return name.equals(that.name) && type.equals(that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type);
        }
    }
}