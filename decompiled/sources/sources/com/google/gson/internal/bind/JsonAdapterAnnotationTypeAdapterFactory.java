package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private static final TypeAdapterFactory TREE_TYPE_CLASS_DUMMY_FACTORY = null;
    private static final TypeAdapterFactory TREE_TYPE_FIELD_DUMMY_FACTORY = null;
    private final ConcurrentMap<Class<?>, TypeAdapterFactory> adapterFactoryMap;
    private final ConstructorConstructor constructorConstructor;

    /* renamed from: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class DummyTypeAdapterFactory implements TypeAdapterFactory {
        private DummyTypeAdapterFactory() {
        }

        public /* synthetic */ DummyTypeAdapterFactory(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r1, TypeToken<T> r2) {
            throw new AssertionError("Factory should not be used");
        }
    }

    static {
        TREE_TYPE_CLASS_DUMMY_FACTORY = new DummyTypeAdapterFactory(null);
        TREE_TYPE_FIELD_DUMMY_FACTORY = new DummyTypeAdapterFactory(null);
    }

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor r1) {
        this.constructorConstructor = r1;
        this.adapterFactoryMap = new ConcurrentHashMap();
    }

    private static Object createAdapter(ConstructorConstructor r1, Class<?> r2) {
        return r1.get(TypeToken.get(r2), true).construct();
    }

    private static JsonAdapter getAnnotation(Class<?> r1) {
        return (JsonAdapter) r1.getAnnotation(JsonAdapter.class);
    }

    private TypeAdapterFactory putFactoryAndGetCurrent(Class<?> r1, TypeAdapterFactory r2) {
        TypeAdapterFactory r0 = this.adapterFactoryMap.putIfAbsent(r1, r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r2;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson r8, TypeToken<T> r9) {
        JsonAdapter r5 = getAnnotation(r9.getRawType());
        if (r5 != null) goto L7;
        return null;
    L7:
        return (TypeAdapter<T>) getTypeAdapter(this.constructorConstructor, r8, r9, r5, true);
    }

    public TypeAdapter<?> getTypeAdapter(ConstructorConstructor r8, Gson r9, TypeToken<?> r10, JsonAdapter r11, boolean r12) {
        Object r82 = createAdapter(r8, r11.value());
        boolean r6 = r11.nullSafe();
        if ((r82 instanceof TypeAdapter) == false) goto L6;
        TypeAdapter<?> r83 = (TypeAdapter) r82;
    L22:
        if (r83 == null) goto L26;
        if (r6 == false) goto L26;
        return r83.nullSafe();
    L26:
        return r83;
    L6:
        if ((r82 instanceof TypeAdapterFactory) == false) goto L11;
        TypeAdapterFactory r84 = (TypeAdapterFactory) r82;
        if (r12 == false) goto L10;
        r84 = putFactoryAndGetCurrent(r10.getRawType(), r84);
    L10:
        r83 = r84.create(r9, r10);
        goto L22
    L11:
        boolean r7 = r82 instanceof JsonSerializer;
        if (r7 == false) goto L28;
        if (r7 == false) goto L16;
        JsonSerializer r85 = (JsonSerializer) r82;
    L15:
        JsonSerializer r1 = r85;
        if (r12 == false) goto L20;
        TypeAdapterFactory r72 = TREE_TYPE_CLASS_DUMMY_FACTORY;
    L21:
        TreeTypeAdapter r0 = new TreeTypeAdapter(r1, null, r9, r10, r72, r6);
        r6 = false;
        r83 = r0;
        goto L22
    L20:
        r72 = TREE_TYPE_FIELD_DUMMY_FACTORY;
        goto L21
    L16:
        r85 = null;
        goto L15
    L28:
        throw new IllegalArgumentException("Invalid attempt to bind an instance of " + r82.getClass().getName() + " as a @JsonAdapter for " + r10.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
    }

    public boolean isClassJsonAdapterFactory(TypeToken<?> r5, TypeAdapterFactory r6) {
        Objects.requireNonNull(r5);
        Objects.requireNonNull(r6);
        if (r6 != TREE_TYPE_CLASS_DUMMY_FACTORY) goto L5;
        return true;
    L5:
        Class<? super Object> r52 = r5.getRawType();
        TypeAdapterFactory r0 = this.adapterFactoryMap.get(r52);
        if (r0 == null) goto L10;
        if (r0 != r6) goto L9;
        return true;
    L9:
        return false;
    L10:
        JsonAdapter r02 = getAnnotation(r52);
        if (r02 != null) goto L13;
        return false;
    L13:
        Class<?> r03 = r02.value();
        if (TypeAdapterFactory.class.isAssignableFrom(r03) == true) goto L17;
        return false;
    L17:
        if (putFactoryAndGetCurrent(r52, (TypeAdapterFactory) createAdapter(this.constructorConstructor, r03)) != r6) goto L19;
        return true;
    L19:
        return false;
    }
}
