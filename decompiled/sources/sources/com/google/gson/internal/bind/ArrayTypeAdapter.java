package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.GsonTypes;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ArrayTypeAdapter<E> extends TypeAdapter<Object> {
    public static final TypeAdapterFactory FACTORY = null;
    private final Class<E> componentType;
    private final TypeAdapter<E> componentTypeAdapter;

    /* renamed from: com.google.gson.internal.bind.ArrayTypeAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements TypeAdapterFactory {
        public AnonymousClass1() {
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r2, TypeToken<T> r3) {
            Type r1 = r3.getType();
            if ((r1 instanceof GenericArrayType) == false) goto L5;
        L10:
            Type r12 = GsonTypes.getArrayComponentType(r1);
            return new ArrayTypeAdapter(r2, r2.getAdapter(TypeToken.get(r12)), GsonTypes.getRawType(r12));
        L5:
            if ((r1 instanceof Class) == true) goto L7;
            return null;
        L7:
            if (((Class) r1).isArray() == true) goto L10;
            return null;
        }
    }

    static {
        FACTORY = new AnonymousClass1();
    }

    public ArrayTypeAdapter(Gson r2, TypeAdapter<E> r3, Class<E> r4) {
        this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper(r2, r3, r4);
        this.componentType = r4;
    }

    @Override // com.google.gson.TypeAdapter
    public Object read(JsonReader r4) {
        if (r4.peek() != JsonToken.NULL) goto L6;
        r4.nextNull();
        return null;
    L6:
        ArrayList r0 = new ArrayList();
        r4.beginArray();
    L8:
        if (r4.hasNext() == false) goto L10;
        r0.add(this.componentTypeAdapter.read(r4));
        goto L8
    L10:
        r4.endArray();
        int r42 = r0.size();
        boolean r1 = this.componentType.isPrimitive();
        Class<E> r3 = this.componentType;
        if (r1 == false) goto L17;
        Object r32 = Array.newInstance(r3, r42);
        int r12 = 0;
    L13:
        if (r12 >= r42) goto L15;
        Array.set(r32, r12, r0.get(r12));
        r12 = r12 + 1;
        goto L13
    L15:
        return r32;
    L17:
        return r0.toArray((Object[]) Array.newInstance(r3, r42));
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter r5, Object r6) {
        if (r6 != null) goto L5;
        r5.nullValue();
        return;
    L5:
        r5.beginArray();
        int r0 = Array.getLength(r6);
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L8;
        Object r2 = Array.get(r6, r1);
        this.componentTypeAdapter.write(r5, r2);
        r1 = r1 + 1;
        goto L6
    L8:
        r5.endArray();
    }
}
