package com.google.gson.internal.bind;

import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class JsonTreeWriter extends JsonWriter {
    private static final JsonPrimitive SENTINEL_CLOSED = null;
    private static final Writer UNWRITABLE_WRITER = null;
    private String pendingName;
    private JsonElement product;
    private final List<JsonElement> stack;

    /* renamed from: com.google.gson.internal.bind.JsonTreeWriter$1, reason: invalid class name */
    public class AnonymousClass1 extends Writer {
        public AnonymousClass1() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            throw new AssertionError();
        }

        @Override // java.io.Writer
        public void write(char[] r1, int r2, int r3) {
            throw new AssertionError();
        }
    }

    static {
        UNWRITABLE_WRITER = new AnonymousClass1();
        SENTINEL_CLOSED = new JsonPrimitive("closed");
    }

    public JsonTreeWriter() {
        super(UNWRITABLE_WRITER);
        this.stack = new ArrayList();
        this.product = JsonNull.INSTANCE;
    }

    private JsonElement peek() {
        return this.stack.get(r1.size() - 1);
    }

    private void put(JsonElement r3) {
        if (this.pendingName == null) goto L12;
        if (r3.isJsonNull() == true) goto L7;
    L8:
        ((JsonObject) peek()).add(this.pendingName, r3);
    L9:
        this.pendingName = null;
        return;
    L7:
        if (getSerializeNulls() == false) goto L9;
    L12:
        if (this.stack.isEmpty() == false) goto L15;
        this.product = r3;
        return;
    L15:
        JsonElement r2 = peek();
        if ((r2 instanceof JsonArray) == false) goto L19;
        ((JsonArray) r2).add(r3);
        return;
    L19:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter beginArray() {
        JsonArray r0 = new JsonArray();
        put(r0);
        this.stack.add(r0);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter beginObject() {
        JsonObject r0 = new JsonObject();
        put(r0);
        this.stack.add(r0);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.stack.isEmpty() == false) goto L6;
        this.stack.add(SENTINEL_CLOSED);
        return;
    L6:
        Model$$ExternalSyntheticBUOutline0.m("Incomplete document");
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter endArray() {
        if (this.stack.isEmpty() == false) goto L5;
    L12:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        if (this.pendingName != null) goto L12;
        if ((peek() instanceof JsonArray) == false) goto L10;
        this.stack.remove(r0.size() - 1);
        return this;
    L10:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter endObject() {
        if (this.stack.isEmpty() == false) goto L5;
    L12:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        if (this.pendingName != null) goto L12;
        if ((peek() instanceof JsonObject) == false) goto L10;
        this.stack.remove(r0.size() - 1);
        return this;
    L10:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.google.gson.stream.JsonWriter, java.io.Flushable
    public void flush() {
    }

    public JsonElement get() {
        if (this.stack.isEmpty() == true) goto L5;
        CstInsn$$ExternalSyntheticBUOutline0.m("Expected one JSON element but was ", this.stack);
        return null;
    L5:
        return this.product;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter name(String r3) {
        Objects.requireNonNull(r3, "name == null");
        if (this.stack.isEmpty() == false) goto L5;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Did not expect a name");
        return null;
    L5:
        if (this.pendingName != null) goto L12;
        if ((peek() instanceof JsonObject) == false) goto L10;
        this.pendingName = r3;
        return this;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Please begin an object before writing a name.");
        return null;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter nullValue() {
        put(JsonNull.INSTANCE);
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(double r3) {
        if (isLenient() == false) goto L5;
    L11:
        put(new JsonPrimitive(Double.valueOf(r3)));
        return this;
    L5:
        if (Double.isNaN(r3) == true) goto L10;
        if (Double.isInfinite(r3) == false) goto L11;
    L10:
        throw new IllegalArgumentException("JSON forbids NaN and infinities: " + r3);
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(long r2) {
        put(new JsonPrimitive(Long.valueOf(r2)));
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(Boolean r2) {
        if (r2 == null) goto L4;
        put(new JsonPrimitive(r2));
        return this;
    L4:
        return nullValue();
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(Number r4) {
        if (r4 != null) goto L6;
        return nullValue();
    L6:
        if (isLenient() == true) goto L14;
        double r0 = r4.doubleValue();
        if (Double.isNaN(r0) == false) goto L10;
    L12:
        Native$$ExternalSyntheticBUOutline5.m("JSON forbids NaN and infinities: ", r4);
        return null;
    L10:
        if (Double.isInfinite(r0) == true) goto L12;
    L14:
        put(new JsonPrimitive(r4));
        return this;
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(String r2) {
        if (r2 == null) goto L4;
        put(new JsonPrimitive(r2));
        return this;
    L4:
        return nullValue();
    }

    @Override // com.google.gson.stream.JsonWriter
    public JsonWriter value(boolean r2) {
        put(new JsonPrimitive(Boolean.valueOf(r2)));
        return this;
    }
}
