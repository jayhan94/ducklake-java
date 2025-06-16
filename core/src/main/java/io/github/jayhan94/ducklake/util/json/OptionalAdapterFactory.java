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
        final TypeAdapter<?> delegate = gson.getAdapter(TypeToken.get(innerType));

        @SuppressWarnings("unchecked")
        TypeAdapter<T> result = (TypeAdapter<T>) new OptionalTypeAdapter<>(delegate);
        return result.nullSafe();
    }

    private static class OptionalTypeAdapter<E> extends TypeAdapter<Optional<E>> {
        private final TypeAdapter<E> delegate;

        OptionalTypeAdapter(TypeAdapter<E> delegate) {
            this.delegate = delegate;
        }

        @Override
        public void write(JsonWriter out, Optional<E> value) throws IOException {
            if (value != null && value.isPresent()) {
                delegate.write(out, value.get());
            } else {
                out.nullValue();
            }
        }

        @Override
        public Optional<E> read(JsonReader in) throws IOException {
            E value = delegate.read(in);
            return Optional.ofNullable(value);
        }
    }
}