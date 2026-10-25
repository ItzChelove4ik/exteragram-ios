package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public enum Variant extends Enum<Variant> {
    private static final /* synthetic */ Variant[] $VALUES = null;
    public static final Variant CONTENT = null;
    public static final Variant EXPRESSIVE = null;
    public static final Variant FIDELITY = null;
    public static final Variant FRUIT_SALAD = null;
    public static final Variant MONOCHROME = null;
    public static final Variant NEUTRAL = null;
    public static final Variant RAINBOW = null;
    public static final Variant TONAL_SPOT = null;
    public static final Variant VIBRANT = null;

    private static /* synthetic */ Variant[] $values() {
        return new Variant[]{MONOCHROME, NEUTRAL, TONAL_SPOT, VIBRANT, EXPRESSIVE, FIDELITY, CONTENT, RAINBOW, FRUIT_SALAD};
    }

    static {
        MONOCHROME = new Variant("MONOCHROME", 0);
        NEUTRAL = new Variant("NEUTRAL", 1);
        TONAL_SPOT = new Variant("TONAL_SPOT", 2);
        VIBRANT = new Variant("VIBRANT", 3);
        EXPRESSIVE = new Variant("EXPRESSIVE", 4);
        FIDELITY = new Variant("FIDELITY", 5);
        CONTENT = new Variant("CONTENT", 6);
        RAINBOW = new Variant("RAINBOW", 7);
        FRUIT_SALAD = new Variant("FRUIT_SALAD", 8);
        $VALUES = $values();
    }

    Variant(String r1, int r2) {
    }

    public static Variant valueOf(String r1) {
        return (Variant) Enum.valueOf(Variant.class, r1);
    }

    public static Variant[] values() {
        return (Variant[]) $VALUES.clone();
    }
}
