package org.telegram.ui.ActionBar;

/* loaded from: classes6.dex */
public enum EdgeToEdgeSupportMode extends Enum<EdgeToEdgeSupportMode> {
    private static final /* synthetic */ EdgeToEdgeSupportMode[] $VALUES = null;
    public static final EdgeToEdgeSupportMode FULL = null;
    public static final EdgeToEdgeSupportMode NONE = null;
    public static final EdgeToEdgeSupportMode VERTICAL = null;

    private static /* synthetic */ EdgeToEdgeSupportMode[] $values() {
        return new EdgeToEdgeSupportMode[]{NONE, VERTICAL, FULL};
    }

    static {
        NONE = new EdgeToEdgeSupportMode("NONE", 0);
        VERTICAL = new EdgeToEdgeSupportMode("VERTICAL", 1);
        FULL = new EdgeToEdgeSupportMode("FULL", 2);
        $VALUES = $values();
    }

    EdgeToEdgeSupportMode(String r1, int r2) {
    }

    public static EdgeToEdgeSupportMode valueOf(String r1) {
        return (EdgeToEdgeSupportMode) Enum.valueOf(EdgeToEdgeSupportMode.class, r1);
    }

    public static EdgeToEdgeSupportMode[] values() {
        return (EdgeToEdgeSupportMode[]) $VALUES.clone();
    }
}
