package org.chromium.net.telemetry;

/* loaded from: classes5.dex */
public enum OptionalBoolean extends Enum<OptionalBoolean> {
    private static final /* synthetic */ OptionalBoolean[] $VALUES = null;
    public static final OptionalBoolean FALSE = null;
    public static final OptionalBoolean TRUE = null;
    public static final OptionalBoolean UNSET = null;
    private final int mValue;

    private static /* synthetic */ OptionalBoolean[] $values() {
        return new OptionalBoolean[]{UNSET, TRUE, FALSE};
    }

    static {
        UNSET = new OptionalBoolean("UNSET", 0, 0);
        TRUE = new OptionalBoolean("TRUE", 1, 1);
        FALSE = new OptionalBoolean("FALSE", 2, 2);
        $VALUES = $values();
    }

    OptionalBoolean(String r1, int r2, int r3) {
        this.mValue = r3;
    }

    public static OptionalBoolean fromBoolean(Boolean r0) {
        if (r0 != null) goto L6;
        return UNSET;
    L6:
        if (r0.booleanValue() == false) goto L10;
        return TRUE;
    L10:
        return FALSE;
    }

    public static OptionalBoolean valueOf(String r1) {
        return (OptionalBoolean) Enum.valueOf(OptionalBoolean.class, r1);
    }

    public static OptionalBoolean[] values() {
        return (OptionalBoolean[]) $VALUES.clone();
    }

    public int getValue() {
        return this.mValue;
    }
}
