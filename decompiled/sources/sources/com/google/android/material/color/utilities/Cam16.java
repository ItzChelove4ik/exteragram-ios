package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public final class Cam16 {
    static final double[][] CAM16RGB_TO_XYZ = null;
    static final double[][] XYZ_TO_CAM16RGB = null;
    private final double astar;
    private final double bstar;
    private final double chroma;
    private final double hue;
    private final double j;
    private final double jstar;
    private final double m;
    private final double q;
    private final double s;
    private final double[] tempArray;

    static {
        XYZ_TO_CAM16RGB = new double[][]{new double[]{0.401288d, 0.650173d, -0.051461d}, new double[]{-0.250268d, 1.204414d, 0.045854d}, new double[]{-0.002079d, 0.048952d, 0.953127d}};
        CAM16RGB_TO_XYZ = new double[][]{new double[]{1.8620678d, -1.0112547d, 0.14918678d}, new double[]{0.38752654d, 0.62144744d, -0.00897398d}, new double[]{-0.0158415d, -0.03412294d, 1.0499644d}};
    }

    private Cam16(double r2, double r4, double r6, double r8, double r10, double r12, double r14, double r16, double r18) {
        this.tempArray = new double[]{0.0d, 0.0d, 0.0d};
        this.hue = r2;
        this.chroma = r4;
        this.j = r6;
        this.q = r8;
        this.m = r10;
        this.s = r12;
        this.jstar = r14;
        this.astar = r16;
        this.bstar = r18;
    }

    public static Cam16 fromInt(int r1) {
        return fromIntInViewingConditions(r1, ViewingConditions.DEFAULT);
    }

    public static Cam16 fromIntInViewingConditions(int r18, ViewingConditions r19) {
        double r3 = ColorUtils.linearized((16711680 & r18) >> 16);
        double r1 = ColorUtils.linearized((65280 & r18) >> 8);
        double r5 = ColorUtils.linearized(r18 & 255);
        return fromXyzInViewingConditions(((0.41233895d * r3) + (0.35762064d * r1)) + (0.18051042d * r5), ((0.2126d * r3) + (0.7152d * r1)) + (0.0722d * r5), ((r3 * 0.01932141d) + (r1 * 0.11916382d)) + (r5 * 0.95034478d), r19);
    }

    public static Cam16 fromJch(double r7, double r9, double r11) {
        return fromJchInViewingConditions(r7, r9, r11, ViewingConditions.DEFAULT);
    }

    private static Cam16 fromJchInViewingConditions(double r27, double r29, double r31, ViewingConditions r33) {
        double r4 = r27 / 100.0d;
        double r15 = (((4.0d / r33.getC()) * Math.sqrt(r4)) * (r33.getAw() + 4.0d)) * r33.getFlRoot();
        double r17 = r29 * r33.getFlRoot();
        double r19 = Math.sqrt(((r29 / Math.sqrt(r4)) * r33.getC()) / (r33.getAw() + 4.0d)) * 50.0d;
        double r0 = Math.toRadians(r31);
        double r21 = (1.7000000000000002d * r27) / ((0.007d * r27) + 1.0d);
        double r2 = Math.log1p(0.0228d * r17) * 43.859649122807014d;
        return new Cam16(r31, r29, r27, r15, r17, r19, r21, r2 * Math.cos(r0), r2 * Math.sin(r0));
    }

    public static Cam16 fromUcs(double r7, double r9, double r11) {
        return fromUcsInViewingConditions(r7, r9, r11, ViewingConditions.DEFAULT);
    }

    public static Cam16 fromUcsInViewingConditions(double r4, double r6, double r8, ViewingConditions r10) {
        double r0 = (Math.expm1(Math.hypot(r6, r8) * 0.0228d) / 0.0228d) / r10.getFlRoot();
        double r62 = Math.atan2(r8, r6) * 57.29577951308232d;
        if (r62 >= 0.0d) goto L5;
        r62 = r62 + 360.0d;
    L5:
        double r82 = r62;
        return fromJchInViewingConditions(r4 / (1.0d - ((r4 - 100.0d) * 0.007d)), r0, r82, r10);
    }

    public static Cam16 fromXyzInViewingConditions(double r35, double r37, double r39, ViewingConditions r41) {
        double[][] r0 = XYZ_TO_CAM16RGB;
        double[] r2 = r0[0];
        double r3 = ((r2[0] * r35) + (r2[1] * r37)) + (r2[2] * r39);
        double[] r22 = r0[1];
        double r7 = ((r22[0] * r35) + (r22[1] * r37)) + (r22[2] * r39);
        double[] r02 = r0[2];
        double r9 = ((r02[0] * r35) + (r02[1] * r37)) + (r02[2] * r39);
        double r1 = r41.getRgbD()[0] * r3;
        double r32 = r41.getRgbD()[1] * r7;
        double r5 = r41.getRgbD()[2] * r9;
        double r72 = Math.pow((r41.getFl() * Math.abs(r1)) / 100.0d, 0.42d);
        double r13 = Math.pow((r41.getFl() * Math.abs(r32)) / 100.0d, 0.42d);
        double r92 = Math.pow((r41.getFl() * Math.abs(r5)) / 100.0d, 0.42d);
        double r03 = ((Math.signum(r1) * 400.0d) * r72) / (r72 + 27.13d);
        double r23 = ((Math.signum(r32) * 400.0d) * r13) / (r13 + 27.13d);
        double r4 = ((Math.signum(r5) * 400.0d) * r92) / (r92 + 27.13d);
        double r8 = (((r03 * 11.0d) + ((-12.0d) * r23)) + r4) / 11.0d;
        double r6 = ((r03 + r23) - (r4 * 2.0d)) / 9.0d;
        double r24 = r23 * 20.0d;
        double r14 = (((r03 * 20.0d) + r24) + (21.0d * r4)) / 20.0d;
        double r04 = (((r03 * 40.0d) + r24) + r4) / 20.0d;
        double r25 = Math.toDegrees(Math.atan2(r6, r8));
        if (r25 >= 0.0d) goto L7;
        r25 = r25 + 360.0d;
    L5:
        double r17 = r25;
        double r26 = Math.toRadians(r17);
        double r21 = Math.pow((r04 * r41.getNbb()) / r41.getAw(), r41.getC() * r41.getZ()) * 100.0d;
        double r19 = r21 / 100.0d;
        double r05 = ((4.0d / r41.getC()) * Math.sqrt(r19)) * (r41.getAw() + 4.0d);
        double r232 = r41.getFlRoot() * r05;
        if (r17 >= 20.14d) goto L12;
        double r12 = r17 + 360.0d;
    L13:
        double r06 = ((((((Math.cos(Math.toRadians(r12) + 2.0d) + 3.8d) * 0.25d) * 3846.153846153846d) * r41.getNc()) * r41.getNcb()) * Math.hypot(r8, r6)) / (r14 + 0.305d);
        double r62 = Math.pow(1.64d - Math.pow(0.29d, r41.getN()), 0.73d) * Math.pow(r06, 0.9d);
        double r192 = r62 * Math.sqrt(r19);
        double r252 = r192 * r41.getFlRoot();
        double r27 = Math.sqrt((r62 * r41.getC()) / (r41.getAw() + 4.0d)) * 50.0d;
        double r29 = (1.7000000000000002d * r21) / ((0.007d * r21) + 1.0d);
        double r07 = Math.log1p(0.0228d * r252) * 43.859649122807014d;
        return new Cam16(r17, r192, r21, r232, r252, r27, r29, r07 * Math.cos(r26), r07 * Math.sin(r26));
    L12:
        r12 = r17;
        goto L13
    L7:
        if (r25 < 360.0d) goto L5;
        r25 = r25 - 360.0d;
        goto L5
    }

    public double distance(Cam16 r7) {
        double r0 = getJstar() - r7.getJstar();
        double r2 = getAstar() - r7.getAstar();
        double r4 = getBstar() - r7.getBstar();
        return Math.pow(Math.sqrt(((r0 * r0) + (r2 * r2)) + (r4 * r4)), 0.63d) * 1.41d;
    }

    public double getAstar() {
        return this.astar;
    }

    public double getBstar() {
        return this.bstar;
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getHue() {
        return this.hue;
    }

    public double getJ() {
        return this.j;
    }

    public double getJstar() {
        return this.jstar;
    }

    public double getM() {
        return this.m;
    }

    public double getQ() {
        return this.q;
    }

    public double getS() {
        return this.s;
    }

    public int toInt() {
        return viewed(ViewingConditions.DEFAULT);
    }

    public int viewed(ViewingConditions r7) {
        double[] r6 = xyzInViewingConditions(r7, this.tempArray);
        return ColorUtils.argbFromXyz(r6[0], r6[1], r6[2]);
    }

    public double[] xyzInViewingConditions(ViewingConditions r21, double[] r22) {
        if (getChroma() != 0.0d) goto L5;
    L8:
        double r0 = 0.0d;
    L9:
        double r02 = Math.pow(r0 / Math.pow(1.64d - Math.pow(0.29d, r21.getN()), 0.73d), 1.1111111111111112d);
        double r6 = Math.toRadians(getHue());
        double r8 = (Math.cos(2.0d + r6) + 3.8d) * 0.25d;
        double r10 = r21.getAw() * Math.pow(getJ() / 100.0d, (1.0d / r21.getC()) / r21.getZ());
        double r82 = ((r8 * 3846.153846153846d) * r21.getNc()) * r21.getNcb();
        double r102 = r10 / r21.getNbb();
        double r12 = Math.sin(r6);
        double r62 = Math.cos(r6);
        double r14 = (((0.305d + r102) * 23.0d) * r02) / (((r82 * 23.0d) + ((11.0d * r02) * r62)) + ((r02 * 108.0d) * r12));
        double r63 = r62 * r14;
        double r142 = r14 * r12;
        double r103 = r102 * 460.0d;
        double r03 = (((451.0d * r63) + r103) + (288.0d * r142)) / 1403.0d;
        double r122 = ((r103 - (891.0d * r63)) - (261.0d * r142)) / 1403.0d;
        double r104 = ((r103 - (r63 * 220.0d)) - (r142 * 6300.0d)) / 1403.0d;
        double r04 = (Math.signum(r03) * (100.0d / r21.getFl())) * Math.pow(Math.max(0.0d, (Math.abs(r03) * 27.13d) / (400.0d - Math.abs(r03))), 2.380952380952381d);
        double r123 = (Math.signum(r122) * (100.0d / r21.getFl())) * Math.pow(Math.max(0.0d, (Math.abs(r122) * 27.13d) / (400.0d - Math.abs(r122))), 2.380952380952381d);
        double r64 = (Math.signum(r104) * (100.0d / r21.getFl())) * Math.pow(Math.max(0.0d, (Math.abs(r104) * 27.13d) / (400.0d - Math.abs(r104))), 2.380952380952381d);
        double r05 = r04 / r21.getRgbD()[0];
        double r124 = r123 / r21.getRgbD()[1];
        double r65 = r64 / r21.getRgbD()[2];
        double[][] r2 = CAM16RGB_TO_XYZ;
        double[] r83 = r2[0];
        double r9 = ((r83[0] * r05) + (r83[1] * r124)) + (r83[2] * r65);
        double[] r84 = r2[1];
        double r143 = ((r84[0] * r05) + (r84[1] * r124)) + (r84[2] * r65);
        double[] r23 = r2[2];
        double r06 = ((r05 * r23[0]) + (r124 * r23[1])) + (r65 * r23[2]);
        if (r22 == null) goto L14;
        r22[0] = r9;
        r22[1] = r143;
        r22[2] = r06;
        return r22;
    L14:
        return new double[]{r9, r143, r06};
    L5:
        if (getJ() == 0.0d) goto L8;
        r0 = getChroma() / Math.sqrt(getJ() / 100.0d);
        goto L9
    }
}
