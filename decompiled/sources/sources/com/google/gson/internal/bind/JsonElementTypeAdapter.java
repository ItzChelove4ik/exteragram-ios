package com.google.gson.internal.bind;

import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes.dex */
public class JsonElementTypeAdapter extends TypeAdapter<JsonElement> {
    public static final JsonElementTypeAdapter ADAPTER = null;

    /* renamed from: com.google.gson.internal.bind.JsonElementTypeAdapter$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = null;

        static {
            int[] r0 = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = r0;
            r0[JsonToken.BEGIN_ARRAY.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L16:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L20:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L26:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L18:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L22:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;     // Catch: NoSuchFieldError -> L15
            return;
        }
    }

    static {
        ADAPTER = new JsonElementTypeAdapter();
    }

    private JsonElementTypeAdapter() {
    }

    private JsonElement readTerminal(JsonReader r2, JsonToken r3) {
        int r1 = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[r3.ordinal()];
        if (r1 == 3) goto L19;
        if (r1 == 4) goto L17;
        if (r1 == 5) goto L15;
        if (r1 != 6) goto L12;
        r2.nextNull();
        return JsonNull.INSTANCE;
    L12:
        DexMaker$$ExternalSyntheticBUOutline0.m("Unexpected token: ", r3);
        return null;
    L15:
        return new JsonPrimitive(Boolean.valueOf(r2.nextBoolean()));
    L17:
        return new JsonPrimitive(new LazilyParsedNumber(r2.nextString()));
    L19:
        return new JsonPrimitive(r2.nextString());
    }

    private JsonElement tryBeginNesting(JsonReader r1, JsonToken r2) {
        int r0 = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[r2.ordinal()];
        if (r0 != 1) goto L5;
        r1.beginArray();
        return new JsonArray();
    L5:
        if (r0 == 2) goto L8;
        return null;
    L8:
        r1.beginObject();
        return new JsonObject();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public JsonElement read(JsonReader r7) {
        if ((r7 instanceof JsonTreeReader) == true) goto L5;
        JsonToken r0 = r7.peek();
        JsonElement r1 = tryBeginNesting(r7, r0);
        if (r1 == null) goto L9;
        ArrayDeque r02 = new ArrayDeque();
    L12:
        if (r7.hasNext() == true) goto L14;
        if ((r1 instanceof JsonArray) == false) goto L32;
        r7.endArray();
    L34:
        if (r02.isEmpty() == true) goto L35;
        r1 = (JsonElement) r02.removeLast();
        goto L12
    L35:
        return r1;
    L32:
        r7.endObject();
        goto L34
    L14:
        if ((r1 instanceof JsonObject) == false) goto L16;
        String r2 = r7.nextName();
    L17:
        JsonToken r3 = r7.peek();
        JsonElement r4 = tryBeginNesting(r7, r3);
        if (r4 == null) goto L20;
        boolean r5 = true;
    L21:
        if (r4 != null) goto L24;
        r4 = readTerminal(r7, r3);
    L24:
        if ((r1 instanceof JsonArray) == false) goto L26;
        ((JsonArray) r1).add(r4);
    L27:
        if (r5 == false) goto L12;
        r02.addLast(r1);
        r1 = r4;
        goto L12
    L26:
        ((JsonObject) r1).add(r2, r4);
        goto L27
    L20:
        r5 = false;
        goto L21
    L16:
        r2 = null;
        goto L17
    L9:
        return readTerminal(r7, r0);
    L5:
        return ((JsonTreeReader) r7).nextJsonElement();
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ JsonElement read(JsonReader r1) {
        return read(r1);
    }

    /* renamed from: write, reason: avoid collision after fix types in other method */
    public void write2(JsonWriter r3, JsonElement r4) {
        if (r4 != null) goto L4;
    L36:
        r3.nullValue();
        return;
    L4:
        if (r4.isJsonNull() == true) goto L36;
        if (r4.isJsonPrimitive() == false) goto L19;
        JsonPrimitive r2 = r4.getAsJsonPrimitive();
        if (r2.isNumber() == false) goto L13;
        r3.value(r2.getAsNumber());
        return;
    L13:
        if (r2.isBoolean() == false) goto L16;
        r3.value(r2.getAsBoolean());
        return;
    L16:
        r3.value(r2.getAsString());
        return;
    L19:
        if (r4.isJsonArray() == false) goto L27;
        r3.beginArray();
        Iterator<JsonElement> r42 = r4.getAsJsonArray().iterator();
    L22:
        if (r42.hasNext() == false) goto L24;
        write2(r3, r42.next());
        goto L22
    L24:
        r3.endArray();
        return;
    L27:
        if (r4.isJsonObject() == false) goto L34;
        r3.beginObject();
        Iterator<Map.Entry<String, JsonElement>> r43 = r4.getAsJsonObject().entrySet().iterator();
    L30:
        if (r43.hasNext() == false) goto L32;
        Map.Entry<String, JsonElement> r0 = r43.next();
        r3.name(r0.getKey());
        write2(r3, r0.getValue());
        goto L30
    L32:
        r3.endObject();
        return;
    L34:
        Buffer$$ExternalSyntheticBUOutline4.m("Couldn't write ", r4.getClass());
    }

    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ void write(JsonWriter r1, JsonElement r2) {
        write2(r1, r2);
    }
}
