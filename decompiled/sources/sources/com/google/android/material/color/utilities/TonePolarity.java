package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public enum TonePolarity extends Enum<TonePolarity> {
    private static final /* synthetic */ TonePolarity[] $VALUES = null;
    public static final TonePolarity DARKER = null;
    public static final TonePolarity FARTHER = null;
    public static final TonePolarity LIGHTER = null;
    public static final TonePolarity NEARER = null;

    private static /* synthetic */ TonePolarity[] $values() {
        return new TonePolarity[]{DARKER, LIGHTER, NEARER, FARTHER};
    }

    static {
        DARKER = new TonePolarity("DARKER", 0);
        LIGHTER = new TonePolarity("LIGHTER", 1);
        NEARER = new TonePolarity("NEARER", 2);
        FARTHER = new TonePolarity("FARTHER", 3);
        $VALUES = $values();
    }

    TonePolarity(String r1, int r2) {
    }

    public static TonePolarity valueOf(String r1) {
        return (TonePolarity) Enum.valueOf(TonePolarity.class, r1);
    }

    public static TonePolarity[] values() {
        return (TonePolarity[]) $VALUES.clone();
    }
}
