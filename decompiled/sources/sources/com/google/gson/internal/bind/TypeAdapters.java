package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.NumberLimits;
import com.google.gson.internal.TroubleshootingGuide;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import j$.time.LocalTime;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* loaded from: classes.dex */
public abstract class TypeAdapters {
    public static final TypeAdapter<AtomicBoolean> ATOMIC_BOOLEAN = null;
    public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY = null;
    public static final TypeAdapter<AtomicInteger> ATOMIC_INTEGER = null;
    public static final TypeAdapter<AtomicIntegerArray> ATOMIC_INTEGER_ARRAY = null;
    public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY = null;
    public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY = null;
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL = null;
    public static final TypeAdapterFactory BIG_DECIMAL_FACTORY = null;
    public static final TypeAdapter<BigInteger> BIG_INTEGER = null;
    public static final TypeAdapterFactory BIG_INTEGER_FACTORY = null;
    public static final TypeAdapter<BitSet> BIT_SET = null;
    public static final TypeAdapterFactory BIT_SET_FACTORY = null;
    public static final TypeAdapter<Boolean> BOOLEAN = null;
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING = null;
    public static final TypeAdapterFactory BOOLEAN_FACTORY = null;
    public static final TypeAdapter<Number> BYTE = null;
    public static final TypeAdapterFactory BYTE_FACTORY = null;
    public static final TypeAdapter<Calendar> CALENDAR = null;
    public static final TypeAdapterFactory CALENDAR_FACTORY = null;
    public static final TypeAdapter<Character> CHARACTER = null;
    public static final TypeAdapterFactory CHARACTER_FACTORY = null;
    public static final TypeAdapter<Class> CLASS = null;
    public static final TypeAdapterFactory CLASS_FACTORY = null;
    public static final TypeAdapter<Currency> CURRENCY = null;
    public static final TypeAdapterFactory CURRENCY_FACTORY = null;
    public static final TypeAdapter<Number> DOUBLE = null;
    public static final TypeAdapter<Number> DOUBLE_STRICT = null;
    public static final TypeAdapterFactory ENUM_FACTORY = null;
    public static final TypeAdapter<Number> FLOAT = null;
    public static final TypeAdapter<Number> FLOAT_STRICT = null;
    public static final TypeAdapter<InetAddress> INET_ADDRESS = null;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY = null;
    public static final TypeAdapter<Number> INTEGER = null;
    public static final TypeAdapterFactory INTEGER_FACTORY = null;
    public static final TypeAdapter<JsonElement> JSON_ELEMENT = null;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY = null;
    public static final TypeAdapter<LazilyParsedNumber> LAZILY_PARSED_NUMBER = null;
    public static final TypeAdapterFactory LAZILY_PARSED_NUMBER_FACTORY = null;
    public static final TypeAdapter<Locale> LOCALE = null;
    public static final TypeAdapterFactory LOCALE_FACTORY = null;
    public static final TypeAdapter<Number> LONG = null;
    public static final TypeAdapter<Number> LONG_AS_STRING = null;
    public static final TypeAdapter<Number> SHORT = null;
    public static final TypeAdapterFactory SHORT_FACTORY = null;
    public static final TypeAdapter<String> STRING = null;
    public static final TypeAdapter<StringBuffer> STRING_BUFFER = null;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY = null;
    public static final TypeAdapter<StringBuilder> STRING_BUILDER = null;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY = null;
    public static final TypeAdapterFactory STRING_FACTORY = null;
    public static final TypeAdapter<URI> URI = null;
    public static final TypeAdapterFactory URI_FACTORY = null;
    public static final TypeAdapter<URL> URL = null;
    public static final TypeAdapterFactory URL_FACTORY = null;
    public static final TypeAdapter<UUID> UUID = null;
    public static final TypeAdapterFactory UUID_FACTORY = null;

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$1, reason: invalid class name */
    public class AnonymousClass1 extends TypeAdapter<Class> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Class read(JsonReader r2) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?\nSee " + TroubleshootingGuide.createUrl("java-lang-class-unsupported"));
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Class read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r2, Class r3) {
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + r3.getName() + ". Forgot to register a type adapter?\nSee " + TroubleshootingGuide.createUrl("java-lang-class-unsupported"));
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Class r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$10, reason: invalid class name */
    public class AnonymousClass10 extends TypeAdapter<AtomicBoolean> {
        public AnonymousClass10() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ AtomicBoolean read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public AtomicBoolean read2(JsonReader r1) {
            return new AtomicBoolean(r1.nextBoolean());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, AtomicBoolean r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, AtomicBoolean r2) {
            r1.value(r2.get());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$11, reason: invalid class name */
    public class AnonymousClass11 extends TypeAdapter<AtomicIntegerArray> {
        public AnonymousClass11() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ AtomicIntegerArray read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public AtomicIntegerArray read2(JsonReader r4) {
            ArrayList r3 = new ArrayList();
            r4.beginArray();
        L4:
            if (r4.hasNext() == false) goto L10;
            r3.add(Integer.valueOf(r4.nextInt()));     // Catch: NumberFormatException -> L7
        L7:
            e = move-exception;
            throw new JsonSyntaxException(e);
        L10:
            r4.endArray();
            int r42 = r3.size();
            AtomicIntegerArray r0 = new AtomicIntegerArray(r42);
            int r1 = 0;
        L11:
            if (r1 >= r42) goto L13;
            r0.set(r1, ((Integer) r3.get(r1)).intValue());
            r1 = r1 + 1;
            goto L11
        L13:
            return r0;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, AtomicIntegerArray r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r4, AtomicIntegerArray r5) {
            r4.beginArray();
            int r3 = r5.length();
            int r0 = 0;
        L3:
            if (r0 >= r3) goto L5;
            r4.value(r5.get(r0));
            r0 = r0 + 1;
            goto L3
        L5:
            r4.endArray();
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$12, reason: invalid class name */
    public class AnonymousClass12 extends TypeAdapter<AtomicLongArray> {
        final /* synthetic */ TypeAdapter val$longAdapter;

        public AnonymousClass12(TypeAdapter r1) {
            this.val$longAdapter = r1;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ AtomicLongArray read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public AtomicLongArray read2(JsonReader r5) {
            ArrayList r0 = new ArrayList();
            r5.beginArray();
        L4:
            if (r5.hasNext() == false) goto L6;
            r0.add(Long.valueOf(((Number) this.val$longAdapter.read(r5)).longValue()));
            goto L4
        L6:
            r5.endArray();
            int r4 = r0.size();
            AtomicLongArray r52 = new AtomicLongArray(r4);
            int r1 = 0;
        L7:
            if (r1 >= r4) goto L9;
            r52.set(r1, ((Long) r0.get(r1)).longValue());
            r1 = r1 + 1;
            goto L7
        L9:
            return r52;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, AtomicLongArray r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r6, AtomicLongArray r7) {
            r6.beginArray();
            int r0 = r7.length();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.val$longAdapter.write(r6, Long.valueOf(r7.get(r1)));
            r1 = r1 + 1;
            goto L3
        L5:
            r6.endArray();
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$13, reason: invalid class name */
    public class AnonymousClass13 extends TypeAdapter<Number> {
        public AnonymousClass13() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L11;
            r2.nextNull();
            return null;
        L11:
            return Long.valueOf(r2.nextLong());
        L8:
            e = move-exception;
            throw new JsonSyntaxException(e);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Number read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, Number r4) {
            if (r4 != null) goto L5;
            r3.nullValue();
            return;
        L5:
            r3.value(r4.longValue());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Number r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$14, reason: invalid class name */
    public class AnonymousClass14 extends TypeAdapter<Number> {
        public AnonymousClass14() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            return Long.valueOf(r2.nextLong());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Number read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, Number r2) {
            if (r2 != null) goto L5;
            r1.nullValue();
            return;
        L5:
            r1.value(r2.toString());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Number r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$15, reason: invalid class name */
    public class AnonymousClass15 extends TypeAdapter<Character> {
        public AnonymousClass15() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Character read(JsonReader r4) {
            if (r4.peek() != JsonToken.NULL) goto L6;
            r4.nextNull();
            return null;
        L6:
            String r3 = r4.nextString();
            if (r3.length() != 1) goto L11;
            return Character.valueOf(r3.charAt(0));
        L11:
            throw new JsonSyntaxException("Expecting character, got: " + r3 + "; at " + r4.getPreviousPath());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Character read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, Character r2) {
            if (r2 != null) goto L4;
            String r0 = null;
        L5:
            r1.value(r0);
            return;
        L4:
            r0 = String.valueOf(r2);
            goto L5
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Character r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$16, reason: invalid class name */
    public class AnonymousClass16 extends TypeAdapter<String> {
        public AnonymousClass16() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ String read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public String read2(JsonReader r2) {
            JsonToken r1 = r2.peek();
            if (r1 != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            if (r1 != JsonToken.BOOLEAN) goto L11;
            return Boolean.toString(r2.nextBoolean());
        L11:
            return r2.nextString();
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, String r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, String r2) {
            r1.value(r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$17, reason: invalid class name */
    public class AnonymousClass17 extends TypeAdapter<BigDecimal> {
        public AnonymousClass17() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ BigDecimal read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public BigDecimal read2(JsonReader r4) {
            if (r4.peek() != JsonToken.NULL) goto L6;
            r4.nextNull();
            return null;
        L6:
            String r3 = r4.nextString();
            return NumberLimits.parseBigDecimal(r3);
        L9:
            e = move-exception;
            TypeAdapters$17$$ExternalSyntheticBUOutline0.m(r3, "' as BigDecimal; at path ", r4.getPreviousPath(), e);
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, BigDecimal r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, BigDecimal r2) {
            r1.value(r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$18, reason: invalid class name */
    public class AnonymousClass18 extends TypeAdapter<BigInteger> {
        public AnonymousClass18() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ BigInteger read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public BigInteger read2(JsonReader r4) {
            if (r4.peek() != JsonToken.NULL) goto L6;
            r4.nextNull();
            return null;
        L6:
            String r3 = r4.nextString();
            return NumberLimits.parseBigInteger(r3);
        L9:
            e = move-exception;
            TypeAdapters$17$$ExternalSyntheticBUOutline0.m(r3, "' as BigInteger; at path ", r4.getPreviousPath(), e);
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, BigInteger r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, BigInteger r2) {
            r1.value(r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$19, reason: invalid class name */
    public class AnonymousClass19 extends TypeAdapter<LazilyParsedNumber> {
        public AnonymousClass19() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public LazilyParsedNumber read(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            return new LazilyParsedNumber(r2.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ LazilyParsedNumber read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, LazilyParsedNumber r2) {
            r1.value(r2);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, LazilyParsedNumber r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$2, reason: invalid class name */
    public class AnonymousClass2 extends TypeAdapter<BitSet> {
        public AnonymousClass2() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ BitSet read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public BitSet read2(JsonReader r7) {
            BitSet r6 = new BitSet();
            r7.beginArray();
            JsonToken r0 = r7.peek();
            int r2 = 0;
        L4:
            if (r0 == JsonToken.END_ARRAY) goto L23;
            int r3 = AnonymousClass34.$SwitchMap$com$google$gson$stream$JsonToken[r0.ordinal()];
            boolean r4 = true;
            if (r3 != 1) goto L8;
        L14:
            int r02 = r7.nextInt();
            if (r02 != 0) goto L17;
            r4 = false;
        L18:
            if (r4 == false) goto L20;
            r6.set(r2);
        L20:
            r2 = r2 + 1;
            r0 = r7.peek();
            goto L4
        L17:
            if (r02 == 1) goto L18;
            TypeAdapters$2$$ExternalSyntheticBUOutline0.m("Invalid bitset value ", r02, ", expected 0 or 1; at path ", r7.getPreviousPath());
            return null;
        L8:
            if (r3 == 2) goto L14;
            if (r3 != 3) goto L12;
            r4 = r7.nextBoolean();
            goto L18
        L12:
            StringBuilder r1 = new StringBuilder("Invalid bitset value type: ");
            r1.append(r0);
            String r72 = r7.getPath();
            r1.append("; at path ");
            r1.append(r72);
            throw new JsonSyntaxException(r1.toString());
        L23:
            r7.endArray();
            return r6;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, BitSet r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r4, BitSet r5) {
            r4.beginArray();
            int r3 = r5.length();
            int r0 = 0;
        L3:
            if (r0 >= r3) goto L5;
            r4.value(r5.get(r0) ? 1 : 0);
            r0 = r0 + 1;
            goto L3
        L5:
            r4.endArray();
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$20, reason: invalid class name */
    public class AnonymousClass20 extends TypeAdapter<StringBuilder> {
        public AnonymousClass20() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ StringBuilder read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public StringBuilder read2(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            return new StringBuilder(r2.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, StringBuilder r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, StringBuilder r2) {
            if (r2 != null) goto L4;
            String r0 = null;
        L5:
            r1.value(r0);
            return;
        L4:
            r0 = r2.toString();
            goto L5
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$21, reason: invalid class name */
    public class AnonymousClass21 extends TypeAdapter<StringBuffer> {
        public AnonymousClass21() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ StringBuffer read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public StringBuffer read2(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            return new StringBuffer(r2.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, StringBuffer r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, StringBuffer r2) {
            if (r2 != null) goto L4;
            String r0 = null;
        L5:
            r1.value(r0);
            return;
        L4:
            r0 = r2.toString();
            goto L5
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$22, reason: invalid class name */
    public class AnonymousClass22 extends TypeAdapter<URL> {
        public AnonymousClass22() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ URL read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public URL read2(JsonReader r3) {
            if (r3.peek() != JsonToken.NULL) goto L6;
            r3.nextNull();
            return null;
        L6:
            String r2 = r3.nextString();
            if (r2.equals("null") == false) goto L10;
            return null;
        L10:
            return new URL(r2);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, URL r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, URL r2) {
            if (r2 != null) goto L4;
            String r0 = null;
        L5:
            r1.value(r0);
            return;
        L4:
            r0 = r2.toExternalForm();
            goto L5
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$23, reason: invalid class name */
    public class AnonymousClass23 extends TypeAdapter<URI> {
        public AnonymousClass23() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ URI read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public URI read2(JsonReader r3) {
            if (r3.peek() != JsonToken.NULL) goto L14;
            r3.nextNull();
            return null;
        L14:
            String r2 = r3.nextString();     // Catch: URISyntaxException -> L11
            if (r2.equals("null") == false) goto L9;
            return null;
        L9:
            return new URI(r2);
        L11:
            e = move-exception;
            throw new JsonIOException(e);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, URI r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, URI r2) {
            if (r2 != null) goto L4;
            String r0 = null;
        L5:
            r1.value(r0);
            return;
        L4:
            r0 = r2.toASCIIString();
            goto L5
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$24, reason: invalid class name */
    public class AnonymousClass24 extends TypeAdapter<InetAddress> {
        public AnonymousClass24() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ InetAddress read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public InetAddress read2(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            return InetAddress.getByName(r2.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, InetAddress r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, InetAddress r2) {
            if (r2 != null) goto L4;
            String r0 = null;
        L5:
            r1.value(r0);
            return;
        L4:
            r0 = r2.getHostAddress();
            goto L5
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$25, reason: invalid class name */
    public class AnonymousClass25 extends TypeAdapter<UUID> {
        public AnonymousClass25() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ UUID read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public UUID read2(JsonReader r4) {
            if (r4.peek() != JsonToken.NULL) goto L6;
            r4.nextNull();
            return null;
        L6:
            String r3 = r4.nextString();
            return UUID.fromString(r3);
        L9:
            e = move-exception;
            TypeAdapters$17$$ExternalSyntheticBUOutline0.m(r3, "' as UUID; at path ", r4.getPreviousPath(), e);
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, UUID r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, UUID r2) {
            if (r2 != null) goto L4;
            String r0 = null;
        L5:
            r1.value(r0);
            return;
        L4:
            r0 = r2.toString();
            goto L5
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$26, reason: invalid class name */
    public class AnonymousClass26 extends TypeAdapter<Currency> {
        public AnonymousClass26() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Currency read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public Currency read2(JsonReader r3) {
            String r2 = r3.nextString();
            return Currency.getInstance(r2);
        L5:
            e = move-exception;
            TypeAdapters$17$$ExternalSyntheticBUOutline0.m(r2, "' as Currency; at path ", r3.getPreviousPath(), e);
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Currency r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, Currency r2) {
            r1.value(r2.getCurrencyCode());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$27, reason: invalid class name */
    public class AnonymousClass27 extends IntegerFieldsTypeAdapter<Calendar> {
        public AnonymousClass27(String... r1) {
            super(r1);
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ Calendar create(long[] r1) {
            return create2(r1);
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        /* renamed from: create, reason: avoid collision after fix types in other method */
        public Calendar create2(long[] r8) {
            return new GregorianCalendar(TypeAdapters.access$100(r8[0]), TypeAdapters.access$100(r8[1]), TypeAdapters.access$100(r8[2]), TypeAdapters.access$100(r8[3]), TypeAdapters.access$100(r8[4]), TypeAdapters.access$100(r8[5]));
        }

        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public /* bridge */ /* synthetic */ long[] integerValues(Calendar r1) {
            return integerValues2(r1);
        }

        /* renamed from: integerValues, reason: avoid collision after fix types in other method */
        public long[] integerValues2(Calendar r18) {
            return new long[]{r18.get(1), r18.get(2), r18.get(5), r18.get(11), r18.get(12), r18.get(13)};
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$28, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass28 extends TypeAdapter<Locale> {
        public AnonymousClass28() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Locale read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public Locale read2(JsonReader r4) {
            String r1 = null;
            if (r4.peek() != JsonToken.NULL) goto L6;
            r4.nextNull();
            return null;
        L6:
            StringTokenizer r42 = new StringTokenizer(r4.nextString(), "_");
            if (r42.hasMoreElements() == false) goto L9;
            String r3 = r42.nextToken();
        L11:
            if (r42.hasMoreElements() == false) goto L13;
            String r0 = r42.nextToken();
        L15:
            if (r42.hasMoreElements() == false) goto L17;
            r1 = r42.nextToken();
        L17:
            if (r0 != null) goto L21;
            if (r1 != null) goto L21;
            return new Locale(r3);
        L21:
            if (r1 != null) goto L25;
            return new Locale(r3, r0);
        L25:
            return new Locale(r3, r0, r1);
        L13:
            r0 = null;
            goto L15
        L9:
            r3 = null;
            goto L11
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Locale r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, Locale r2) {
            if (r2 != null) goto L4;
            String r0 = null;
        L5:
            r1.value(r0);
            return;
        L4:
            r0 = r2.toString();
            goto L5
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$29, reason: invalid class name */
    public class AnonymousClass29 implements TypeAdapterFactory {
        final /* synthetic */ TypeToken val$type;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        public AnonymousClass29(TypeToken r1, TypeAdapter r2) {
            this.val$type = r1;
            this.val$typeAdapter = r2;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r1, TypeToken<T> r2) {
            if (r2.equals(this.val$type) == true) goto L5;
            return null;
        L5:
            return this.val$typeAdapter;
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$3, reason: invalid class name */
    public class AnonymousClass3 extends TypeAdapter<Boolean> {
        public AnonymousClass3() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Boolean read(JsonReader r2) {
            JsonToken r1 = r2.peek();
            if (r1 != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            if (r1 != JsonToken.STRING) goto L11;
            return Boolean.valueOf(Boolean.parseBoolean(r2.nextString()));
        L11:
            return Boolean.valueOf(r2.nextBoolean());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Boolean read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, Boolean r2) {
            r1.value(r2);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Boolean r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$30, reason: invalid class name */
    public class AnonymousClass30 implements TypeAdapterFactory {
        final /* synthetic */ Class val$type;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        public AnonymousClass30(Class r1, TypeAdapter r2) {
            this.val$type = r1;
            this.val$typeAdapter = r2;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r1, TypeToken<T> r2) {
            if (r2.getRawType() == this.val$type) goto L5;
            return null;
        L5:
            return this.val$typeAdapter;
        }

        public String toString() {
            return "Factory[type=" + this.val$type.getName() + ",adapter=" + this.val$typeAdapter + "]";
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$31, reason: invalid class name */
    public class AnonymousClass31 implements TypeAdapterFactory {
        final /* synthetic */ Class val$boxed;
        final /* synthetic */ TypeAdapter val$typeAdapter;
        final /* synthetic */ Class val$unboxed;

        public AnonymousClass31(Class r1, Class r2, TypeAdapter r3) {
            this.val$unboxed = r1;
            this.val$boxed = r2;
            this.val$typeAdapter = r3;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r1, TypeToken<T> r2) {
            Class<? super T> r12 = r2.getRawType();
            if (r12 == this.val$unboxed) goto L10;
            if (r12 == this.val$boxed) goto L10;
            return null;
        L10:
            return this.val$typeAdapter;
        }

        public String toString() {
            return "Factory[type=" + this.val$boxed.getName() + "+" + this.val$unboxed.getName() + ",adapter=" + this.val$typeAdapter + "]";
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$32, reason: invalid class name */
    public class AnonymousClass32 implements TypeAdapterFactory {
        final /* synthetic */ Class val$base;
        final /* synthetic */ Class val$sub;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        public AnonymousClass32(Class r1, Class r2, TypeAdapter r3) {
            this.val$base = r1;
            this.val$sub = r2;
            this.val$typeAdapter = r3;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson r1, TypeToken<T> r2) {
            Class<? super T> r12 = r2.getRawType();
            if (r12 == this.val$base) goto L10;
            if (r12 == this.val$sub) goto L10;
            return null;
        L10:
            return this.val$typeAdapter;
        }

        public String toString() {
            return "Factory[type=" + this.val$base.getName() + "+" + this.val$sub.getName() + ",adapter=" + this.val$typeAdapter + "]";
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$33, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass33 implements TypeAdapterFactory {
        final /* synthetic */ Class val$clazz;
        final /* synthetic */ TypeAdapter val$typeAdapter;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* renamed from: com.google.gson.internal.bind.TypeAdapters$33$1, reason: invalid class name */
        public class AnonymousClass1<T1> extends TypeAdapter<T1> {
            final /* synthetic */ AnonymousClass33 this$0;
            final /* synthetic */ Class val$requestedType;

            public AnonymousClass1(AnonymousClass33 r1, Class r2) {
                this.this$0 = r1;
                this.val$requestedType = r2;
            }

            @Override // com.google.gson.TypeAdapter
            public T1 read(JsonReader r5) {
                T1 r0 = (T1) this.this$0.val$typeAdapter.read(r5);
                if (r0 != null) goto L5;
            L9:
                return r0;
            L5:
                if (this.val$requestedType.isInstance(r0) == true) goto L9;
                throw new JsonSyntaxException("Expected a " + this.val$requestedType.getName() + " but was " + r0.getClass().getName() + "; at path " + r5.getPreviousPath());
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter r1, T1 r2) {
                this.this$0.val$typeAdapter.write(r1, r2);
            }
        }

        public AnonymousClass33(Class r1, TypeAdapter r2) {
            this.val$clazz = r1;
            this.val$typeAdapter = r2;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T2> TypeAdapter<T2> create(Gson r1, TypeToken<T2> r2) {
            Class<? super T2> r12 = r2.getRawType();
            if (this.val$clazz.isAssignableFrom(r12) == true) goto L7;
            return null;
        L7:
            return new AnonymousClass1(this, r12);
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.val$clazz.getName() + ",adapter=" + this.val$typeAdapter + "]";
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$34, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass34 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = null;

        static {
            int[] r0 = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = r0;
            r0[JsonToken.NUMBER.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$4, reason: invalid class name */
    public class AnonymousClass4 extends TypeAdapter<Boolean> {
        public AnonymousClass4() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Boolean read(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            return Boolean.valueOf(r2.nextString());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Boolean read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r1, Boolean r2) {
            if (r2 != null) goto L4;
            String r0 = "null";
        L5:
            r1.value(r0);
            return;
        L4:
            r0 = r2.toString();
            goto L5
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Boolean r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$5, reason: invalid class name */
    public class AnonymousClass5 extends TypeAdapter<Number> {
        public AnonymousClass5() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader r4) {
            if (r4.peek() != JsonToken.NULL) goto L18;
            r4.nextNull();
            return null;
        L18:
            int r3 = r4.nextInt();     // Catch: NumberFormatException -> L15
            if (r3 <= 255) goto L10;
        L13:
            TypeAdapters$2$$ExternalSyntheticBUOutline0.m("Lossy conversion from ", r3, " to byte; at path ", r4.getPreviousPath());
            return null;
        L10:
            if (r3 < (-128)) goto L13;
            return Byte.valueOf((byte) r3);
        L15:
            e = move-exception;
            throw new JsonSyntaxException(e);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Number read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, Number r4) {
            if (r4 != null) goto L5;
            r3.nullValue();
            return;
        L5:
            r3.value(r4.byteValue());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Number r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$6, reason: invalid class name */
    public class AnonymousClass6 extends TypeAdapter<Number> {
        public AnonymousClass6() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader r4) {
            if (r4.peek() != JsonToken.NULL) goto L18;
            r4.nextNull();
            return null;
        L18:
            int r3 = r4.nextInt();     // Catch: NumberFormatException -> L15
            if (r3 <= 65535) goto L10;
        L13:
            TypeAdapters$2$$ExternalSyntheticBUOutline0.m("Lossy conversion from ", r3, " to short; at path ", r4.getPreviousPath());
            return null;
        L10:
            if (r3 < (-32768)) goto L13;
            return Short.valueOf((short) r3);
        L15:
            e = move-exception;
            throw new JsonSyntaxException(e);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Number read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, Number r4) {
            if (r4 != null) goto L5;
            r3.nullValue();
            return;
        L5:
            r3.value(r4.shortValue());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Number r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$7, reason: invalid class name */
    public class AnonymousClass7 extends TypeAdapter<Number> {
        public AnonymousClass7() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L11;
            r2.nextNull();
            return null;
        L11:
            return Integer.valueOf(r2.nextInt());
        L8:
            e = move-exception;
            throw new JsonSyntaxException(e);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ Number read(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, Number r4) {
            if (r4 != null) goto L5;
            r3.nullValue();
            return;
        L5:
            r3.value(r4.intValue());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Number r2) {
            write2(r1, r2);
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$8, reason: invalid class name */
    public class AnonymousClass8 extends TypeAdapter<AtomicInteger> {
        public AnonymousClass8() {
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ AtomicInteger read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public AtomicInteger read2(JsonReader r1) {
            return new AtomicInteger(r1.nextInt());
        L4:
            e = move-exception;
            throw new JsonSyntaxException(e);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, AtomicInteger r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, AtomicInteger r4) {
            r3.value(r4.get());
        }
    }

    /* renamed from: com.google.gson.internal.bind.TypeAdapters$9, reason: invalid class name */
    public class AnonymousClass9 extends TypeAdapter<AtomicLong> {
        final /* synthetic */ TypeAdapter val$longAdapter;

        public AnonymousClass9(TypeAdapter r1) {
            this.val$longAdapter = r1;
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ AtomicLong read(JsonReader r1) {
            return read2(r1);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public AtomicLong read2(JsonReader r3) {
            return new AtomicLong(((Number) this.val$longAdapter.read(r3)).longValue());
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, AtomicLong r2) {
            write2(r1, r2);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, AtomicLong r4) {
            this.val$longAdapter.write(r3, Long.valueOf(r4.get()));
        }
    }

    /* loaded from: classes5.dex */
    public static class DoubleAdapter extends TypeAdapter<Number> {
        private final boolean strict;

        public DoubleAdapter(boolean r1) {
            this.strict = r1;
        }

        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            return Double.valueOf(r2.nextDouble());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public /* bridge */ /* synthetic */ Number read2(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r3, Number r4) {
            if (r4 != null) goto L5;
            r3.nullValue();
            return;
        L5:
            double r0 = r4.doubleValue();
            if (this.strict == false) goto L8;
            TypeAdapters.access$000(r0);
        L8:
            r3.value(r0);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Number r2) {
            write2(r1, r2);
        }
    }

    /* loaded from: classes5.dex */
    public interface FactorySupplier {
        TypeAdapterFactory get();
    }

    /* loaded from: classes5.dex */
    public static class FloatAdapter extends TypeAdapter<Number> {
        private final boolean strict;

        public FloatAdapter(boolean r1) {
            this.strict = r1;
        }

        @Override // com.google.gson.TypeAdapter
        public Number read(JsonReader r2) {
            if (r2.peek() != JsonToken.NULL) goto L7;
            r2.nextNull();
            return null;
        L7:
            return Float.valueOf((float) r2.nextDouble());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public /* bridge */ /* synthetic */ Number read2(JsonReader r1) {
            return read(r1);
        }

        /* renamed from: write, reason: avoid collision after fix types in other method */
        public void write2(JsonWriter r4, Number r5) {
            if (r5 != null) goto L5;
            r4.nullValue();
            return;
        L5:
            float r0 = r5.floatValue();
            if (this.strict == false) goto L9;
            TypeAdapters.access$000(r0);
        L9:
            if ((r5 instanceof Float) == true) goto L12;
            r5 = Float.valueOf(r0);
        L12:
            r4.value(r5);
        }

        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter r1, Number r2) {
            write2(r1, r2);
        }
    }

    /* loaded from: classes5.dex */
    public static abstract class IntegerFieldsTypeAdapter<T> extends TypeAdapter<T> {
        private final List<String> fields;

        public IntegerFieldsTypeAdapter(String... r1) {
            this.fields = Arrays.asList(r1);
        }

        public abstract T create(long[] r1);

        public abstract long[] integerValues(T r1);

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader r5) {
            if (r5.peek() != JsonToken.NULL) goto L6;
            r5.nextNull();
            return null;
        L6:
            r5.beginObject();
            long[] r0 = new long[this.fields.size()];
        L8:
            if (r5.peek() == JsonToken.END_OBJECT) goto L13;
            String r1 = r5.nextName();
            int r12 = this.fields.indexOf(r1);
            if (r12 >= 0) goto L11;
            r5.skipValue();
            goto L8
        L11:
            r0[r12] = r5.nextLong();
            goto L8
        L13:
            r5.endObject();
            return create(r0);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter r4, T r5) {
            if (r5 != null) goto L5;
            r4.nullValue();
            return;
        L5:
            r4.beginObject();
            long[] r52 = integerValues(r5);
            int r0 = 0;
        L7:
            if (r0 >= this.fields.size()) goto L9;
            r4.name(this.fields.get(r0));
            r4.value(r52[r0]);
            r0 = r0 + 1;
            goto L7
        L9:
            r4.endObject();
        }
    }

    static {
        TypeAdapter<Class> r0 = new AnonymousClass1().nullSafe();
        CLASS = r0;
        CLASS_FACTORY = newFactory(Class.class, r0);
        TypeAdapter<BitSet> r02 = new AnonymousClass2().nullSafe();
        BIT_SET = r02;
        BIT_SET_FACTORY = newFactory(BitSet.class, r02);
        AnonymousClass3 r03 = new AnonymousClass3();
        BOOLEAN = r03;
        BOOLEAN_AS_STRING = new AnonymousClass4();
        BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, r03);
        AnonymousClass5 r04 = new AnonymousClass5();
        BYTE = r04;
        BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, r04);
        AnonymousClass6 r05 = new AnonymousClass6();
        SHORT = r05;
        SHORT_FACTORY = newFactory(Short.TYPE, Short.class, r05);
        AnonymousClass7 r06 = new AnonymousClass7();
        INTEGER = r06;
        INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, r06);
        TypeAdapter<AtomicInteger> r07 = new AnonymousClass8().nullSafe();
        ATOMIC_INTEGER = r07;
        ATOMIC_INTEGER_FACTORY = newFactory(AtomicInteger.class, r07);
        TypeAdapter<AtomicBoolean> r08 = new AnonymousClass10().nullSafe();
        ATOMIC_BOOLEAN = r08;
        ATOMIC_BOOLEAN_FACTORY = newFactory(AtomicBoolean.class, r08);
        TypeAdapter<AtomicIntegerArray> r09 = new AnonymousClass11().nullSafe();
        ATOMIC_INTEGER_ARRAY = r09;
        ATOMIC_INTEGER_ARRAY_FACTORY = newFactory(AtomicIntegerArray.class, r09);
        LONG = new AnonymousClass13();
        LONG_AS_STRING = new AnonymousClass14();
        FLOAT = new FloatAdapter(false);
        FLOAT_STRICT = new FloatAdapter(true);
        DOUBLE = new DoubleAdapter(false);
        DOUBLE_STRICT = new DoubleAdapter(true);
        AnonymousClass15 r010 = new AnonymousClass15();
        CHARACTER = r010;
        CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, r010);
        AnonymousClass16 r011 = new AnonymousClass16();
        STRING = r011;
        AnonymousClass17 r1 = new AnonymousClass17();
        BIG_DECIMAL = r1;
        BIG_DECIMAL_FACTORY = newFactory(BigDecimal.class, r1);
        AnonymousClass18 r12 = new AnonymousClass18();
        BIG_INTEGER = r12;
        BIG_INTEGER_FACTORY = newFactory(BigInteger.class, r12);
        AnonymousClass19 r13 = new AnonymousClass19();
        LAZILY_PARSED_NUMBER = r13;
        LAZILY_PARSED_NUMBER_FACTORY = newFactory(LazilyParsedNumber.class, r13);
        STRING_FACTORY = newFactory(String.class, r011);
        AnonymousClass20 r012 = new AnonymousClass20();
        STRING_BUILDER = r012;
        STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, r012);
        AnonymousClass21 r013 = new AnonymousClass21();
        STRING_BUFFER = r013;
        STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, r013);
        AnonymousClass22 r014 = new AnonymousClass22();
        URL = r014;
        URL_FACTORY = newFactory(URL.class, r014);
        AnonymousClass23 r015 = new AnonymousClass23();
        URI = r015;
        URI_FACTORY = newFactory(URI.class, r015);
        AnonymousClass24 r016 = new AnonymousClass24();
        INET_ADDRESS = r016;
        INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, r016);
        AnonymousClass25 r017 = new AnonymousClass25();
        UUID = r017;
        UUID_FACTORY = newFactory(UUID.class, r017);
        TypeAdapter<Currency> r018 = new AnonymousClass26().nullSafe();
        CURRENCY = r018;
        CURRENCY_FACTORY = newFactory(Currency.class, r018);
        AnonymousClass27 r019 = new AnonymousClass27(new String[]{"year", "month", "dayOfMonth", "hourOfDay", "minute", "second"});
        CALENDAR = r019;
        CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, r019);
        AnonymousClass28 r020 = new AnonymousClass28();
        LOCALE = r020;
        LOCALE_FACTORY = newFactory(Locale.class, r020);
        JsonElementTypeAdapter r021 = JsonElementTypeAdapter.ADAPTER;
        JSON_ELEMENT = r021;
        JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, r021);
        ENUM_FACTORY = EnumTypeAdapter.FACTORY;
    }

    public static /* synthetic */ void access$000(double r0) {
        checkValidFloatingPoint(r0);
    }

    public static /* synthetic */ int access$100(long r0) {
        return toIntExact(r0);
    }

    public static TypeAdapter<AtomicLong> atomicLongAdapter(TypeAdapter<Number> r1) {
        Objects.requireNonNull(r1);
        return new AnonymousClass9(r1).nullSafe();
    }

    public static TypeAdapter<AtomicLongArray> atomicLongArrayAdapter(TypeAdapter<Number> r1) {
        Objects.requireNonNull(r1);
        return new AnonymousClass12(r1).nullSafe();
    }

    private static void checkValidFloatingPoint(double r2) {
        if (Double.isNaN(r2) == true) goto L8;
        if (Double.isInfinite(r2) == true) goto L8;
        return;
    L8:
        throw new IllegalArgumentException(r2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
    }

    public static TypeAdapterFactory javaTimeTypeAdapterFactory() {
        TypeAdapter<LocalTime> r2 = JavaTimeTypeAdapters.LOCAL_TIME;     // Catch: Throwable -> L5
        return ((FactorySupplier) JavaTimeTypeAdapters.class.getDeclaredConstructor(null).newInstance(null)).get();
    L8:
        return null;
    }

    public static <TT> TypeAdapterFactory newFactory(TypeToken<TT> r1, TypeAdapter<TT> r2) {
        return new AnonymousClass29(r1, r2);
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> r1, TypeAdapter<TT> r2) {
        return new AnonymousClass30(r1, r2);
    }

    public static <TT> TypeAdapterFactory newFactory(Class<TT> r1, Class<TT> r2, TypeAdapter<? super TT> r3) {
        return new AnonymousClass31(r1, r2, r3);
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(Class<TT> r1, Class<? extends TT> r2, TypeAdapter<? super TT> r3) {
        return new AnonymousClass32(r1, r2, r3);
    }

    public static <T1> TypeAdapterFactory newTypeHierarchyFactory(Class<T1> r1, TypeAdapter<T1> r2) {
        return new AnonymousClass33(r1, r2);
    }

    private static int toIntExact(long r3) {
        int r0 = (int) r3;
        if (r0 != r3) goto L6;
        return r0;
    L6:
        throw new IllegalArgumentException("Too big for an int: " + r3);
    }
}
