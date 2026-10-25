package androidx.palette.graphics;

/* loaded from: classes4.dex */
public final class Target {
    public static final Target DARK_MUTED = null;
    public static final Target DARK_VIBRANT = null;
    public static final Target LIGHT_MUTED = null;
    public static final Target LIGHT_VIBRANT = null;
    public static final Target MUTED = null;
    public static final Target VIBRANT = null;
    boolean mIsExclusive;
    final float[] mLightnessTargets;
    final float[] mSaturationTargets;
    final float[] mWeights;

    static {
        Target r0 = new Target();
        LIGHT_VIBRANT = r0;
        setDefaultLightLightnessValues(r0);
        setDefaultVibrantSaturationValues(r0);
        Target r02 = new Target();
        VIBRANT = r02;
        setDefaultNormalLightnessValues(r02);
        setDefaultVibrantSaturationValues(r02);
        Target r03 = new Target();
        DARK_VIBRANT = r03;
        setDefaultDarkLightnessValues(r03);
        setDefaultVibrantSaturationValues(r03);
        Target r04 = new Target();
        LIGHT_MUTED = r04;
        setDefaultLightLightnessValues(r04);
        setDefaultMutedSaturationValues(r04);
        Target r05 = new Target();
        MUTED = r05;
        setDefaultNormalLightnessValues(r05);
        setDefaultMutedSaturationValues(r05);
        Target r06 = new Target();
        DARK_MUTED = r06;
        setDefaultDarkLightnessValues(r06);
        setDefaultMutedSaturationValues(r06);
    }

    public Target() {
        float[] r1 = new float[3];
        this.mSaturationTargets = r1;
        float[] r2 = new float[3];
        this.mLightnessTargets = r2;
        this.mWeights = new float[3];
        this.mIsExclusive = true;
        setTargetDefaultValues(r1);
        setTargetDefaultValues(r2);
        setDefaultWeights();
    }

    private static void setDefaultDarkLightnessValues(Target r2) {
        float[] r22 = r2.mLightnessTargets;
        r22[1] = 0.26f;
        r22[2] = 0.45f;
    }

    private static void setDefaultLightLightnessValues(Target r2) {
        float[] r22 = r2.mLightnessTargets;
        r22[0] = 0.55f;
        r22[1] = 0.74f;
    }

    private static void setDefaultMutedSaturationValues(Target r2) {
        float[] r22 = r2.mSaturationTargets;
        r22[1] = 0.3f;
        r22[2] = 0.4f;
    }

    private static void setDefaultNormalLightnessValues(Target r2) {
        float[] r22 = r2.mLightnessTargets;
        r22[0] = 0.3f;
        r22[1] = 0.5f;
        r22[2] = 0.7f;
    }

    private static void setDefaultVibrantSaturationValues(Target r2) {
        float[] r22 = r2.mSaturationTargets;
        r22[0] = 0.35f;
        r22[1] = 1.0f;
    }

    private void setDefaultWeights() {
        float[] r3 = this.mWeights;
        r3[0] = 0.24f;
        r3[1] = 0.52f;
        r3[2] = 0.24f;
    }

    private static void setTargetDefaultValues(float[] r2) {
        r2[0] = 0.0f;
        r2[1] = 0.5f;
        r2[2] = 1.0f;
    }

    public float getLightnessWeight() {
        return this.mWeights[1];
    }

    public float getMaximumLightness() {
        return this.mLightnessTargets[2];
    }

    public float getMaximumSaturation() {
        return this.mSaturationTargets[2];
    }

    public float getMinimumLightness() {
        return this.mLightnessTargets[0];
    }

    public float getMinimumSaturation() {
        return this.mSaturationTargets[0];
    }

    public float getPopulationWeight() {
        return this.mWeights[2];
    }

    public float getSaturationWeight() {
        return this.mWeights[0];
    }

    public float getTargetLightness() {
        return this.mLightnessTargets[1];
    }

    public float getTargetSaturation() {
        return this.mSaturationTargets[1];
    }

    public boolean isExclusive() {
        return this.mIsExclusive;
    }

    public void normalizeWeights() {
        int r0 = this.mWeights.length;
        int r2 = 0;
        float r4 = 0.0f;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L9;
        float r5 = this.mWeights[r3];
        if (r5 <= 0.0f) goto L7;
        r4 = r4 + r5;
    L7:
        r3 = r3 + 1;
        goto L3
    L9:
        if (r4 == 0.0f) goto L16;
        int r02 = this.mWeights.length;
    L11:
        if (r2 >= r02) goto L23;
        float[] r32 = this.mWeights;
        float r52 = r32[r2];
        if (r52 <= 0.0f) goto L15;
        r32[r2] = r52 / r4;
    L15:
        r2 = r2 + 1;
        goto L11
    L23:
        return;
    }
}
