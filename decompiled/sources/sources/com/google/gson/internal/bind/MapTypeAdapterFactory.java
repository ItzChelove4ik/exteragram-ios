package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.GsonTypes;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class MapTypeAdapterFactory implements TypeAdapterFactory {
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;

    public final class Adapter<K, V> extends TypeAdapter<Map<K, V>> {
        private final ObjectConstructor<? extends Map<K, V>> constructor;
        private final TypeAdapter<K> keyTypeAdapter;
        final /* synthetic */ MapTypeAdapterFactory this$0;
        private final TypeAdapter<V> valueTypeAdapter;

        public Adapter(MapTypeAdapterFactory r1, TypeAdapter<K> r2, TypeAdapter<V> r3, ObjectConstructor<? extends Map<K, V>> r4) {
            this.this$0 = r1;
            this.keyTypeAdapter = r2;
            this.valueTypeAdapter = r3;
            this.constructor = r4;
        }

        private String keyToString(JsonElement r2) {
            if (r2.isJsonPrimitive() == false) goto L19;
            JsonPrimitive r1 = r2.getAsJsonPrimitive();
            if (r1.isNumber() == false) goto L9;
            return String.valueOf(r1.getAsNumber());
        L9:
            if (r1.isBoolean() == false) goto L13;
            return Boolean.toString(r1.getAsBoolean());
        L13:
            if (r1.isString() == true) goto L15;
            AsyncTimeout$$ExternalSyntheticBUOutline0.m();
            return null;
        L15:
            return r1.getAsString();
        L19:
            if (r2.isJsonNull() == false) goto L22;
            return "null";
        L22:
            AsyncTimeout$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Object read(JsonReader r1) {
            return read(r1);
        }

        @Override // com.google.gson.TypeAdapter
        public Map<K, V> read(JsonReader r6) {
            JsonToken r0 = r6.peek();
            if (r0 != JsonToken.NULL) goto L6;
            r6.nextNull();
            return null;
        L6:
            Map<K, V> r1 = this.constructor.construct();
            if (r0 != JsonToken.BEGIN_ARRAY) goto L18;
            r6.beginArray();
        L10:
            if (r6.hasNext() == false) goto L16;
            r6.beginArray();
            K r02 = this.keyTypeAdapter.read(r6);
            V r2 = this.valueTypeAdapter.read(r6);
            if (r1.containsKey(r02) == true) goto L15;
            r1.put(r02, r2);
            r6.endArray();
            goto L10
        L15:
            throw new JsonSyntaxException("duplicate key: " + r02);
        L16:
            r6.endArray();
            return r1;
        L18:
            r6.beginObject();
        L20:
            if (r6.hasNext() == false) goto L26;
            JsonReaderInternalAccess.INSTANCE.promoteNameToValue(r6);
            K r03 = this.keyTypeAdapter.read(r6);
            V r22 = this.valueTypeAdapter.read(r6);
            if (r1.containsKey(r03) == true) goto L25;
            r1.put(r03, r22);
            goto L20
        L25:
            throw new JsonSyntaxException("duplicate key: " + r03);
        L26:
            r6.endObject();
            return r1;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Object r2) {
            write(r1, (Map) r2);
        }

        public void write(JsonWriter r8, Map<K, V> r9) {
            if (r9 != null) goto L6;
            r8.nullValue();
            return;
        L6:
            if (this.this$0.complexMapKeySerialization == true) goto L13;
            r8.beginObject();
            Iterator<Map.Entry<K, V>> r92 = r9.entrySet().iterator();
        L9:
            if (r92.hasNext() == false) goto L11;
            Map.Entry<K, V> r0 = r92.next();
            r8.name(String.valueOf(r0.getKey()));
            this.valueTypeAdapter.write(r8, r0.getValue());
            goto L9
        L11:
            r8.endObject();
            return;
        L13:
            ArrayList r02 = new ArrayList(r9.size());
            ArrayList r1 = new ArrayList(r9.size());
            Iterator<Map.Entry<K, V>> r93 = r9.entrySet().iterator();
            int r2 = 0;
            boolean r3 = false;
        L15:
            if (r93.hasNext() == false) goto L24;
            Map.Entry<K, V> r4 = r93.next();
            JsonElement r5 = this.keyTypeAdapter.toJsonTree(r4.getKey());
            r02.add(r5);
            r1.add(r4.getValue());
            if (r5.isJsonArray() == false) goto L19;
        L22:
            boolean r42 = true;
        L23:
            r3 = r3 | r42;
            goto L15
        L19:
            if (r5.isJsonObject() == true) goto L22;
            r42 = false;
            goto L23
        L24:
            if (r3 == false) goto L30;
            r8.beginArray();
            int r94 = r02.size();
        L26:
            if (r2 >= r94) goto L28;
            r8.beginArray();
            Streams.write((JsonElement) r02.get(r2), r8);
            this.valueTypeAdapter.write(r8, r1.get(r2));
            r8.endArray();
            r2 = r2 + 1;
            goto L26
        L28:
            r8.endArray();
            return;
        L30:
            r8.beginObject();
            int r95 = r02.size();
        L31:
            if (r2 >= r95) goto L33;
            r8.name(keyToString((JsonElement) r02.get(r2)));
            this.valueTypeAdapter.write(r8, r1.get(r2));
            r2 = r2 + 1;
            goto L31
        L33:
            r8.endObject();
        }
    }

    public MapTypeAdapterFactory(ConstructorConstructor r1, boolean r2) {
        this.constructorConstructor = r1;
        this.complexMapKeySerialization = r2;
    }

    private TypeAdapter<?> getKeyAdapter(Gson r1, Type r2) {
        if (r2 == Boolean.TYPE) goto L10;
        if (r2 == Boolean.class) goto L10;
        return r1.getAdapter(TypeToken.get(r2));
    L10:
        return TypeAdapters.BOOLEAN_AS_STRING;
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson r6, TypeToken<T> r7) {
        Type r0 = r7.getType();
        Class<? super T> r1 = r7.getRawType();
        if (Map.class.isAssignableFrom(r1) == true) goto L6;
        return null;
    L6:
        Type[] r02 = GsonTypes.getMapKeyAndValueTypes(r0, r1);
        Type r2 = r02[0];
        Type r03 = r02[1];
        return new Adapter(this, new TypeAdapterRuntimeTypeWrapper(r6, getKeyAdapter(r6, r2), r2), new TypeAdapterRuntimeTypeWrapper(r6, r6.getAdapter(TypeToken.get(r03)), r03), this.constructorConstructor.get(r7, false));
    }
}
