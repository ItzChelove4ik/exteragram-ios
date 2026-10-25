package org.telegram.messenger;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class RuntimeClassNameTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<?> baseType;
    private final ExclusionStrategy exclusionStrategy;
    private final Map<String, Class<?>> labelToSubtype;
    private final Map<Class<?>, String> subtypeToLabel;
    private final String typeFieldName;

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: org.telegram.messenger.RuntimeClassNameTypeAdapterFactory$1, reason: invalid class name */
    public class AnonymousClass1<R> extends TypeAdapter<R> {
        final /* synthetic */ RuntimeClassNameTypeAdapterFactory this$0;
        final /* synthetic */ Gson val$gson;
        final /* synthetic */ Map val$labelToDelegate;
        final /* synthetic */ Map val$subtypeToDelegate;
        final /* synthetic */ TypeToken val$type;

        public AnonymousClass1(RuntimeClassNameTypeAdapterFactory r1, Map r2, Gson r3, TypeToken r4, Map r5) {
            this.this$0 = r1;
            this.val$labelToDelegate = r2;
            this.val$gson = r3;
            this.val$type = r4;
            this.val$subtypeToDelegate = r5;
        }

        private TypeAdapter<R> getDelegate(Class<?> r3) {
            TypeAdapter<R> r0 = (TypeAdapter) this.val$subtypeToDelegate.get(r3);
            if (r0 == null) goto L5;
            return r0;
        L5:
            Iterator r2 = this.val$subtypeToDelegate.entrySet().iterator();
        L7:
            if (r2.hasNext() == false) goto L12;
            Map.Entry r02 = (Map.Entry) r2.next();
            if (((Class) r02.getKey()).isAssignableFrom(r3) == false) goto L7;
            return (TypeAdapter) r02.getValue();
        L12:
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public R read(JsonReader r7) {
            JsonElement r72 = Streams.parse(r7);
            if (r72.isJsonObject() == false) goto L22;
            JsonElement r0 = r72.getAsJsonObject().remove(RuntimeClassNameTypeAdapterFactory.m4875$$Nest$fgettypeFieldName(this.this$0));
            if (r0 == null) goto L19;
            String r02 = r0.getAsString();
            TypeAdapter<T> r3 = (TypeAdapter) this.val$labelToDelegate.get(r02);
            if (r3 != null) goto L18;
            r3 = this.val$gson.getDelegateAdapter(this.this$0, TypeToken.get(Class.forName(r02)));
            if (r3 != null) goto L18;
            throw new JsonParseException("cannot deserialize " + RuntimeClassNameTypeAdapterFactory.m4874$$Nest$fgetbaseType(this.this$0) + " subtype named " + r02 + "; did you forget to register a subtype?");
        L14:
            e = move-exception;
            throw new JsonParseException("Cannot find class " + r02, e);
        L18:
            return r3.fromJsonTree(r72);
        L19:
            StringBuilder r03 = new StringBuilder("cannot deserialize ");
            r03.append(RuntimeClassNameTypeAdapterFactory.m4874$$Nest$fgetbaseType(this.this$0));
            String r6 = RuntimeClassNameTypeAdapterFactory.m4875$$Nest$fgettypeFieldName(this.this$0);
            r03.append(" because it does not define a field named ");
            r03.append(r6);
            throw new JsonParseException(r03.toString());
        L22:
            if (r72.isJsonNull() == false) goto L25;
            return null;
        L25:
            TypeAdapter<T> r04 = this.val$gson.getDelegateAdapter(this.this$0, this.val$type);
            if (r04 == null) goto L30;
            return r04.fromJsonTree(r72);
        L30:
            throw new JsonParseException("cannot deserialize " + RuntimeClassNameTypeAdapterFactory.m4874$$Nest$fgetbaseType(this.this$0) + "; did you forget to register a subtype?");
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter r5, R r6) {
            Class<?> r0 = r6.getClass();
            String r1 = r0.getSimpleName();
            TypeAdapter<R> r2 = getDelegate(r0);
            if (r2 == null) goto L19;
            JsonElement r62 = r2.toJsonTree(r6);
            if (r62.isJsonObject() == true) goto L8;
            Streams.write(r62, r5);
            return;
        L8:
            JsonObject r63 = r62.getAsJsonObject();
            if (r63.has(RuntimeClassNameTypeAdapterFactory.m4875$$Nest$fgettypeFieldName(this.this$0)) == true) goto L17;
            JsonObject r02 = new JsonObject();
            r02.add(RuntimeClassNameTypeAdapterFactory.m4875$$Nest$fgettypeFieldName(this.this$0), new JsonPrimitive(r1));
            Iterator<Map.Entry<String, JsonElement>> r4 = r63.entrySet().iterator();
        L12:
            if (r4.hasNext() == false) goto L14;
            Map.Entry<String, JsonElement> r64 = r4.next();
            r02.add(r64.getKey(), r64.getValue());
            goto L12
        L14:
            Streams.write(r02, r5);
            return;
        L17:
            throw new JsonParseException("cannot serialize " + r0.getSimpleName() + " because it already defines a field named " + RuntimeClassNameTypeAdapterFactory.m4875$$Nest$fgettypeFieldName(this.this$0));
        L19:
            throw new JsonParseException("cannot serialize " + r0.getSimpleName() + "; did you forget to register a subtype?");
        }
    }

    /* renamed from: -$$Nest$fgetbaseType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Class m4874$$Nest$fgetbaseType(RuntimeClassNameTypeAdapterFactory r0) {
        return r0.baseType;
    }

    /* renamed from: -$$Nest$fgettypeFieldName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m4875$$Nest$fgettypeFieldName(RuntimeClassNameTypeAdapterFactory r0) {
        return r0.typeFieldName;
    }

    private RuntimeClassNameTypeAdapterFactory(Class<?> r2, String r3, ExclusionStrategy r4) {
        this.labelToSubtype = new LinkedHashMap();
        this.subtypeToLabel = new LinkedHashMap();
        if (r3 == null) goto L8;
        if (r2 == null) goto L8;
        this.baseType = r2;
        this.typeFieldName = r3;
        this.exclusionStrategy = r4;
        return;
    L8:
        throw null;
    }

    public static <T> RuntimeClassNameTypeAdapterFactory<T> of(Class<T> r3) {
        return new RuntimeClassNameTypeAdapterFactory(r3, "class", null);
    }

    public static <T> RuntimeClassNameTypeAdapterFactory<T> of(Class<T> r1, String r2, ExclusionStrategy r3) {
        return new RuntimeClassNameTypeAdapterFactory(r1, r2, r3);
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <R> TypeAdapter<R> create(Gson r7, TypeToken<R> r8) {
        if (this.exclusionStrategy.shouldSkipClass(r8.getRawType().getClass()) == false) goto L6;
        return null;
    L6:
        LinkedHashMap r2 = new LinkedHashMap();
        LinkedHashMap r5 = new LinkedHashMap();
        if (Object.class.isAssignableFrom(r8.getRawType()) == false) goto L10;
        TypeAdapter<T> r0 = r7.getDelegateAdapter(this, r8);
        r2.put(r8.getRawType().getSimpleName(), r0);
        r5.put(r8.getRawType(), r0);
    L10:
        return new AnonymousClass1(this, r2, r7, r8, r5).nullSafe();
    }

    public RuntimeClassNameTypeAdapterFactory<T> registerSubtype(Class<? extends T> r2) {
        return registerSubtype(r2, r2.getSimpleName());
    }

    public RuntimeClassNameTypeAdapterFactory<T> registerSubtype(Class<? extends T> r3, String r4) {
        if (r3 == null) goto L13;
        if (r4 != null) goto L6;
        throw null;
    L6:
        if (this.subtypeToLabel.containsKey(r3) == false) goto L8;
    L11:
        f$$ExternalSyntheticBUOutline1.m("types and labels must be unique");
        return null;
    L8:
        if (this.labelToSubtype.containsKey(r4) == true) goto L11;
        this.labelToSubtype.put(r4, r3);
        this.subtypeToLabel.put(r3, r4);
        return this;
    L13:
        throw null;
    }
}
