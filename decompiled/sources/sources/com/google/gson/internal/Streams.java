package com.google.gson.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.JsonElementTypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.Closeable;
import java.io.EOFException;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class Streams {

    /* renamed from: com.google.gson.internal.Streams$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class AppendableWriter extends Writer {
        private final Appendable appendable;
        private final CurrentWrite currentWrite;

        public static class CurrentWrite implements CharSequence {
            private String cachedString;
            private char[] chars;

            private CurrentWrite() {
            }

            public /* synthetic */ CurrentWrite(AnonymousClass1 r1) {
                this();
            }

            @Override // java.lang.CharSequence
            public char charAt(int r1) {
                return this.chars[r1];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.chars.length;
            }

            public void setChars(char[] r1) {
                this.chars = r1;
                this.cachedString = null;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int r2, int r3) {
                return new String(this.chars, r2, r3 - r2);
            }

            @Override // java.lang.CharSequence
            public String toString() {
                if (this.cachedString != null) goto L6;
                this.cachedString = new String(this.chars);
            L6:
                return this.cachedString;
            }
        }

        public AppendableWriter(Appendable r3) {
            this.currentWrite = new CurrentWrite(null);
            this.appendable = r3;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence r2) {
            this.appendable.append(r2);
            return this;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence r2, int r3, int r4) {
            this.appendable.append(r2, r3, r4);
            return this;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public /* bridge */ /* synthetic */ Appendable append(CharSequence r1) {
            return append(r1);
        }

        @Override // java.io.Writer, java.lang.Appendable
        public /* bridge */ /* synthetic */ Appendable append(CharSequence r1, int r2, int r3) {
            return append(r1, r2, r3);
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Appendable r1 = this.appendable;
            if ((r1 instanceof Closeable) == false) goto L6;
            ((Closeable) r1).close();
            return;
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            Appendable r1 = this.appendable;
            if ((r1 instanceof Flushable) == false) goto L6;
            ((Flushable) r1).flush();
            return;
        }

        @Override // java.io.Writer
        public void write(int r1) {
            this.appendable.append((char) r1);
        }

        @Override // java.io.Writer
        public void write(String r1, int r2, int r3) {
            Objects.requireNonNull(r1);
            this.appendable.append(r1, r2, r3 + r2);
        }

        @Override // java.io.Writer
        public void write(char[] r2, int r3, int r4) {
            this.currentWrite.setChars(r2);
            this.appendable.append(this.currentWrite, r3, r4 + r3);
        }
    }

    public static JsonElement parse(JsonReader r2) {
        r2.peek();     // Catch: NumberFormatException -> L8 IOException -> L11 MalformedJsonException -> L14 EOFException -> L17
        boolean r0 = false;
        return JsonElementTypeAdapter.ADAPTER.read(r2);
    L6:
        e = e;
    L19:
        if (r0 == false) goto L23;
        return JsonNull.INSTANCE;
    L23:
        throw new JsonSyntaxException(e);
    L17:
        e = e;
        r0 = true;
    L14:
        e = move-exception;
        throw new JsonSyntaxException(e);
    L11:
        e = move-exception;
        throw new JsonIOException(e);
    L8:
        e = move-exception;
        throw new JsonSyntaxException(e);
    }

    public static void write(JsonElement r1, JsonWriter r2) {
        JsonElementTypeAdapter.ADAPTER.write2(r2, r1);
    }

    public static Writer writerForAppendable(Appendable r1) {
        if ((r1 instanceof Writer) == false) goto L7;
        return (Writer) r1;
    L7:
        return new AppendableWriter(r1);
    }
}
