package androidx.core.content.res;

import androidx.core.graphics.ColorUtils;

/* loaded from: classes3.dex */
public class CamColor {
    private final float mAstar;
    private final float mBstar;
    private final float mChroma;
    private final float mHue;
    private final float mJ;
    private final float mJstar;
    private final float mM;
    private final float mQ;
    private final float mS;

    public CamColor(float r1, float r2, float r3, float r4, float r5, float r6, float r7, float r8, float r9) {
        this.mHue = r1;
        this.mChroma = r2;
        this.mJ = r3;
        this.mQ = r4;
        this.mM = r5;
        this.mS = r6;
        this.mJstar = r7;
        this.mAstar = r8;
        this.mBstar = r9;
    }

    private static CamColor findCamByJ(float r12, float r13, float r14) {
        float r1 = 100.0f;
        float r2 = 1000.0f;
        float r5 = 0.0f;
        CamColor r4 = null;
        float r3 = 1000.0f;
    L4:
        if (Math.abs(r5 - r1) <= 0.01f) goto L19;
        float r6 = ((r1 - r5) / 2.0f) + r5;
        int r7 = fromJch(r6, r13, r12).viewedInSrgb();
        float r8 = CamUtils.lStarFromInt(r7);
        float r9 = Math.abs(r14 - r8);
        if (r9 >= 0.2f) goto L11;
        CamColor r72 = fromColor(r7);
        float r10 = r72.distance(fromJch(r72.getJ(), r72.getChroma(), r12));
        if (r10 > 1.0f) goto L11;
        r4 = r72;
        r2 = r9;
        r3 = r10;
    L11:
        if (r2 != 0.0f) goto L16;
        if (r3 != 0.0f) goto L16;
        return r4;
    L16:
        if (r8 < r14) goto L17;
        r1 = r6;
        goto L4
    L17:
        r5 = r6;
        goto L4
    L19:
        return r4;
    }

    public static CamColor fromColor(int r14) {
        float[] r0 = new float[7];
        float[] r2 = new float[3];
        fromColorInViewingConditions(r14, ViewingConditions.DEFAULT, r0, r2);
        return new CamColor(r2[0], r2[1], r0[0], r0[1], r0[2], r0[3], r0[4], r0[5], r0[6]);
    }

