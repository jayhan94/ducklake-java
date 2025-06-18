package io.github.jayhan94.ducklake.util.json;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public class OptionalAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if (typeToken.getRawType() != Optional.class) {
            return null;
        }

        final ParameterizedType parameterizedType = (ParameterizedType) typeToken.getType();
        final Type innerType = parameterizedType.getActualTypeArguments()[0];

        @SuppressWarnings("unchecked")
        TypeAdapter<T> result = (TypeAdapter<T>) new OptionalTypeAdapter<>(gson, innerType);
        return result.nullSafe();
    }

    private static class OptionalTypeAdapter<E> extends TypeAdapter<Optional<E>> {
        private final Gson gson;
        private final Type innerType;

        OptionalTypeAdapter(Gson gson, Type innerType) {
            this.gson = gson;
            this.innerType = innerType;
        }

        @Override
        public void write(JsonWriter out, Optional<E> value) throws IOException {
            if (value != null && value.isPresent()) {
                E innerValue = value.get();
                // Dynamically get the adapter for the actual runtime type of the value
                TypeAdapter<E> delegate = (TypeAdapter<E>) gson.getAdapter(innerValue.getClass());
                delegate.write(out, innerValue);
            } else {
                out.nullValue();
            }
        }

        @Override
        public Optional<E> read(JsonReader in) throws IOException {
            // Deserialization for interfaces can be complex, but let's provide a basic
            // implementation.
            TypeAdapter<E> delegate = (TypeAdapter<E>) gson.getAdapter(TypeToken.get(innerType));
            E value = delegate.read(in);
            return Optional.ofNullable(value);
        }
    }
}