package org.telegram.ui.Components;

/* loaded from: classes3.dex */
public enum IconBackgroundColors extends Enum<IconBackgroundColors> {
    private static final /* synthetic */ IconBackgroundColors[] $VALUES = null;
    public static final IconBackgroundColors BLUE = null;
    public static final IconBackgroundColors BLUE_ALT = null;
    public static final IconBackgroundColors BLUE_DEEP = null;
    public static final IconBackgroundColors BLUE_LIGHT = null;
    public static final IconBackgroundColors CYAN = null;
    public static final IconBackgroundColors GRAY = null;
    public static final IconBackgroundColors GREEN = null;
    public static final IconBackgroundColors GREEN_DEEP = null;
    public static final IconBackgroundColors ORANGE = null;
    public static final IconBackgroundColors ORANGE_BRIGHT = null;
    public static final IconBackgroundColors ORANGE_DEEP = null;
    public static final IconBackgroundColors PURPLE = null;
    public static final IconBackgroundColors RED = null;
    public final int bottom;
    public final int top;

    private static /* synthetic */ IconBackgroundColors[] $values() {
        return new IconBackgroundColors[]{BLUE, BLUE_ALT, BLUE_DEEP, BLUE_LIGHT, ORANGE, ORANGE_BRIGHT, ORANGE_DEEP, GREEN, GREEN_DEEP, RED, CYAN, PURPLE, GRAY};
    }

    static {
        BLUE = new IconBackgroundColors("BLUE", 0, -14899731, -15431455);
        BLUE_ALT = new IconBackgroundColors("BLUE_ALT", 1, -14899731, -15497247);
        BLUE_DEEP = new IconBackgroundColors("BLUE_DEEP", 2, -11565578, -13276952);
        BLUE_LIGHT = new IconBackgroundColors("BLUE_LIGHT", 3, -14965523, -15431455);
        ORANGE = new IconBackgroundColors("ORANGE", 4, -1007845, -1996271);
        ORANGE_BRIGHT = new IconBackgroundColors("ORANGE_BRIGHT", 5, -1071598, -1608430);
        ORANGE_DEEP = new IconBackgroundColors("ORANGE_DEEP", 6, -881871, -1940716);
        GREEN = new IconBackgroundColors("GREEN", 7, -11154873, -14175180);
        GREEN_DEEP = new IconBackgroundColors("GREEN_DEEP", 8, -14840995, -15172775);
        RED = new IconBackgroundColors("RED", 9, -765355, -2148011);
        CYAN = new IconBackgroundColors("CYAN", 10, -13451058, -14836538);
        PURPLE = new IconBackgroundColors("PURPLE", 11, -3903756, -6335009);
        GRAY = new IconBackgroundColors("GRAY", 12, -7956054, -9534569);
        $VALUES = $values();
    }

    IconBackgroundColors(String r1, int r2, int r3, int r4) {
        this.top = r3;
        this.bottom = r4;
    }

    public static IconBackgroundColors valueOf(String r1) {
        return (IconBackgroundColors) Enum.valueOf(IconBackgroundColors.class, r1);
    }

    public static IconBackgroundColors[] values() {
        return (IconBackgroundColors[]) $VALUES.clone();
    }
}
