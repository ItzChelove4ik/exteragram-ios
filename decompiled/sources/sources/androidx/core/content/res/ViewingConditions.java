package androidx.core.content.res;

/* loaded from: classes3.dex */
final class ViewingConditions {
    static final ViewingConditions DEFAULT = null;
    private final float mAw;
    private final float mC;
    private final float mFl;
    private final float mFlRoot;
    private final float mN;
    private final float mNbb;
    private final float mNc;
    private final float mNcb;
    private final float[] mRgbD;
    private final float mZ;

    static {
        DEFAULT = make(CamUtils.WHITE_POINT_D65, (float) ((CamUtils.yFromLStar(50.0f) * 63.66197723675813d) / 100.0d), 50.0f, 2.0f, false);
    }

    private ViewingConditions(float r1, float r2, float r3, float r4, float r5, float r6, float[] r7, float r8, float r9, float r10) {
        this.mN = r1;
        this.mAw = r2;
        this.mNbb = r3;
        this.mNcb = r4;
        this.mC = r5;
        this.mNc = r6;
        this.mRgbD = r7;
        this.mFl = r8;
        this.mFlRoot = r9;
        this.mZ = r10;
    }

    public static ViewingConditions make(float[] r22, float r23, float r24, float r25, boolean r26) {
        float[][] r1 = CamUtils.XYZ_TO_CAM16RGB;
        float r3 = r22[0];
        float[] r4 = r1[0];
        float r5 = r4[0] * r3;
        float r7 = r22[1];
        float r52 = r5 + (r4[1] * r7);
        float r9 = r22[2];
        float r53 = r52 + (r4[2] * r9);
        float[] r42 = r1[1];
        float r10 = ((r42[0] * r3) + (r42[1] * r7)) + (r42[2] * r9);
        float[] r12 = r1[2];
        float r32 = ((r3 * r12[0]) + (r7 * r12[1])) + (r9 * r12[2]);
        float r43 = (r25 / 10.0f) + 0.8f;
        if (r43 < 0.9d) goto L6;
        float r13 = CamUtils.lerp(0.59f, 0.69f, (r43 - 0.9f) * 10.0f);
    L5:
        float r16 = r13;
        if (r26 == false) goto L10;
        float r72 = 1.0f;
    L11:
        double r11 = r72;
        if (r11 <= 1.0d) goto L15;
        r72 = 1.0f;
    L17:
        float[] r132 = {(((100.0f / r53) * r72) + 1.0f) - r72, (((100.0f / r10) * r72) + 1.0f) - r72, (((100.0f / r32) * r72) + 1.0f) - r72};
        float r92 = 1.0f / ((5.0f * r23) + 1.0f);
        float r112 = ((r92 * r92) * r92) * r92;
        float r14 = 1.0f - r112;
        float r113 = (r112 * r23) + (((0.1f * r14) * r14) * ((float) Math.cbrt(r23 * 5.0d)));
        float r122 = CamUtils.yFromLStar(r24) / r22[1];
        double r0 = r122;
        float r21 = ((float) Math.sqrt(r0)) + 1.48f;
        float r142 = 0.725f / ((float) Math.pow(r0, 0.2d));
        float[] r33 = {(float) Math.pow(((r132[0] * r113) * r53) / 100.0d, 0.42d), (float) Math.pow(((r132[1] * r113) * r10) / 100.0d, 0.42d), (float) Math.pow(((r132[2] * r113) * r32) / 100.0d, 0.42d)};
        float r02 = r33[0];
        float r2 = (r02 * 400.0f) / (r02 + 27.13f);
        float r03 = r33[1];
        float r6 = (r03 * 400.0f) / (r03 + 27.13f);
        float r04 = r33[2];
        float[] r05 = {r2, r6, (400.0f * r04) / (r04 + 27.13f)};
        return new ViewingConditions(r122, (((r05[0] * 2.0f) + r05[1]) + (r05[2] * 0.05f)) * r142, r142, r142, r16, r43, r132, r113, (float) Math.pow(r113, 0.25d), r21);
    L15:
        if (r11 >= 0.0d) goto L17;
        r72 = 0.0f;
        goto L17
    L10:
        r72 = (1.0f - (((float) Math.exp(((-r23) - 42.0f) / 92.0f)) * 0.2777778f)) * r43;
        goto L11
    L6:
        r13 = CamUtils.lerp(0.525f, 0.59f, (r43 - 0.8f) * 10.0f);
        goto L5
    }

    public float getAw() {
        return this.mAw;
    }

    public float getC() {
        return this.mC;
    }

    public float getFl() {
        return this.mFl;
    }

    public float getFlRoot() {
        return this.mFlRoot;
    }

    public float getN() {
        return this.mN;
    }

    public float getNbb() {
        return this.mNbb;
    }

    public float getNc() {
        return this.mNc;
    }

    public float getNcb() {
        return this.mNcb;
    }

    public float[] getRgbD() {
        return this.mRgbD;
    }

    public float getZ() {
        return this.mZ;
    }
}
