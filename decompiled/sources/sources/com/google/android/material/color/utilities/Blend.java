package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public class Blend {
    private Blend() {
    }

    public static int cam16Ucs(int r18, int r19, double r20) {
        Cam16 r0 = Cam16.fromInt(r18);
        Cam16 r1 = Cam16.fromInt(r19);
        double r2 = r0.getJstar();
        double r4 = r0.getAstar();
        double r6 = r0.getBstar();
        double r8 = r1.getJstar();
        double r10 = r1.getAstar();
        return Cam16.fromUcs(r2 + ((r8 - r2) * r20), r4 + ((r10 - r4) * r20), r6 + ((r1.getBstar() - r6) * r20)).toInt();
    }

    public static int harmonize(int r10, int r11) {
        Hct r102 = Hct.fromInt(r10);
        Hct r112 = Hct.fromInt(r11);
        double r0 = Math.min(MathUtils.differenceDegrees(r102.getHue(), r112.getHue()) * 0.5d, 15.0d);
        return Hct.from(MathUtils.sanitizeDegreesDouble(r102.getHue() + (r0 * MathUtils.rotationDirection(r102.getHue(), r112.getHue()))), r102.getChroma(), r102.getTone()).toInt();
    }

    public static int hctHue(int r6, int r7, double r8) {
        Cam16 r72 = Cam16.fromInt(cam16Ucs(r6, r7, r8));
        Cam16 r82 = Cam16.fromInt(r6);
        return Hct.from(r72.getHue(), r82.getChroma(), ColorUtils.lstarFromArgb(r6)).toInt();
    }
}
