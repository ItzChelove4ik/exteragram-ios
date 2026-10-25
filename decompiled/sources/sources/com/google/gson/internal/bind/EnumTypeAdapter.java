package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.lang.Enum;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
    static final TypeAdapterFactory FACTORY = null;
    private final Map<T, String> constantToName;
    private final Map<String, T> nameToConstant;
    private final Map<String, T> stringToConstant;

    /* renamed from: com.google.gson.internal.bind.EnumTypeAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements TypeAdapterFactory {
        public AnonymousClass1() {
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r2, TypeToken<T> r3) {
            Class<? super T> r1 = r3.getRawType();
            if (Enum.class.isAssignableFrom(r1) == false) goto L11;
            if (r1 == Enum.class) goto L11;
            if (r1.isEnum() == true) goto L10;
            r1 = r1.getSuperclass();
        L10:
            return new EnumTypeAdapter(r1, null);
        L11:
            return null;
        }
    }

    static {
        FACTORY = new AnonymousClass1();
    }

    private EnumTypeAdapter(Class<T> r13) {
        Field[] r132 = r13.getDeclaredFields();     // Catch: IllegalAccessException -> L8
        int r1 = r132.length;     // Catch: IllegalAccessException -> L8
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r3 >= r1) goto L11;
        Field r5 = r132[r3];     // Catch: IllegalAccessException -> L8
        if (r5.isEnumConstant() == false) goto L10;
        r132[r4] = r5;     // Catch: IllegalAccessException -> L8
        r4 = r4 + 1;     // Catch: IllegalAccessException -> L8
    L10:
        r3 = r3 + 1;     // Catch: IllegalAccessException -> L8
        goto L4
    L11:
        Field[] r133 = (Field[]) Arrays.copyOf(r132, r4);     // Catch: IllegalAccessException -> L8
        int r12 = calculateHashMapCapacity(r4);     // Catch: IllegalAccessException -> L8
        this.nameToConstant = new HashMap(r12);     // Catch: IllegalAccessException -> L8
        this.stringToConstant = new HashMap(r12);     // Catch: IllegalAccessException -> L8
        this.constantToName = new HashMap(r12);     // Catch: IllegalAccessException -> L8
        AccessibleObject.setAccessible(r133, true);     // Catch: IllegalAccessException -> L8
        int r14 = r133.length;     // Catch: IllegalAccessException -> L8
        int r32 = 0;
    L12:
        if (r32 >= r14) goto L20;
        Field r42 = r133[r32];     // Catch: IllegalAccessException -> L8
        Enum r52 = (Enum) r42.get(null);     // Catch: IllegalAccessException -> L8
        String r6 = r52.name();     // Catch: IllegalAccessException -> L8
        String r7 = r52.toString();     // Catch: IllegalAccessException -> L8
        SerializedName r43 = (SerializedName) r42.getAnnotation(SerializedName.class);     // Catch: IllegalAccessException -> L8
        if (r43 == null) goto L18;
        r6 = r43.value();     // Catch: IllegalAccessException -> L8
        String[] r44 = r43.alternate();     // Catch: IllegalAccessException -> L8
        int r8 = r44.length;     // Catch: IllegalAccessException -> L8
        int r9 = 0;
    L16:
        if (r9 >= r8) goto L18;
        this.nameToConstant.put(r44[r9], r52);     // Catch: IllegalAccessException -> L8
        r9 = r9 + 1;     // Catch: IllegalAccessException -> L8
    L18:
        this.nameToConstant.put(r6, r52);     // Catch: IllegalAccessException -> L8
        this.stringToConstant.put(r7, r52);     // Catch: IllegalAccessException -> L8
        this.constantToName.put(r52, r6);     // Catch: IllegalAccessException -> L8
        r32 = r32 + 1;
        goto L12
    L20:
        return;
    L8:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        throw null;
    }

    public /* synthetic */ EnumTypeAdapter(Class r1, AnonymousClass1 r2) {
        this(r1);
    }

    private static int calculateHashMapCapacity(int r2) {
        return (int) Math.ceil(r2 / 0.75f);
    }

    @Override // com.google.gson.TypeAdapter
    public T read(JsonReader r3) {
        if (r3.peek() != JsonToken.NULL) goto L6;
        r3.nextNull();
        return null;
    L6:
        String r32 = r3.nextString();
        T r0 = this.nameToConstant.get(r32);
        if (r0 == null) goto L9;
        return r0;
    L9:
        return this.stringToConstant.get(r32);
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ Object read(JsonReader r1) {
        return read(r1);
    }

    public void write(JsonWriter r1, T r2) {
        if (r2 != null) goto L4;
        String r0 = null;
    L5:
        r1.value(r0);
        return;
    L4:
        r0 = this.constantToName.get(r2);
        goto L5
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ void write(JsonWriter r1, Object r2) {
        write(r1, (Enum) r2);
    }
}
