package com.google.gson.internal.bind;

import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import androidx.recyclerview.widget.RecyclerView$$ExternalSyntheticBUOutline0;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class JsonTreeReader extends JsonReader {
    private static final Object SENTINEL_CLOSED = null;
    private static final Reader UNREADABLE_READER = null;
    private int[] pathIndices;
    private String[] pathNames;
    private Object[] stack;
    private int stackSize;

    /* renamed from: com.google.gson.internal.bind.JsonTreeReader$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 extends Reader {
        public AnonymousClass1() {
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] r1, int r2, int r3) {
            throw new AssertionError();
        }
    }

    /* renamed from: com.google.gson.internal.bind.JsonTreeReader$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = null;

        static {
            int[] r0 = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = r0;
            r0[JsonToken.NAME.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    static {
        UNREADABLE_READER = new AnonymousClass1();
        SENTINEL_CLOSED = new Object();
    }

    public JsonTreeReader(JsonElement r3) {
        super(UNREADABLE_READER);
        this.stack = new Object[32];
        this.stackSize = 0;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        push(r3);
    }

    private void expect(JsonToken r3) {
        if (peek() != r3) goto L5;
        return;
    L5:
        StringBuilder r0 = new StringBuilder("Expected ");
        r0.append(r3);
        r0.append(" but was ");
        r0.append(peek());
        RecyclerView$$ExternalSyntheticBUOutline0.m(r0, locationString());
    }

    private String getPath(boolean r7) {
        StringBuilder r0 = new StringBuilder("$");
        int r1 = 0;
    L3:
        int r2 = this.stackSize;
        if (r1 >= r2) goto L31;
        Object[] r3 = this.stack;
        Object r4 = r3[r1];
        if ((r4 instanceof JsonArray) == false) goto L21;
        r1 = r1 + 1;
        if (r1 >= r2) goto L29;
        if ((r3[r1] instanceof Iterator) == false) goto L29;
        int r32 = this.pathIndices[r1];
        if (r7 == false) goto L19;
        if (r32 <= 0) goto L19;
        if (r1 != (r2 - 1)) goto L17;
    L18:
        r32 = r32 - 1;
        goto L19
    L17:
        if (r1 == (r2 - 2)) goto L18;
    L19:
        r0.append('[');
        r0.append(r32);
        r0.append(']');
    L29:
        r1 = r1 + 1;
        goto L3
    L21:
        if ((r4 instanceof JsonObject) == false) goto L29;
        r1 = r1 + 1;
        if (r1 >= r2) goto L29;
        if ((r3[r1] instanceof Iterator) == false) goto L29;
        r0.append('.');
        String r22 = this.pathNames[r1];
        if (r22 == null) goto L29;
        r0.append(r22);
        goto L29
    L31:
        return r0.toString();
    }

    private String locationString() {
        return " at path " + getPath();
    }

    private String nextName(boolean r5) {
        expect(JsonToken.NAME);
        Map.Entry r0 = (Map.Entry) ((Iterator) peekStack()).next();
        String r1 = (String) r0.getKey();
        String[] r2 = this.pathNames;
        int r3 = this.stackSize - 1;
        if (r5 == false) goto L5;
        String r52 = "<skipped>";
    L6:
        r2[r3] = r52;
        push(r0.getValue());
        return r1;
    L5:
        r52 = r1;
        goto L6
    }

    private Object peekStack() {
        return this.stack[this.stackSize - 1];
    }

    private Object popStack() {
        Object[] r0 = this.stack;
        int r1 = this.stackSize - 1;
        this.stackSize = r1;
        Object r3 = r0[r1];
        r0[r1] = null;
        return r3;
    }

    private void push(Object r4) {
        int r0 = this.stackSize;
        Object[] r1 = this.stack;
        if (r0 != r1.length) goto L5;
        int r02 = r0 * 2;
        this.stack = Arrays.copyOf(r1, r02);
        this.pathIndices = Arrays.copyOf(this.pathIndices, r02);
        this.pathNames = (String[]) Arrays.copyOf(this.pathNames, r02);
    L5:
        Object[] r03 = this.stack;
        int r12 = this.stackSize;
        this.stackSize = r12 + 1;
        r03[r12] = r4;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginArray() {
        expect(JsonToken.BEGIN_ARRAY);
        push(((JsonArray) peekStack()).iterator());
        this.pathIndices[this.stackSize - 1] = 0;
    }

    @Override // com.google.gson.stream.JsonReader
    public void beginObject() {
        expect(JsonToken.BEGIN_OBJECT);
        push(((JsonObject) peekStack()).entrySet().iterator());
    }

    @Override // com.google.gson.stream.JsonReader, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.stack = new Object[]{SENTINEL_CLOSED};
        this.stackSize = 1;
    }

    @Override // com.google.gson.stream.JsonReader
    public void endArray() {
        expect(JsonToken.END_ARRAY);
        popStack();
        popStack();
        int r0 = this.stackSize;
        if (r0 <= 0) goto L6;
        int[] r2 = this.pathIndices;
        int r02 = r0 - 1;
        r2[r02] = r2[r02] + 1;
        return;
    }

    @Override // com.google.gson.stream.JsonReader
    public void endObject() {
        expect(JsonToken.END_OBJECT);
        this.pathNames[this.stackSize - 1] = null;
        popStack();
        popStack();
        int r0 = this.stackSize;
        if (r0 <= 0) goto L6;
        int[] r3 = this.pathIndices;
        int r02 = r0 - 1;
        r3[r02] = r3[r02] + 1;
        return;
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPath() {
        return getPath(false);
    }

    @Override // com.google.gson.stream.JsonReader
    public String getPreviousPath() {
        return getPath(true);
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean hasNext() {
        JsonToken r1 = peek();
        if (r1 != JsonToken.END_OBJECT) goto L5;
        return false;
    L5:
        if (r1 != JsonToken.END_ARRAY) goto L7;
        return false;
    L7:
        if (r1 == JsonToken.END_DOCUMENT) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // com.google.gson.stream.JsonReader
    public boolean nextBoolean() {
        expect(JsonToken.BOOLEAN);
        boolean r0 = ((JsonPrimitive) popStack()).getAsBoolean();
        int r1 = this.stackSize;
        if (r1 <= 0) goto L5;
        int[] r3 = this.pathIndices;
        int r12 = r1 - 1;
        r3[r12] = r3[r12] + 1;
    L5:
        return r0;
    }

    @Override // com.google.gson.stream.JsonReader
    public double nextDouble() {
        JsonToken r0 = peek();
        JsonToken r1 = JsonToken.NUMBER;
        if (r0 != r1) goto L5;
    L9:
        double r02 = ((JsonPrimitive) peekStack()).getAsDouble();
        if (isLenient() == false) goto L12;
    L18:
        popStack();
        int r2 = this.stackSize;
        if (r2 <= 0) goto L21;
        int[] r4 = this.pathIndices;
        int r22 = r2 - 1;
        r4[r22] = r4[r22] + 1;
    L21:
        return r02;
    L12:
        if (Double.isNaN(r02) == true) goto L17;
        if (Double.isInfinite(r02) == false) goto L18;
    L17:
        throw new MalformedJsonException("JSON forbids NaN and infinities: " + r02);
    L5:
        if (r0 == JsonToken.STRING) goto L9;
        StringBuilder r23 = new StringBuilder("Expected ");
        r23.append(r1);
        r23.append(" but was ");
        r23.append(r0);
        RecyclerView$$ExternalSyntheticBUOutline0.m(r23, locationString());
        return 0.0d;
    }

    @Override // com.google.gson.stream.JsonReader
    public int nextInt() {
        JsonToken r0 = peek();
        JsonToken r1 = JsonToken.NUMBER;
        if (r0 != r1) goto L5;
    L9:
        int r02 = ((JsonPrimitive) peekStack()).getAsInt();
        popStack();
        int r12 = this.stackSize;
        if (r12 <= 0) goto L12;
        int[] r4 = this.pathIndices;
        int r13 = r12 - 1;
        r4[r13] = r4[r13] + 1;
    L12:
        return r02;
    L5:
        if (r0 == JsonToken.STRING) goto L9;
        StringBuilder r2 = new StringBuilder("Expected ");
        r2.append(r1);
        r2.append(" but was ");
        r2.append(r0);
        RecyclerView$$ExternalSyntheticBUOutline0.m(r2, locationString());
        return 0;
    }

    public JsonElement nextJsonElement() {
        JsonToken r0 = peek();
        if (r0 != JsonToken.NAME) goto L5;
    L12:
        LiveData$$ExternalSyntheticBUOutline0.m("Unexpected ", r0, " when reading a JsonElement.");
        return null;
    L5:
        if (r0 == JsonToken.END_ARRAY) goto L12;
        if (r0 == JsonToken.END_OBJECT) goto L12;
        if (r0 == JsonToken.END_DOCUMENT) goto L12;
        JsonElement r02 = (JsonElement) peekStack();
        skipValue();
        return r02;
    }

    @Override // com.google.gson.stream.JsonReader
    public long nextLong() {
        JsonToken r0 = peek();
        JsonToken r1 = JsonToken.NUMBER;
        if (r0 != r1) goto L5;
    L9:
        long r02 = ((JsonPrimitive) peekStack()).getAsLong();
        popStack();
        int r2 = this.stackSize;
        if (r2 <= 0) goto L12;
        int[] r4 = this.pathIndices;
        int r22 = r2 - 1;
        r4[r22] = r4[r22] + 1;
    L12:
        return r02;
    L5:
        if (r0 == JsonToken.STRING) goto L9;
        StringBuilder r23 = new StringBuilder("Expected ");
        r23.append(r1);
        r23.append(" but was ");
        r23.append(r0);
        RecyclerView$$ExternalSyntheticBUOutline0.m(r23, locationString());
        return 0;
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextName() {
        return nextName(false);
    }

    @Override // com.google.gson.stream.JsonReader
    public void nextNull() {
        expect(JsonToken.NULL);
        popStack();
        int r0 = this.stackSize;
        if (r0 <= 0) goto L6;
        int[] r2 = this.pathIndices;
        int r02 = r0 - 1;
        r2[r02] = r2[r02] + 1;
        return;
    }

    @Override // com.google.gson.stream.JsonReader
    public String nextString() {
        JsonToken r0 = peek();
        JsonToken r1 = JsonToken.STRING;
        if (r0 != r1) goto L5;
    L9:
        String r02 = ((JsonPrimitive) popStack()).getAsString();
        int r12 = this.stackSize;
        if (r12 <= 0) goto L12;
        int[] r4 = this.pathIndices;
        int r13 = r12 - 1;
        r4[r13] = r4[r13] + 1;
    L12:
        return r02;
    L5:
        if (r0 == JsonToken.NUMBER) goto L9;
        StringBuilder r2 = new StringBuilder("Expected ");
        r2.append(r1);
        r2.append(" but was ");
        r2.append(r0);
        RecyclerView$$ExternalSyntheticBUOutline0.m(r2, locationString());
        return null;
    }

    @Override // com.google.gson.stream.JsonReader
    public JsonToken peek() {
        if (this.stackSize == 0) goto L5;
        Object r0 = peekStack();
        if ((r0 instanceof Iterator) == false) goto L21;
        boolean r1 = this.stack[this.stackSize - 2] instanceof JsonObject;
        Iterator r02 = (Iterator) r0;
        if (r02.hasNext() == false) goto L15;
        if (r1 == true) goto L12;
        push(r02.next());
        return peek();
    L12:
        return JsonToken.NAME;
    L15:
        if (r1 == false) goto L19;
        return JsonToken.END_OBJECT;
    L19:
        return JsonToken.END_ARRAY;
    L21:
        if ((r0 instanceof JsonObject) == false) goto L25;
        return JsonToken.BEGIN_OBJECT;
    L25:
        if ((r0 instanceof JsonArray) == false) goto L29;
        return JsonToken.BEGIN_ARRAY;
    L29:
        if ((r0 instanceof JsonPrimitive) == false) goto L45;
        JsonPrimitive r03 = (JsonPrimitive) r0;
        if (r03.isString() == false) goto L35;
        return JsonToken.STRING;
    L35:
        if (r03.isBoolean() == false) goto L39;
        return JsonToken.BOOLEAN;
    L39:
        if (r03.isNumber() == true) goto L41;
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L41:
        return JsonToken.NUMBER;
    L45:
        if ((r0 instanceof JsonNull) == false) goto L49;
        return JsonToken.NULL;
    L49:
        if (r0 != SENTINEL_CLOSED) goto L53;
        Segment$$ExternalSyntheticBUOutline1.m("JsonReader is closed");
        return null;
    L53:
        throw new MalformedJsonException("Custom JsonElement subclass " + r0.getClass().getName() + " is not supported");
    L5:
        return JsonToken.END_DOCUMENT;
    }

    public void promoteNameToValue() {
        expect(JsonToken.NAME);
        Map.Entry r0 = (Map.Entry) ((Iterator) peekStack()).next();
        push(r0.getValue());
        push(new JsonPrimitive((String) r0.getKey()));
    }

    @Override // com.google.gson.stream.JsonReader
    public void skipValue() {
        JsonToken r0 = peek();
        int r02 = AnonymousClass2.$SwitchMap$com$google$gson$stream$JsonToken[r0.ordinal()];
        if (r02 != 1) goto L5;
        nextName(true);
        return;
    L5:
        if (r02 != 2) goto L7;
        endArray();
        return;
    L7:
        if (r02 != 3) goto L9;
        endObject();
        return;
    L9:
        if (r02 == 4) goto L20;
        popStack();
        int r03 = this.stackSize;
        if (r03 <= 0) goto L21;
        int[] r3 = this.pathIndices;
        int r04 = r03 - 1;
        r3[r04] = r3[r04] + 1;
        return;
    L21:
        return;
    }

    @Override // com.google.gson.stream.JsonReader
    public String toString() {
        return JsonTreeReader.class.getSimpleName() + locationString();
    }
}
