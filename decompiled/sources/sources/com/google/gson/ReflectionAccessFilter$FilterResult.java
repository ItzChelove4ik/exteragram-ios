package com.google.gson;

/* loaded from: classes.dex */
public enum ReflectionAccessFilter$FilterResult extends Enum<ReflectionAccessFilter$FilterResult> {
    private static final /* synthetic */ ReflectionAccessFilter$FilterResult[] $VALUES = null;
    public static final ReflectionAccessFilter$FilterResult ALLOW = null;
    public static final ReflectionAccessFilter$FilterResult BLOCK_ALL = null;
    public static final ReflectionAccessFilter$FilterResult BLOCK_INACCESSIBLE = null;
    public static final ReflectionAccessFilter$FilterResult INDECISIVE = null;

    private static /* synthetic */ ReflectionAccessFilter$FilterResult[] $values() {
        return new ReflectionAccessFilter$FilterResult[]{ALLOW, INDECISIVE, BLOCK_INACCESSIBLE, BLOCK_ALL};
    }

    static {
        ALLOW = new ReflectionAccessFilter$FilterResult("ALLOW", 0);
        INDECISIVE = new ReflectionAccessFilter$FilterResult("INDECISIVE", 1);
        BLOCK_INACCESSIBLE = new ReflectionAccessFilter$FilterResult("BLOCK_INACCESSIBLE", 2);
        BLOCK_ALL = new ReflectionAccessFilter$FilterResult("BLOCK_ALL", 3);
        $VALUES = $values();
    }

    ReflectionAccessFilter$FilterResult(String r1, int r2) {
    }

    public static ReflectionAccessFilter$FilterResult valueOf(String r1) {
        return (ReflectionAccessFilter$FilterResult) Enum.valueOf(ReflectionAccessFilter$FilterResult.class, r1);
    }

    public static ReflectionAccessFilter$FilterResult[] values() {
        return (ReflectionAccessFilter$FilterResult[]) $VALUES.clone();
    }
}
