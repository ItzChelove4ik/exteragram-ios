package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.SerializationDelegatingTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class Gson {
    final List<TypeAdapterFactory> builderFactories;
    final List<TypeAdapterFactory> builderHierarchyFactories;
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;
    final String datePattern;
    final int dateStyle;
    final Excluder excluder;
    final List<TypeAdapterFactory> factories;
    final FieldNamingStrategy fieldNamingStrategy;
    final FormattingStyle formattingStyle;
    final boolean generateNonExecutableJson;
    final boolean htmlSafe;
    final Map<Type, Object> instanceCreators;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    final LongSerializationPolicy longSerializationPolicy;
    final ToNumberStrategy numberToNumberStrategy;
    final ToNumberStrategy objectToNumberStrategy;
    final List<Object> reflectionFilters;
    final boolean serializeNulls;
    final boolean serializeSpecialFloatingPointValues;
    final Strictness strictness;
    private final ThreadLocal<Map<TypeToken<?>, TypeAdapter<?>>> threadLocalAdapterResults;
    final int timeStyle;
    private final ConcurrentMap<TypeToken<?>, TypeAdapter<?>> typeTokenCache;
    final boolean useJdkUnsafe;

    public static class FutureTypeAdapter<T> extends SerializationDelegatingTypeAdapter<T> {
        private TypeAdapter<T> delegate;

        public FutureTypeAdapter() {
            this.delegate = null;
        }

        private TypeAdapter<T> delegate() {
            TypeAdapter<T> r0 = this.delegate;
            if (r0 == null) goto L5;
            return r0;
        L5:
            Segment$$ExternalSyntheticBUOutline1.m("Adapter for type with cyclic dependency has been used before dependency has been resolved");
            return null;
        }

        @Override // com.google.gson.internal.bind.SerializationDelegatingTypeAdapter
        public TypeAdapter<T> getSerializationDelegate() {
            return delegate();
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader r1) {
            return delegate().read(r1);
        }

        public void setDelegate(TypeAdapter<T> r2) {
            if (this.delegate != null) goto L6;
            this.delegate = r2;
            return;
        L6:
            Buffer$$ExternalSyntheticBUOutline2.m("Delegate is already set");
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter r1, T r2) {
            delegate().write(r1, r2);
        }
    }

    public Gson() {
        this(GsonBuilder.DEFAULT);
    }

    public Gson(GsonBuilder r5) {
        this.threadLocalAdapterResults = new ThreadLocal();
        this.typeTokenCache = new ConcurrentHashMap();
        this.excluder = r5.excluder;
        this.fieldNamingStrategy = r5.fieldNamingPolicy;
        HashMap r0 = new HashMap(r5.instanceCreators);
        this.instanceCreators = r0;
        this.serializeNulls = r5.serializeNulls;
        this.complexMapKeySerialization = r5.complexMapKeySerialization;
        this.generateNonExecutableJson = r5.generateNonExecutableJson;
        this.htmlSafe = r5.escapeHtmlChars;
        this.formattingStyle = r5.formattingStyle;
        this.strictness = r5.strictness;
        this.serializeSpecialFloatingPointValues = r5.serializeSpecialFloatingPointValues;
        boolean r1 = r5.useJdkUnsafe;
        this.useJdkUnsafe = r1;
        this.longSerializationPolicy = r5.longSerializationPolicy;
        this.datePattern = r5.datePattern;
        this.dateStyle = r5.dateStyle;
        this.timeStyle = r5.timeStyle;
        this.builderFactories = GsonBuilder.newImmutableList(r5.factories);
        this.builderHierarchyFactories = GsonBuilder.newImmutableList(r5.hierarchyFactories);
        this.objectToNumberStrategy = r5.objectToNumberStrategy;
        this.numberToNumberStrategy = r5.numberToNumberStrategy;
        List<Object> r2 = GsonBuilder.newImmutableList(r5.reflectionFilters);
        this.reflectionFilters = r2;
        if (r5 != GsonBuilder.DEFAULT) goto L6;
        this.constructorConstructor = GsonBuilder.DEFAULT_CONSTRUCTOR_CONSTRUCTOR;
        this.jsonAdapterFactory = GsonBuilder.DEFAULT_JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY;
        this.factories = GsonBuilder.DEFAULT_TYPE_ADAPTER_FACTORIES;
        return;
    L6:
        ConstructorConstructor r3 = new ConstructorConstructor(r0, r1, r2);
        this.constructorConstructor = r3;
        JsonAdapterAnnotationTypeAdapterFactory r02 = new JsonAdapterAnnotationTypeAdapterFactory(r3);
        this.jsonAdapterFactory = r02;
        this.factories = r5.createFactories(r3, r02);
    }

    private static void assertFullConsumption(Object r0, JsonReader r1) {
        /*  JADX ERROR: Simple mode code generation failed
            java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
            	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
            	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
            	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
            	at java.base/java.util.Objects.checkIndex(Objects.java:385)
            	at java.base/java.util.ArrayList.get(ArrayList.java:427)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:361)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */
        /*
            if (r0 == 0) goto L21
            com.google.gson.stream.JsonToken r0 = r1.peek()     // Catch: java.io.IOException -> L13 com.google.gson.stream.MalformedJsonException -> L1a
            com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.END_DOCUMENT     // Catch: java.io.IOException -> L13 com.google.gson.stream.MalformedJsonException -> L1a
            if (r0 != r1) goto Lb
            goto L21
        Lb:
            com.google.gson.JsonSyntaxException r0 = new com.google.gson.JsonSyntaxException     // Catch: java.io.IOException -> L13 com.google.gson.stream.MalformedJsonException -> L1a
            java.lang.String r1 = "JSON document was not fully consumed."
            r0.<init>(r1)     // Catch: java.io.IOException -> L13 com.google.gson.stream.MalformedJsonException -> L1a
            throw r0     // Catch: java.io.IOException -> L13 com.google.gson.stream.MalformedJsonException -> L1a
        L13:
            r0 = move-exception
            com.google.gson.JsonIOException r1 = new com.google.gson.JsonIOException
            r1.<init>(r0)
            throw r1
        L1a:
            r0 = move-exception
            com.google.gson.JsonSyntaxException r1 = new com.google.gson.JsonSyntaxException
            r1.<init>(r0)
            throw r1
        L21:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.assertFullConsumption(java.lang.Object, com.google.gson.stream.JsonReader):void");
    }

    public <T> T fromJson(JsonReader r8, TypeToken<T> r9) {
        Strictness r2 = r8.getStrictness();
        Strictness r3 = this.strictness;
        if (r3 == null) goto L6;
        r8.setStrictness(r3);
    L46:
        r8.peek();     // Catch: Throwable -> L17 AssertionError -> L19 IOException -> L21 IllegalStateException -> L23 EOFException -> L35
        boolean r32 = false;
        TypeAdapter<T> r7 = getAdapter(r9);     // Catch: Throwable -> L17 AssertionError -> L19 IOException -> L21 IllegalStateException -> L23 EOFException -> L25
        T r4 = r7.read(r8);     // Catch: Throwable -> L17 AssertionError -> L19 IOException -> L21 IllegalStateException -> L23 EOFException -> L25
        Class r5 = Primitives.wrap(r9.getRawType());     // Catch: Throwable -> L17 AssertionError -> L19 IOException -> L21 IllegalStateException -> L23 EOFException -> L25
        if (r4 != null) goto L13;
    L27:
        r8.setStrictness(r2);
        return r4;
    L13:
        if (r5.isInstance(r4) == true) goto L27;
        throw new ClassCastException("Type adapter '" + r7 + "' returned wrong type; requested " + r9.getRawType() + " but got instance of " + r4.getClass() + "\nVerify that the adapter was registered for the correct type.");     // Catch: Throwable -> L17 AssertionError -> L19 IOException -> L21 IllegalStateException -> L23 EOFException -> L25
    L25:
        e = e;
    L37:
        if (r32 == false) goto L41;
        r8.setStrictness(r2);
        return null;
    L41:
        throw new JsonSyntaxException(e);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        r8.setStrictness(r2);
        throw th;
    L35:
        e = e;
        r32 = true;
    L21:
        e = move-exception;
        throw new JsonSyntaxException(e);     // Catch: Throwable -> L17
    L19:
        e = move-exception;
        throw new AssertionError("AssertionError (GSON 2.14.0): " + e.getMessage(), e);     // Catch: Throwable -> L17
    L23:
        e = move-exception;
        throw new JsonSyntaxException(e);     // Catch: Throwable -> L17
    L6:
        if (r8.getStrictness() != Strictness.LEGACY_STRICT) goto L46;
        r8.setStrictness(Strictness.LENIENT);
        goto L46
    }

    public <T> T fromJson(Reader r1, TypeToken<T> r2) {
        JsonReader r12 = newJsonReader(r1);
        T r0 = (T) fromJson(r12, r2);
        assertFullConsumption(r0, r12);
        return r0;
    }

    public <T> T fromJson(Reader r1, Class<T> r2) {
        return (T) fromJson(r1, TypeToken.get(r2));
    }

    public <T> T fromJson(String r2, TypeToken<T> r3) {
        if (r2 != null) goto L6;
        return null;
    L6:
        return (T) fromJson(new StringReader(r2), r3);
    }

    public <T> T fromJson(String r1, Class<T> r2) {
        return (T) fromJson(r1, TypeToken.get(r2));
    }

    public <T> T fromJson(String r1, Type r2) {
        return (T) fromJson(r1, TypeToken.get(r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> TypeAdapter<T> getAdapter(TypeToken<T> r8) {
        Objects.requireNonNull(r8, "type must not be null");
        TypeAdapter<T> r0 = (TypeAdapter) this.typeTokenCache.get(r8);
        if (r0 == null) goto L5;
        return r0;
    L5:
        Map<? extends TypeToken<?>, ? extends TypeAdapter<?>> r02 = this.threadLocalAdapterResults.get();
        if (r02 != null) goto L8;
        r02 = new HashMap();
        this.threadLocalAdapterResults.set(r02);
        boolean r1 = true;
    L32:
        FutureTypeAdapter r2 = new FutureTypeAdapter();     // Catch: Throwable -> L19
        r02.put(r8, r2);     // Catch: Throwable -> L19
        Iterator<TypeAdapterFactory> r3 = this.factories.iterator();     // Catch: Throwable -> L19
        TypeAdapter<T> r5 = null;
    L14:
        if (r3.hasNext() == false) goto L21;
        r5 = r3.next().create(this, r8);     // Catch: Throwable -> L19
        if (r5 == null) goto L14;
        r2.setDelegate(r5);     // Catch: Throwable -> L19
        r02.put(r8, r5);     // Catch: Throwable -> L19
    L21:
        if (r1 == false) goto L23;
        this.threadLocalAdapterResults.remove();
    L23:
        if (r5 == null) goto L27;
        if (r1 == false) goto L26;
        this.typeTokenCache.putAll(r02);
    L26:
        return r5;
    L27:
        Native$$ExternalSyntheticBUOutline5.m("GSON (2.14.0) cannot handle ", r8);
        return null;
    L19:
        th = move-exception;
        if (r1 == false) goto L31;
        this.threadLocalAdapterResults.remove();
    L31:
        throw th;
    L8:
        TypeAdapter<T> r12 = (TypeAdapter) r02.get(r8);
        if (r12 == null) goto L11;
        return r12;
    L11:
        r1 = false;
        goto L32
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> r1) {
        return getAdapter(TypeToken.get(r1));
    }

    public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory r4, TypeToken<T> r5) {
        Objects.requireNonNull(r4, "skipPast must not be null");
        Objects.requireNonNull(r5, "type must not be null");
        if (this.jsonAdapterFactory.isClassJsonAdapterFactory(r5, r4) == false) goto L5;
        r4 = this.jsonAdapterFactory;
    L5:
        Iterator<TypeAdapterFactory> r0 = this.factories.iterator();
        boolean r1 = false;
    L7:
        if (r0.hasNext() == false) goto L15;
        TypeAdapterFactory r2 = r0.next();
        if (r1 == false) goto L10;
        TypeAdapter<T> r22 = r2.create(this, r5);
        if (r22 == null) goto L7;
        return r22;
    L10:
        if (r2 != r4) goto L7;
        r1 = true;
        goto L7
    L15:
        if (r1 == false) goto L17;
        Native$$ExternalSyntheticBUOutline5.m("GSON cannot serialize or deserialize ", r5);
        return null;
    L17:
        return getAdapter(r5);
    }

    public JsonReader newJsonReader(Reader r2) {
        JsonReader r0 = new JsonReader(r2);
        Strictness r1 = this.strictness;
        if (r1 != null) goto L5;
        r1 = Strictness.LEGACY_STRICT;
    L5:
        r0.setStrictness(r1);
        return r0;
    }

    public JsonWriter newJsonWriter(Writer r2) {
        if (this.generateNonExecutableJson == false) goto L5;
        r2.write(")]}'\n");
    L5:
        JsonWriter r0 = new JsonWriter(r2);
        r0.setFormattingStyle(this.formattingStyle);
        r0.setHtmlSafe(this.htmlSafe);
        Strictness r22 = this.strictness;
        if (r22 != null) goto L8;
        r22 = Strictness.LEGACY_STRICT;
    L8:
        r0.setStrictness(r22);
        r0.setSerializeNulls(this.serializeNulls);
        return r0;
    }

    public String toJson(JsonElement r2) {
        StringBuilder r0 = new StringBuilder();
        toJson(r2, r0);
        return r0.toString();
    }

    public String toJson(Object r2) {
        if (r2 != null) goto L6;
        return toJson(JsonNull.INSTANCE);
    L6:
        return toJson(r2, r2.getClass());
    }

    public String toJson(Object r2, Type r3) {
        StringBuilder r0 = new StringBuilder();
        toJson(r2, r3, r0);
        return r0.toString();
    }

    public void toJson(JsonElement r6, JsonWriter r7) {
        Strictness r1 = r7.getStrictness();
        boolean r2 = r7.isHtmlSafe();
        boolean r3 = r7.getSerializeNulls();
        r7.setHtmlSafe(this.htmlSafe);
        r7.setSerializeNulls(this.serializeNulls);
        Strictness r5 = this.strictness;
        if (r5 == null) goto L6;
        r7.setStrictness(r5);
    L21:
        Streams.write(r6, r7);     // Catch: Throwable -> L11 AssertionError -> L13 IOException -> L16
        r7.setStrictness(r1);
        r7.setHtmlSafe(r2);
        r7.setSerializeNulls(r3);
        return;
    L11:
        th = move-exception;
        r7.setStrictness(r1);
        r7.setHtmlSafe(r2);
        r7.setSerializeNulls(r3);
        throw th;
    L16:
        e = move-exception;
        throw new JsonIOException(e);     // Catch: Throwable -> L11
    L13:
        e = move-exception;
        throw new AssertionError("AssertionError (GSON 2.14.0): " + e.getMessage(), e);     // Catch: Throwable -> L11
    L6:
        if (r7.getStrictness() != Strictness.LEGACY_STRICT) goto L21;
        r7.setStrictness(Strictness.LENIENT);
        goto L21
    }

    public void toJson(JsonElement r1, Appendable r2) {
        toJson(r1, newJsonWriter(Streams.writerForAppendable(r2)));     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        throw new JsonIOException(e);
    }

    public void toJson(Object r6, Type r7, JsonWriter r8) {
        TypeAdapter r72 = getAdapter(TypeToken.get(r7));
        Strictness r1 = r8.getStrictness();
        Strictness r2 = this.strictness;
        if (r2 == null) goto L6;
        r8.setStrictness(r2);
    L8:
        boolean r22 = r8.isHtmlSafe();
        boolean r3 = r8.getSerializeNulls();
        r8.setHtmlSafe(this.htmlSafe);
        r8.setSerializeNulls(this.serializeNulls);
        r72.write(r8, r6);     // Catch: Throwable -> L12 AssertionError -> L14 IOException -> L17
        r8.setStrictness(r1);
        r8.setHtmlSafe(r22);
        r8.setSerializeNulls(r3);
        return;
    L12:
        th = move-exception;
        r8.setStrictness(r1);
        r8.setHtmlSafe(r22);
        r8.setSerializeNulls(r3);
        throw th;
    L17:
        e = move-exception;
        throw new JsonIOException(e);     // Catch: Throwable -> L12
    L14:
        e = move-exception;
        throw new AssertionError("AssertionError (GSON 2.14.0): " + e.getMessage(), e);     // Catch: Throwable -> L12
    L6:
        if (r8.getStrictness() != Strictness.LEGACY_STRICT) goto L8;
        r8.setStrictness(Strictness.LENIENT);
        goto L8
    }

    public void toJson(Object r1, Type r2, Appendable r3) {
        toJson(r1, r2, newJsonWriter(Streams.writerForAppendable(r3)));     // Catch: IOException -> L4
        return;
    L4:
        e = move-exception;
        throw new JsonIOException(e);
    }

    public JsonElement toJsonTree(Object r2) {
        if (r2 != null) goto L6;
        return JsonNull.INSTANCE;
    L6:
        return toJsonTree(r2, r2.getClass());
    }

    public JsonElement toJsonTree(Object r2, Type r3) {
        JsonTreeWriter r0 = new JsonTreeWriter();
        toJson(r2, r3, r0);
        return r0.get();
    }

    public String toString() {
        return "{serializeNulls:" + this.serializeNulls + ",factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
    }
}
