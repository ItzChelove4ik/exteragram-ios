package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.NumberLimits;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public enum ToNumberPolicy extends Enum<ToNumberPolicy> implements ToNumberStrategy {
    private static final /* synthetic */ ToNumberPolicy[] $VALUES = null;
    public static final ToNumberPolicy BIG_DECIMAL = null;
    public static final ToNumberPolicy DOUBLE = null;
    public static final ToNumberPolicy LAZILY_PARSED_NUMBER = null;
    public static final ToNumberPolicy LONG_OR_DOUBLE = null;

    /* renamed from: com.google.gson.ToNumberPolicy$1, reason: invalid class name */
    public enum AnonymousClass1 extends ToNumberPolicy {
        public AnonymousClass1(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.ToNumberStrategy
        public Double readNumber(JsonReader r1) {
            return Double.valueOf(r1.nextDouble());
        }

        @Override // com.google.gson.ToNumberStrategy
        public /* bridge */ /* synthetic */ Number readNumber(JsonReader r1) {
            return readNumber(r1);
        }
    }

    /* renamed from: com.google.gson.ToNumberPolicy$2, reason: invalid class name */
    public enum AnonymousClass2 extends ToNumberPolicy {
        public AnonymousClass2(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.ToNumberStrategy
        public Number readNumber(JsonReader r1) {
            return new LazilyParsedNumber(r1.nextString());
        }
    }

    /* renamed from: com.google.gson.ToNumberPolicy$3, reason: invalid class name */
    public enum AnonymousClass3 extends ToNumberPolicy {
        public AnonymousClass3(String r2, int r3) {
            super(r2, r3, null);
        }

        private Number parseAsDouble(String r5, JsonReader r6) {
            Double r1 = Double.valueOf(r5);     // Catch: NumberFormatException -> L8
            if (r1.isInfinite() == true) goto L11;
            if (r1.isNaN() == true) goto L11;
        L12:
            return r1;
        L11:
            if (r6.isLenient() == true) goto L12;
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + r1 + "; at path " + r6.getPreviousPath());     // Catch: NumberFormatException -> L8
        L8:
            e = move-exception;
            throw new JsonParseException("Cannot parse " + r5 + "; at path " + r6.getPreviousPath(), e);
        }

        @Override // com.google.gson.ToNumberStrategy
        public Number readNumber(JsonReader r4) {
            String r0 = r4.nextString();
            if (r0.indexOf(46) >= 0) goto L5;
            return Long.valueOf(Long.parseLong(r0));
        L9:
            return this.parseAsDouble(r0, r4);
        L5:
            return parseAsDouble(r0, r4);
        }
    }

    /* renamed from: com.google.gson.ToNumberPolicy$4, reason: invalid class name */
    public enum AnonymousClass4 extends ToNumberPolicy {
        public AnonymousClass4(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.ToNumberStrategy
        public /* bridge */ /* synthetic */ Number readNumber(JsonReader r1) {
            return readNumber(r1);
        }

        @Override // com.google.gson.ToNumberStrategy
        public BigDecimal readNumber(JsonReader r5) {
            String r4 = r5.nextString();
            return NumberLimits.parseBigDecimal(r4);
        L5:
            e = move-exception;
            throw new JsonParseException("Cannot parse " + r4 + "; at path " + r5.getPreviousPath(), e);
        }
    }

    private static /* synthetic */ ToNumberPolicy[] $values() {
        return new ToNumberPolicy[]{DOUBLE, LAZILY_PARSED_NUMBER, LONG_OR_DOUBLE, BIG_DECIMAL};
    }

    static {
        DOUBLE = new AnonymousClass1("DOUBLE", 0);
        LAZILY_PARSED_NUMBER = new AnonymousClass2("LAZILY_PARSED_NUMBER", 1);
        LONG_OR_DOUBLE = new AnonymousClass3("LONG_OR_DOUBLE", 2);
        BIG_DECIMAL = new AnonymousClass4("BIG_DECIMAL", 3);
        $VALUES = $values();
    }

    ToNumberPolicy(String r1, int r2) {
    }

    /* synthetic */ ToNumberPolicy(String r1, int r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static ToNumberPolicy valueOf(String r1) {
        return (ToNumberPolicy) Enum.valueOf(ToNumberPolicy.class, r1);
    }

    public static ToNumberPolicy[] values() {
        return (ToNumberPolicy[]) $VALUES.clone();
    }
}
