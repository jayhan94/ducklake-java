package io.github.jayhan94.ducklake.datatype;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for creating and parsing DuckLake data types
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class DataTypes {

    private static final Pattern DECIMAL_PATTERN = Pattern.compile("decimal\\((\\d+),\\s*(\\d+)\\)");
    private static final Map<String, DataType> PRIMITIVE_TYPES = new HashMap<>();

    static {
        // Register all primitive types for parsing
        PRIMITIVE_TYPES.put("boolean", BooleanType.INSTANCE);

        // Integer types
        PRIMITIVE_TYPES.put("int8", IntegerTypes.Int8Type.INSTANCE);
        PRIMITIVE_TYPES.put("int16", IntegerTypes.Int16Type.INSTANCE);
        PRIMITIVE_TYPES.put("int32", IntegerTypes.Int32Type.INSTANCE);
        PRIMITIVE_TYPES.put("int64", IntegerTypes.Int64Type.INSTANCE);
        PRIMITIVE_TYPES.put("uint8", IntegerTypes.Uint8Type.INSTANCE);
        PRIMITIVE_TYPES.put("uint16", IntegerTypes.Uint16Type.INSTANCE);
        PRIMITIVE_TYPES.put("uint32", IntegerTypes.Uint32Type.INSTANCE);
        PRIMITIVE_TYPES.put("uint64", IntegerTypes.Uint64Type.INSTANCE);

        // Float types
        PRIMITIVE_TYPES.put("float32", FloatTypes.Float32Type.INSTANCE);
        PRIMITIVE_TYPES.put("float64", FloatTypes.Float64Type.INSTANCE);

        // Temporal types
        PRIMITIVE_TYPES.put("time", TemporalTypes.TimeType.INSTANCE);
        PRIMITIVE_TYPES.put("timetz", TemporalTypes.TimeTzType.INSTANCE);
        PRIMITIVE_TYPES.put("date", TemporalTypes.DateType.INSTANCE);
        PRIMITIVE_TYPES.put("timestamp", TemporalTypes.TimestampType.INSTANCE);
        PRIMITIVE_TYPES.put("timestamptz", TemporalTypes.TimestampTzType.INSTANCE);
        PRIMITIVE_TYPES.put("timestamp_s", TemporalTypes.TimestampSType.INSTANCE);
        PRIMITIVE_TYPES.put("timestamp_ms", TemporalTypes.TimestampMsType.INSTANCE);
        PRIMITIVE_TYPES.put("timestamp_ns", TemporalTypes.TimestampNsType.INSTANCE);
        PRIMITIVE_TYPES.put("interval", TemporalTypes.IntervalType.INSTANCE);

        // String types
        PRIMITIVE_TYPES.put("varchar", StringTypes.VarcharType.INSTANCE);
        PRIMITIVE_TYPES.put("uuid", StringTypes.UuidType.INSTANCE);

        // Binary types
        PRIMITIVE_TYPES.put("blob", BinaryTypes.BlobType.INSTANCE);
    }

    private DataTypes() {
    }

    public static boolean isPrimitive(String catalogColumnTypeString) {
        return !isNested(catalogColumnTypeString);
    }

    public static boolean isNested(String catalogColumnTypeString) {
        return isMap(catalogColumnTypeString) || isList(catalogColumnTypeString)
                || isStruct(catalogColumnTypeString);
    }

    public static boolean isMap(String catalogColumnTypeString) {
        return catalogColumnTypeString.equals("map");
    }

    public static boolean isList(String catalogColumnTypeString) {
        return catalogColumnTypeString.equals("list");
    }

    public static boolean isStruct(String catalogColumnTypeString) {
        return catalogColumnTypeString.equals("struct");
    }

    // Convenience methods for primitive types
    public static BooleanType BOOLEAN() {
        return BooleanType.INSTANCE;
    }

    // Integer types
    public static IntegerTypes.Int8Type INT8() {
        return IntegerTypes.Int8Type.INSTANCE;
    }

    public static IntegerTypes.Int16Type INT16() {
        return IntegerTypes.Int16Type.INSTANCE;
    }

    public static IntegerTypes.Int32Type INT32() {
        return IntegerTypes.Int32Type.INSTANCE;
    }

    public static IntegerTypes.Int64Type INT64() {
        return IntegerTypes.Int64Type.INSTANCE;
    }

    public static IntegerTypes.Uint8Type UINT8() {
        return IntegerTypes.Uint8Type.INSTANCE;
    }

    public static IntegerTypes.Uint16Type UINT16() {
        return IntegerTypes.Uint16Type.INSTANCE;
    }

    public static IntegerTypes.Uint32Type UINT32() {
        return IntegerTypes.Uint32Type.INSTANCE;
    }

    public static IntegerTypes.Uint64Type UINT64() {
        return IntegerTypes.Uint64Type.INSTANCE;
    }

    // Float types
    public static FloatTypes.Float32Type FLOAT32() {
        return FloatTypes.Float32Type.INSTANCE;
    }

    public static FloatTypes.Float64Type FLOAT64() {
        return FloatTypes.Float64Type.INSTANCE;
    }

    // Temporal types
    public static TemporalTypes.TimeType TIME() {
        return TemporalTypes.TimeType.INSTANCE;
    }

    public static TemporalTypes.TimeTzType TIMETZ() {
        return TemporalTypes.TimeTzType.INSTANCE;
    }

    public static TemporalTypes.DateType DATE() {
        return TemporalTypes.DateType.INSTANCE;
    }

    public static TemporalTypes.TimestampType TIMESTAMP() {
        return TemporalTypes.TimestampType.INSTANCE;
    }

    public static TemporalTypes.TimestampTzType TIMESTAMPTZ() {
        return TemporalTypes.TimestampTzType.INSTANCE;
    }

    public static TemporalTypes.TimestampSType TIMESTAMP_S() {
        return TemporalTypes.TimestampSType.INSTANCE;
    }

    public static TemporalTypes.TimestampMsType TIMESTAMP_MS() {
        return TemporalTypes.TimestampMsType.INSTANCE;
    }

    public static TemporalTypes.TimestampNsType TIMESTAMP_NS() {
        return TemporalTypes.TimestampNsType.INSTANCE;
    }

    public static TemporalTypes.IntervalType INTERVAL() {
        return TemporalTypes.IntervalType.INSTANCE;
    }

    // String types
    public static StringTypes.VarcharType VARCHAR() {
        return StringTypes.VarcharType.INSTANCE;
    }

    public static StringTypes.UuidType UUID() {
        return StringTypes.UuidType.INSTANCE;
    }

    // Binary types
    public static BinaryTypes.BlobType BLOB() {
        return BinaryTypes.BlobType.INSTANCE;
    }

    /**
     * Create a decimal type with specified precision and scale
     * 
     * @param precision the precision (total number of digits)
     * @param scale     the scale (number of digits after decimal point)
     * @return a DecimalType instance
     */
    public static DecimalType decimal(int precision, int scale) {
        return new DecimalType(precision, scale);
    }

    /**
     * Create a list type with specified element type
     * 
     * @param elementType the type of elements in the list
     * @return a ListType instance
     */
    public static ListType list(DataType elementType) {
        return new ListType(elementType);
    }

    /**
     * Create a struct type with specified fields
     * 
     * @param fields the fields in the struct
     * @return a StructType instance
     */
    public static StructType struct(StructType.StructField... fields) {
        return new StructType(Arrays.asList(fields));
    }

    /**
     * Create a struct type with specified fields
     * 
     * @param fields the fields in the struct
     * @return a StructType instance
     */
    public static StructType struct(List<StructType.StructField> fields) {
        return new StructType(fields);
    }

    /**
     * Create a struct field
     * 
     * @param name the field name
     * @param type the field type
     * @return a StructField instance
     */
    public static StructType.StructField field(String name, DataType type) {
        return new StructType.StructField(name, type);
    }

    /**
     * Create a map type with specified key and value types
     * 
     * @param keyType   the type of keys in the map
     * @param valueType the type of values in the map
     * @return a MapType instance
     */
    public static MapType map(DataType keyType, DataType valueType) {
        return new MapType(keyType, valueType);
    }

    /**
     * Parse a data type from its string representation
     * 
     * @param typeString the type string (e.g., "int32", "decimal(10, 2)",
     *                   "list<int32>")
     * @return the parsed DataType
     * @throws IllegalArgumentException if the type string is invalid
     */
    public static DataType parseType(String typeString) {
        if (typeString == null || typeString.trim().isEmpty()) {
            throw new IllegalArgumentException("Type string cannot be null or empty");
        }

        String trimmed = typeString.trim();

        // Try to parse as decimal first
        Matcher decimalMatcher = DECIMAL_PATTERN.matcher(trimmed);
        if (decimalMatcher.matches()) {
            int precision = Integer.parseInt(decimalMatcher.group(1));
            int scale = Integer.parseInt(decimalMatcher.group(2));
            return decimal(precision, scale);
        }

        // Try to parse as primitive type
        DataType primitiveType = PRIMITIVE_TYPES.get(trimmed);
        if (primitiveType != null) {
            return primitiveType;
        }

        // Handle nested types (simplified parsing)
        if (trimmed.startsWith("list<") && trimmed.endsWith(">")) {
            String elementTypeStr = trimmed.substring(5, trimmed.length() - 1);
            DataType elementType = parseType(elementTypeStr);
            return list(elementType);
        }

        if (trimmed.startsWith("map<") && trimmed.endsWith(">")) {
            String content = trimmed.substring(4, trimmed.length() - 1);
            int commaIndex = findTopLevelComma(content);
            if (commaIndex == -1) {
                throw new IllegalArgumentException("Invalid map type format: " + typeString);
            }
            String keyTypeStr = content.substring(0, commaIndex).trim();
            String valueTypeStr = content.substring(commaIndex + 1).trim();
            DataType keyType = parseType(keyTypeStr);
            DataType valueType = parseType(valueTypeStr);
            return map(keyType, valueType);
        }

        // Struct parsing would be more complex, not implemented in this basic version
        throw new IllegalArgumentException("Unsupported type format: " + typeString);
    }

    /**
     * Find the top-level comma in a type string (not inside nested angle brackets)
     */
    private static int findTopLevelComma(String str) {
        int angleDepth = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '<') {
                angleDepth++;
            } else if (c == '>') {
                angleDepth--;
            } else if (c == ',' && angleDepth == 0) {
                return i;
            }
        }
        return -1;
    }
}