    public static void fromColorInViewingConditions(int r21, ViewingConditions r22, float[] r23, float[] r24) {
        CamUtils.xyzFromInt(r21, r24);
        float[][] r0 = CamUtils.XYZ_TO_CAM16RGB;
        float r3 = r24[0];
        float[] r4 = r0[0];
        float r5 = r4[0] * r3;
        float r7 = r24[1];
        float r52 = r5 + (r4[1] * r7);
        float r9 = r24[2];
        float r53 = r52 + (r4[2] * r9);
        float[] r42 = r0[1];
        float r10 = ((r42[0] * r3) + (r42[1] * r7)) + (r42[2] * r9);
        float[] r02 = r0[2];
        float r32 = ((r3 * r02[0]) + (r7 * r02[1])) + (r9 * r02[2]);
        float r03 = r22.getRgbD()[0] * r53;
        float r43 = r22.getRgbD()[1] * r10;
        float r54 = r22.getRgbD()[2] * r32;
        float r33 = (float) Math.pow((r22.getFl() * Math.abs(r03)) / 100.0d, 0.42d);
        float r72 = (float) Math.pow((r22.getFl() * Math.abs(r43)) / 100.0d, 0.42d);
        float r92 = (float) Math.pow((r22.getFl() * Math.abs(r54)) / 100.0d, 0.42d);
        float r04 = ((Math.signum(r03) * 400.0f) * r33) / (r33 + 27.13f);
        float r34 = ((Math.signum(r43) * 400.0f) * r72) / (r72 + 27.13f);
        float r44 = ((Math.signum(r54) * 400.0f) * r92) / (r92 + 27.13f);
        double r55 = r44;
        float r73 = ((float) (((r04 * 11.0d) + (r34 * (-12.0d))) + r55)) / 11.0f;
        float r56 = ((float) ((r04 + r34) - (r55 * 2.0d))) / 9.0f;
        float r35 = r34 * 20.0f;
        float r93 = (((r04 * 20.0f) + r35) + (21.0f * r44)) / 20.0f;
        float r05 = (((r04 * 40.0f) + r35) + r44) / 20.0f;
        float r36 = (((float) Math.atan2(r56, r73)) * 180.0f) / 3.1415927f;
        if (r36 >= 0.0f) goto L6;
        r36 = r36 + 360.0f;
    L8:
        float r8 = (3.1415927f * r36) / 180.0f;
        float r06 = ((float) Math.pow((r05 * r22.getNbb()) / r22.getAw(), r22.getC() * r22.getZ())) * 100.0f;
        float r74 = (((4.0f / r22.getC()) * ((float) Math.sqrt(r06 / 100.0f))) * (r22.getAw() + 4.0f)) * r22.getFlRoot();
        if (r36 >= 20.14d) goto L11;
        float r15 = 360.0f + r36;
    L12:
        float r57 = ((float) Math.sqrt(r06 / 100.0d)) * (((float) Math.pow(1.64d - Math.pow(0.29d, r22.getN()), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos(((r15 * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * r22.getNc()) * r22.getNcb()) * ((float) Math.sqrt((r73 * r73) + (r56 * r56)))) / (r93 + 0.305f), 0.9d)));
        float r6 = r22.getFlRoot() * r57;
        float r45 = ((float) Math.sqrt((r4 * r22.getC()) / (r22.getAw() + 4.0f))) * 50.0f;
        float r94 = (1.7f * r06) / ((0.007f * r06) + 1.0f);
        float r102 = ((float) Math.log((0.0228f * r6) + 1.0f)) * 43.85965f;
        double r11 = r8;
        float r82 = ((float) Math.cos(r11)) * r102;
        float r103 = r102 * ((float) Math.sin(r11));
        r24[0] = r36;
        r24[1] = r57;
        if (r23 == null) goto L16;
        r23[0] = r06;
        r23[1] = r74;
        r23[2] = r6;
        r23[3] = r45;
        r23[4] = r94;
        r23[5] = r82;
        r23[6] = r103;
        return;
    L16:
        return;
    L11:
        r15 = r36;
        goto L12
    L6:
        if (r36 < 360.0f) goto L8;
        r36 = r36 - 360.0f;
        goto L8
    }

    private static CamColor fromJch(float r1, float r2, float r3) {
        return fromJchInFrame(r1, r2, r3, ViewingConditions.DEFAULT);
    }

    private static CamColor fromJchInFrame(float r12, float r13, float r14, ViewingConditions r15) {
        float r0 = (((4.0f / r15.getC()) * ((float) Math.sqrt(r12 / 100.0d))) * (r15.getAw() + 4.0f)) * r15.getFlRoot();
        float r2 = r15.getFlRoot() * r13;
        float r6 = ((float) Math.sqrt(((r13 / ((float) Math.sqrt(r4))) * r15.getC()) / (r15.getAw() + 4.0f))) * 50.0f;
        float r7 = (1.7f * r12) / ((0.007f * r12) + 1.0f);
        float r4 = ((float) Math.log((r2 * 0.0228d) + 1.0d)) * 43.85965f;
        double r8 = (3.1415927f * r14) / 180.0f;
        return new CamColor(r14, r13, r12, r0, r2, r6, r7, ((float) Math.cos(r8)) * r4, r4 * ((float) Math.sin(r8)));
    }

    public static int toColor(float r1, float r2, float r3) {
        return toColor(r1, r2, r3, ViewingConditions.DEFAULT);
    }

    public static int toColor(float r6, float r7, float r8, ViewingConditions r9) {
        if (r7 < 1.0d) goto L32;
        if (Math.round(r8) <= 0.0d) goto L32;
        if (Math.round(r8) >= 100.0d) goto L32;
        if (r6 >= 0.0f) goto L12;
        float r62 = 0.0f;
    L13:
        CamColor r3 = null;
        boolean r2 = true;
        float r1 = 0.0f;
        float r0 = r7;
    L15:
        if (Math.abs(r1 - r7) < 0.4f) goto L26;
        CamColor r4 = findCamByJ(r62, r0, r8);
        if (r2 == true) goto L18;
        if (r4 != null) goto L24;
        r7 = r0;
    L25:
        r0 = ((r7 - r1) / 2.0f) + r1;
        goto L15
    L24:
        r1 = r0;
        r3 = r4;
        goto L25
    L18:
        if (r4 != null) goto L20;
        r0 = ((r7 - r1) / 2.0f) + r1;
        r2 = false;
        goto L15
    L20:
        return r4.viewed(r9);
    L26:
        if (r3 != null) goto L30;
        return CamUtils.intFromLStar(r8);
    L30:
        return r3.viewed(r9);
    L12:
        r62 = Math.min(360.0f, r6);
    L32:
        return CamUtils.intFromLStar(r8);
    }

