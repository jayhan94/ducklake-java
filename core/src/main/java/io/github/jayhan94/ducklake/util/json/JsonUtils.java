package io.github.jayhan94.ducklake.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.jayhan94.ducklake.datatype.DataType;

public final class JsonUtils {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapterFactory(new OptionalAdapterFactory())
            .registerTypeAdapter(DataType.class, new DataTypeAdapter())
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    private JsonUtils() {
    }

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }
}