package io.github.jayhan94.ducklake.util.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.jayhan94.ducklake.datatype.DataType;

import java.lang.reflect.Type;

public class DataTypeAdapter implements JsonSerializer<DataType> {
    @Override
    public JsonElement serialize(DataType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}