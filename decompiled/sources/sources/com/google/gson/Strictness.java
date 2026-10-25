package com.google.gson;

/* loaded from: classes.dex */
public enum Strictness extends Enum<Strictness> {
    private static final /* synthetic */ Strictness[] $VALUES = null;
    public static final Strictness LEGACY_STRICT = null;
    public static final Strictness LENIENT = null;
    public static final Strictness STRICT = null;

    private static /* synthetic */ Strictness[] $values() {
        return new Strictness[]{LENIENT, LEGACY_STRICT, STRICT};
    }

    static {
        LENIENT = new Strictness("LENIENT", 0);
        LEGACY_STRICT = new Strictness("LEGACY_STRICT", 1);
        STRICT = new Strictness("STRICT", 2);
        $VALUES = $values();
    }

    Strictness(String r1, int r2) {
    }

    public static Strictness valueOf(String r1) {
        return (Strictness) Enum.valueOf(Strictness.class, r1);
    }

    public static Strictness[] values() {
        return (Strictness[]) $VALUES.clone();
    }
}
