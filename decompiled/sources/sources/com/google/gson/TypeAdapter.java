package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

/* loaded from: classes.dex */
public abstract class TypeAdapter<T> {

    /* renamed from: com.google.gson.TypeAdapter$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public final class NullSafeTypeAdapter extends TypeAdapter<T> {
        final /* synthetic */ TypeAdapter this$0;

        private NullSafeTypeAdapter(TypeAdapter r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ NullSafeTypeAdapter(TypeAdapter r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader r3) {
            if (r3.peek() != JsonToken.NULL) goto L7;
            r3.nextNull();
            return null;
        L7:
            return (T) this.this$0.read(r3);
        }

        public String toString() {
            return "NullSafeTypeAdapter[" + this.this$0 + "]";
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter r1, T r2) {
            if (r2 != null) goto L5;
            r1.nullValue();
            return;
        L5:
            this.this$0.write(r1, r2);
        }
    }

    public TypeAdapter() {
    }

    public final T fromJson(Reader r2) {
        return read(new JsonReader(r2));
    }

    public final T fromJson(String r2) {
        return fromJson(new StringReader(r2));
    }

    public final T fromJsonTree(JsonElement r2) {
        return read(new JsonTreeReader(r2));
    L4:
        e = move-exception;
        throw new JsonIOException(e);
    }

    public final TypeAdapter<T> nullSafe() {
        if ((this instanceof NullSafeTypeAdapter) == false) goto L5;
        return this;
    L5:
        return new NullSafeTypeAdapter(this, null);
    }

    public abstract T read(JsonReader r1);

    public final String toJson(T r3) {
        StringBuilder r0 = new StringBuilder();
        toJson(Streams.writerForAppendable(r0), r3);     // Catch: IOException -> L6
        return r0.toString();
    L6:
        e = move-exception;
        throw new JsonIOException(e);
    }

    public final void toJson(Writer r2, T r3) {
        write(new JsonWriter(r2), r3);
    }

    public final JsonElement toJsonTree(T r2) {
        JsonTreeWriter r0 = new JsonTreeWriter();     // Catch: IOException -> L4
        write(r0, r2);     // Catch: IOException -> L4
        return r0.get();
    L4:
        e = move-exception;
        throw new JsonIOException(e);
    }

    public abstract void write(JsonWriter r1, T r2);
}
