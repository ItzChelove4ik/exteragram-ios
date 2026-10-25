package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public final class ViewingConditions {
    public static final ViewingConditions DEFAULT = null;
    private final double aw;

    /* renamed from: c, reason: collision with root package name */
    private final double f39c;
    private final double fl;
    private final double flRoot;
    private final double n;
    private final double nbb;
    private final double nc;
    private final double ncb;
    private final double[] rgbD;
    private final double z;

    static {
        DEFAULT = defaultWithBackgroundLstar(50.0d);
    }

    private ViewingConditions(double r1, double r3, double r5, double r7, double r9, double r11, double[] r13, double r14, double r16, double r18) {
        this.n = r1;
        this.aw = r3;
        this.nbb = r5;
        this.ncb = r7;
        this.f39c = r9;
        this.nc = r11;
        this.rgbD = r13;
        this.fl = r14;
        this.flRoot = r16;
        this.z = r18;
    }

    public static ViewingConditions defaultWithBackgroundLstar(double r8) {
        return make(ColorUtils.whitePointD65(), (ColorUtils.yFromLstar(50.0d) * 63.66197723675813d) / 100.0d, r8, 2.0d, false);
    }

    public static ViewingConditions make(double[] r43, double r44, double r46, double r48, boolean r50) {
        double r4 = Math.max(0.1d, r46);
        double[][] r6 = Cam16.XYZ_TO_CAM16RGB;
        double r8 = r43[0];
        double[] r10 = r6[0];
        double r11 = r10[0] * r8;
        double r14 = r43[1];
        double r112 = r11 + (r10[1] * r14);
        double r17 = r43[2];
        double r113 = r112 + (r10[2] * r17);
        double[] r102 = r6[1];
        double r19 = ((r102[0] * r8) + (r102[1] * r14)) + (r102[2] * r17);
        double[] r62 = r6[2];
        double r82 = ((r8 * r62[0]) + (r14 * r62[1])) + (r17 * r62[2]);
        double r34 = (r48 / 10.0d) + 0.8d;
        if (r34 < 0.9d) goto L6;
        double r142 = MathUtils.lerp(0.59d, 0.69d, (r34 - 0.9d) * 10.0d);
    L5:
        double r32 = r142;
        if (r50 == false) goto L10;
        double r172 = 0.1d;
        double r25 = 1.0d;
    L11:
        double r2 = MathUtils.clampDouble(0.0d, 1.0d, r25);
        double[] r3 = {(((100.0d / r113) * r2) + 1.0d) - r2, (((100.0d / r19) * r2) + 1.0d) - r2, (((100.0d / r82) * r2) + 1.0d) - r2};
        double r23 = 5.0d * r44;
        double r252 = 1.0d / (r23 + 1.0d);
        double r27 = ((r252 * r252) * r252) * r252;
        double r143 = 1.0d - r27;
        double r0 = (r27 * r44) + (((r143 * r172) * r143) * Math.cbrt(r23));
        double r42 = ColorUtils.yFromLstar(r4) / r43[1];
        double r41 = Math.sqrt(r42) + 1.48d;
        double r28 = 0.725d / Math.pow(r42, 0.2d);
        double[] r103 = {Math.pow(((r3[0] * r0) * r113) / 100.0d, 0.42d), Math.pow(((r3[1] * r0) * r19) / 100.0d, 0.42d), Math.pow(((r3[2] * r0) * r82) / 100.0d, 0.42d)};
        double r63 = r103[0];
        double r114 = (r63 * 400.0d) / (r63 + 27.13d);
        double r64 = r103[1];
        double r173 = (r64 * 400.0d) / (r64 + 27.13d);
        double r65 = r103[2];
        double[] r22 = {r114, r173, (400.0d * r65) / (r65 + 27.13d)};
        return new ViewingConditions(r42, (((r22[0] * 2.0d) + r22[1]) + (r22[2] * 0.05d)) * r28, r28, r28, r32, r34, r3, r0, Math.pow(r0, 0.25d), r41);
    L10:
        r172 = 0.1d;
        r25 = (1.0d - (Math.exp(((-r44) - 42.0d) / 92.0d) * 0.2777777777777778d)) * r34;
        goto L11
    L6:
        r142 = MathUtils.lerp(0.525d, 0.59d, (r34 - 0.8d) * 10.0d);
        goto L5
    }

    public double getAw() {
        return this.aw;
    }

    public double getC() {
        return this.f39c;
    }

    public double getFl() {
        return this.fl;
    }

    public double getFlRoot() {
        return this.flRoot;
    }

    public double getN() {
        return this.n;
    }

    public double getNbb() {
        return this.nbb;
    }

    public double getNc() {
        return this.nc;
    }

    public double getNcb() {
        return this.ncb;
    }

    public double[] getRgbD() {
        return this.rgbD;
    }

    public double getZ() {
        return this.z;
    }
}
