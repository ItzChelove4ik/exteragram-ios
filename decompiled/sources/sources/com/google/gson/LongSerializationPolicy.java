package com.google.gson;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.gson.internal.bind.TypeAdapters;

/* loaded from: classes.dex */
public enum LongSerializationPolicy extends Enum<LongSerializationPolicy> {
    private static final /* synthetic */ LongSerializationPolicy[] $VALUES = null;
    public static final LongSerializationPolicy DEFAULT = null;
    public static final LongSerializationPolicy STRING = null;

    /* renamed from: com.google.gson.LongSerializationPolicy$1, reason: invalid class name */
    public enum AnonymousClass1 extends LongSerializationPolicy {
        public AnonymousClass1(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.LongSerializationPolicy
        public TypeAdapter<Number> typeAdapter() {
            return TypeAdapters.LONG;
        }
    }

    /* renamed from: com.google.gson.LongSerializationPolicy$2, reason: invalid class name */
    public enum AnonymousClass2 extends LongSerializationPolicy {
        public AnonymousClass2(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.google.gson.LongSerializationPolicy
        public TypeAdapter<Number> typeAdapter() {
            return TypeAdapters.LONG_AS_STRING;
        }
    }

    private static /* synthetic */ LongSerializationPolicy[] $values() {
        return new LongSerializationPolicy[]{DEFAULT, STRING};
    }

    static {
        DEFAULT = new AnonymousClass1(PluginsConstants.Strategy.DEFAULT, 0);
        STRING = new AnonymousClass2("STRING", 1);
        $VALUES = $values();
    }

    LongSerializationPolicy(String r1, int r2) {
    }

    /* synthetic */ LongSerializationPolicy(String r1, int r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static LongSerializationPolicy valueOf(String r1) {
        return (LongSerializationPolicy) Enum.valueOf(LongSerializationPolicy.class, r1);
    }

    public static LongSerializationPolicy[] values() {
        return (LongSerializationPolicy[]) $VALUES.clone();
    }

    public abstract TypeAdapter<Number> typeAdapter();
}
