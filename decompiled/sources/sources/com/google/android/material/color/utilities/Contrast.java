package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public final class Contrast {
    private static final double CONTRAST_RATIO_EPSILON = 0.04d;
    private static final double LUMINANCE_GAMUT_MAP_TOLERANCE = 0.4d;
    public static final double RATIO_30 = 3.0d;
    public static final double RATIO_45 = 4.5d;
    public static final double RATIO_70 = 7.0d;
    public static final double RATIO_MAX = 21.0d;
    public static final double RATIO_MIN = 1.0d;

    private Contrast() {
    }

    public static double darker(double r11, double r13) {
        if (r11 >= 0.0d) goto L5;
    L23:
        return -1.0d;
    L5:
        if (r11 > 100.0d) goto L23;
        double r112 = ColorUtils.yFromLstar(r11);
        double r9 = ((r112 + 5.0d) / r13) - 5.0d;
        if (r9 < 0.0d) goto L23;
        if (r9 > 100.0d) goto L23;
        double r113 = ratioOfYs(r112, r9);
        double r7 = Math.abs(r113 - r13);
        if (r113 < r13) goto L15;
    L17:
        double r114 = ColorUtils.lstarFromY(r9) - LUMINANCE_GAMUT_MAP_TOLERANCE;
        if (r114 < 0.0d) goto L23;
        if (r114 > 100.0d) goto L23;
        return r114;
    L15:
        if (r7 <= CONTRAST_RATIO_EPSILON) goto L17;
        return -1.0d;
    }

    public static double darkerUnsafe(double r0, double r2) {
        return Math.max(0.0d, darker(r0, r2));
    }

    public static double lighter(double r11, double r13) {
        if (r11 >= 0.0d) goto L5;
    L23:
        return -1.0d;
    L5:
        if (r11 > 100.0d) goto L23;
        double r112 = ColorUtils.yFromLstar(r11);
        double r9 = ((r112 + 5.0d) * r13) - 5.0d;
        if (r9 < 0.0d) goto L23;
        if (r9 > 100.0d) goto L23;
        double r113 = ratioOfYs(r9, r112);
        double r7 = Math.abs(r113 - r13);
        if (r113 < r13) goto L15;
    L17:
        double r114 = ColorUtils.lstarFromY(r9) + LUMINANCE_GAMUT_MAP_TOLERANCE;
        if (r114 < 0.0d) goto L23;
        if (r114 > 100.0d) goto L23;
        return r114;
    L15:
        if (r7 <= CONTRAST_RATIO_EPSILON) goto L17;
        return -1.0d;
    }

    public static double lighterUnsafe(double r0, double r2) {
        double r02 = lighter(r0, r2);
        if (r02 >= 0.0d) goto L6;
        return 100.0d;
    L6:
        return r02;
    }

    public static double ratioOfTones(double r0, double r2) {
        return ratioOfYs(ColorUtils.yFromLstar(r0), ColorUtils.yFromLstar(r2));
    }

    public static double ratioOfYs(double r3, double r5) {
        double r0 = Math.max(r3, r5);
        if (r0 == r5) goto L7;
        r3 = r5;
    L7:
        return (r0 + 5.0d) / (r3 + 5.0d);
    }
}
