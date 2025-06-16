package io.github.jayhan94.ducklake.datatype;

import java.util.Collections;
import java.util.List;

/**
 * Temporal data types (time, date, timestamp, interval)
 * Based on: https://ducklake.select/docs/stable/specification/data_types.html
 */
public final class TemporalTypes {

    private TemporalTypes() {
        // Utility class
    }

    public static final class TimeType extends DataType {
        public static final TimeType INSTANCE = new TimeType();

        private TimeType() {
        }

        @Override
        public String name() {
            return "time";
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

    public static final class TimeTzType extends DataType {
        public static final TimeTzType INSTANCE = new TimeTzType();

        private TimeTzType() {
        }

        @Override
        public String name() {
            return "timetz";
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

    public static final class DateType extends DataType {
        public static final DateType INSTANCE = new DateType();

        private DateType() {
        }

        @Override
        public String name() {
            return "date";
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

    public static final class TimestampType extends DataType {
        public static final TimestampType INSTANCE = new TimestampType();

        private TimestampType() {
        }

        @Override
        public String name() {
            return "timestamp";
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

    public static final class TimestampTzType extends DataType {
        public static final TimestampTzType INSTANCE = new TimestampTzType();

        private TimestampTzType() {
        }

        @Override
        public String name() {
            return "timestamptz";
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

    public static final class TimestampSType extends DataType {
        public static final TimestampSType INSTANCE = new TimestampSType();

        private TimestampSType() {
        }

        @Override
        public String name() {
            return "timestamp_s";
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

    public static final class TimestampMsType extends DataType {
        public static final TimestampMsType INSTANCE = new TimestampMsType();

        private TimestampMsType() {
        }

        @Override
        public String name() {
            return "timestamp_ms";
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

    public static final class TimestampNsType extends DataType {
        public static final TimestampNsType INSTANCE = new TimestampNsType();

        private TimestampNsType() {
        }

        @Override
        public String name() {
            return "timestamp_ns";
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

    public static final class IntervalType extends DataType {
        public static final IntervalType INSTANCE = new IntervalType();

        private IntervalType() {
        }

        @Override
        public String name() {
            return "interval";
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