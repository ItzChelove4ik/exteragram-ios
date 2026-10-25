package com.google.gson;

import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public abstract class JsonElement {
    @Deprecated
    public JsonElement() {
    }

    public boolean getAsBoolean() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public float getAsFloat() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int getAsInt() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public JsonArray getAsJsonArray() {
        if (isJsonArray() == true) goto L5;
        DexMaker$$ExternalSyntheticBUOutline0.m("Not a JSON Array: ", this);
        return null;
    L5:
        return (JsonArray) this;
    }

    public JsonObject getAsJsonObject() {
        if (isJsonObject() == true) goto L5;
        DexMaker$$ExternalSyntheticBUOutline0.m("Not a JSON Object: ", this);
        return null;
    L5:
        return (JsonObject) this;
    }

    public JsonPrimitive getAsJsonPrimitive() {
        if (isJsonPrimitive() == true) goto L5;
        DexMaker$$ExternalSyntheticBUOutline0.m("Not a JSON Primitive: ", this);
        return null;
    L5:
        return (JsonPrimitive) this;
    }

    public long getAsLong() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String getAsString() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean isJsonArray() {
        return this instanceof JsonArray;
    }

    public boolean isJsonNull() {
        return this instanceof JsonNull;
    }

    public boolean isJsonObject() {
        return this instanceof JsonObject;
    }

    public boolean isJsonPrimitive() {
        return this instanceof JsonPrimitive;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();     // Catch: IOException -> L4
        JsonWriter r1 = new JsonWriter(Streams.writerForAppendable(r0));     // Catch: IOException -> L4
        r1.setStrictness(Strictness.LENIENT);     // Catch: IOException -> L4
        Streams.write(this, r1);     // Catch: IOException -> L4
        return r0.toString();
    L4:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }
}
