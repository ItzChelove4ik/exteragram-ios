package androidx.core.graphics;

/* loaded from: classes3.dex */
public enum BlendModeCompat extends Enum<BlendModeCompat> {
    private static final /* synthetic */ BlendModeCompat[] $VALUES = null;
    public static final BlendModeCompat CLEAR = null;
    public static final BlendModeCompat COLOR = null;
    public static final BlendModeCompat COLOR_BURN = null;
    public static final BlendModeCompat COLOR_DODGE = null;
    public static final BlendModeCompat DARKEN = null;
    public static final BlendModeCompat DIFFERENCE = null;
    public static final BlendModeCompat DST = null;
    public static final BlendModeCompat DST_ATOP = null;
    public static final BlendModeCompat DST_IN = null;
    public static final BlendModeCompat DST_OUT = null;
    public static final BlendModeCompat DST_OVER = null;
    public static final BlendModeCompat EXCLUSION = null;
    public static final BlendModeCompat HARD_LIGHT = null;
    public static final BlendModeCompat HUE = null;
    public static final BlendModeCompat LIGHTEN = null;
    public static final BlendModeCompat LUMINOSITY = null;
    public static final BlendModeCompat MODULATE = null;
    public static final BlendModeCompat MULTIPLY = null;
    public static final BlendModeCompat OVERLAY = null;
    public static final BlendModeCompat PLUS = null;
    public static final BlendModeCompat SATURATION = null;
    public static final BlendModeCompat SCREEN = null;
    public static final BlendModeCompat SOFT_LIGHT = null;
    public static final BlendModeCompat SRC = null;
    public static final BlendModeCompat SRC_ATOP = null;
    public static final BlendModeCompat SRC_IN = null;
    public static final BlendModeCompat SRC_OUT = null;
    public static final BlendModeCompat SRC_OVER = null;
    public static final BlendModeCompat XOR = null;

    private static /* synthetic */ BlendModeCompat[] $values() {
        return new BlendModeCompat[]{CLEAR, SRC, DST, SRC_OVER, DST_OVER, SRC_IN, DST_IN, SRC_OUT, DST_OUT, SRC_ATOP, DST_ATOP, XOR, PLUS, MODULATE, SCREEN, OVERLAY, DARKEN, LIGHTEN, COLOR_DODGE, COLOR_BURN, HARD_LIGHT, SOFT_LIGHT, DIFFERENCE, EXCLUSION, MULTIPLY, HUE, SATURATION, COLOR, LUMINOSITY};
    }

    static {
        CLEAR = new BlendModeCompat("CLEAR", 0);
        SRC = new BlendModeCompat("SRC", 1);
        DST = new BlendModeCompat("DST", 2);
        SRC_OVER = new BlendModeCompat("SRC_OVER", 3);
        DST_OVER = new BlendModeCompat("DST_OVER", 4);
        SRC_IN = new BlendModeCompat("SRC_IN", 5);
        DST_IN = new BlendModeCompat("DST_IN", 6);
        SRC_OUT = new BlendModeCompat("SRC_OUT", 7);
        DST_OUT = new BlendModeCompat("DST_OUT", 8);
        SRC_ATOP = new BlendModeCompat("SRC_ATOP", 9);
        DST_ATOP = new BlendModeCompat("DST_ATOP", 10);
        XOR = new BlendModeCompat("XOR", 11);
        PLUS = new BlendModeCompat("PLUS", 12);
        MODULATE = new BlendModeCompat("MODULATE", 13);
        SCREEN = new BlendModeCompat("SCREEN", 14);
        OVERLAY = new BlendModeCompat("OVERLAY", 15);
        DARKEN = new BlendModeCompat("DARKEN", 16);
        LIGHTEN = new BlendModeCompat("LIGHTEN", 17);
        COLOR_DODGE = new BlendModeCompat("COLOR_DODGE", 18);
        COLOR_BURN = new BlendModeCompat("COLOR_BURN", 19);
        HARD_LIGHT = new BlendModeCompat("HARD_LIGHT", 20);
        SOFT_LIGHT = new BlendModeCompat("SOFT_LIGHT", 21);
        DIFFERENCE = new BlendModeCompat("DIFFERENCE", 22);
        EXCLUSION = new BlendModeCompat("EXCLUSION", 23);
        MULTIPLY = new BlendModeCompat("MULTIPLY", 24);
        HUE = new BlendModeCompat("HUE", 25);
        SATURATION = new BlendModeCompat("SATURATION", 26);
        COLOR = new BlendModeCompat("COLOR", 27);
        LUMINOSITY = new BlendModeCompat("LUMINOSITY", 28);
        $VALUES = $values();
    }

    BlendModeCompat(String r1, int r2) {
    }

    public static BlendModeCompat valueOf(String r1) {
        return (BlendModeCompat) Enum.valueOf(BlendModeCompat.class, r1);
    }

    public static BlendModeCompat[] values() {
        return (BlendModeCompat[]) $VALUES.clone();
    }
}
