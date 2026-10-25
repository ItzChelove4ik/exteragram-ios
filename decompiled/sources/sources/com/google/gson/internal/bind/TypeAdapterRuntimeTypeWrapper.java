package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* loaded from: classes.dex */
final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final Gson context;
    private final TypeAdapter<T> delegate;
    private final Type type;

    public TypeAdapterRuntimeTypeWrapper(Gson r1, TypeAdapter<T> r2, Type r3) {
        this.context = r1;
        this.delegate = r2;
        this.type = r3;
    }

    private static Type getRuntimeTypeIfMoreSpecific(Type r1, Object r2) {
        if (r2 != null) goto L4;
        return r1;
    L4:
        if ((r1 instanceof Class) == true) goto L8;
        if ((r1 instanceof TypeVariable) == true) goto L8;
        return r1;
    L8:
        return r2.getClass();
    }

    private static boolean isReflective(TypeAdapter<?> r1) {
    L3:
        if ((r1 instanceof SerializationDelegatingTypeAdapter) == false) goto L9;
        TypeAdapter<?> r0 = ((SerializationDelegatingTypeAdapter) r1).getSerializationDelegate();
        if (r0 == r1) goto L9;
        r1 = r0;
    L9:
        return r1 instanceof ReflectiveTypeAdapterFactory.Adapter;
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader r1) {
        return this.delegate.read(r1);
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter r4, T r5) {
        TypeAdapter<T> r0 = this.delegate;
        Type r1 = getRuntimeTypeIfMoreSpecific(this.type, r5);
        if (r1 == this.type) goto L10;
        r0 = this.context.getAdapter(TypeToken.get(r1));
        if ((r0 instanceof ReflectiveTypeAdapterFactory.Adapter) == false) goto L10;
        if (isReflective(this.delegate) == true) goto L10;
        r0 = this.delegate;
    L10:
        r0.write(r4, r5);
    }
}