    public float distance(CamColor r4) {
        float r0 = getJStar() - r4.getJStar();
        float r1 = getAStar() - r4.getAStar();
        float r3 = getBStar() - r4.getBStar();
        return (float) (Math.pow(Math.sqrt(((r0 * r0) + (r1 * r1)) + (r3 * r3)), 0.63d) * 1.41d);
    }

    public float getAStar() {
        return this.mAstar;
    }

    public float getBStar() {
        return this.mBstar;
    }

    public float getChroma() {
        return this.mChroma;
    }

    public float getHue() {
        return this.mHue;
    }

    public float getJ() {
        return this.mJ;
    }

    public float getJStar() {
        return this.mJstar;
    }

    public int viewed(ViewingConditions r18) {
        if (getChroma() != 0.0d) goto L5;
    L8:
        float r0 = 0.0f;
    L9:
        float r02 = (float) Math.pow(r0 / Math.pow(1.64d - Math.pow(0.29d, r18.getN()), 0.73d), 1.1111111111111112d);
        double r6 = (getHue() * 3.1415927f) / 180.0f;
        float r1 = ((float) (Math.cos(2.0d + r6) + 3.8d)) * 0.25f;
        float r8 = r18.getAw() * ((float) Math.pow(getJ() / 100.0d, (1.0d / r18.getC()) / r18.getZ()));
        float r12 = ((r1 * 3846.1538f) * r18.getNc()) * r18.getNcb();
        float r82 = r8 / r18.getNbb();
        float r4 = (float) Math.sin(r6);
        float r5 = (float) Math.cos(r6);
        float r62 = (((0.305f + r82) * 23.0f) * r02) / (((r12 * 23.0f) + ((11.0f * r02) * r5)) + ((r02 * 108.0f) * r4));
        float r52 = r5 * r62;
        float r63 = r62 * r4;
        float r83 = r82 * 460.0f;
        float r03 = (((451.0f * r52) + r83) + (288.0f * r63)) / 1403.0f;
        float r42 = ((r83 - (891.0f * r52)) - (261.0f * r63)) / 1403.0f;
        float r04 = (Math.signum(r03) * (100.0f / r18.getFl())) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(r03) * 27.13d) / (400.0d - Math.abs(r03))), 2.380952380952381d));
        float r43 = (Math.signum(r42) * (100.0f / r18.getFl())) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(r42) * 27.13d) / (400.0d - Math.abs(r42))), 2.380952380952381d));
        float r2 = (Math.signum(((r83 - (r52 * 220.0f)) - (r63 * 6300.0f)) / 1403.0f) * (100.0f / r18.getFl())) * ((float) Math.pow((float) Math.max(0.0d, (Math.abs(r8) * 27.13d) / (400.0d - Math.abs(r8))), 2.380952380952381d));
        float r05 = r04 / r18.getRgbD()[0];
        float r44 = r43 / r18.getRgbD()[1];
        float r22 = r2 / r18.getRgbD()[2];
        float[][] r13 = CamUtils.CAM16RGB_TO_XYZ;
        float[] r7 = r13[0];
        float r84 = ((r7[0] * r05) + (r7[1] * r44)) + (r7[2] * r22);
        float[] r72 = r13[1];
        float r9 = ((r72[0] * r05) + (r72[1] * r44)) + (r72[2] * r22);
        float[] r14 = r13[2];
        return ColorUtils.XYZToColor(r84, r9, ((r05 * r14[0]) + (r44 * r14[1])) + (r22 * r14[2]));
    L5:
        if (getJ() == 0.0d) goto L8;
        r0 = getChroma() / ((float) Math.sqrt(getJ() / 100.0d));
        goto L9
    }

    public int viewedInSrgb() {
        return viewed(ViewingConditions.DEFAULT);
    }
}
