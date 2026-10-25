package org.telegram.ui.Components;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* loaded from: classes7.dex */
public class Scroller {
    private static float DECELERATION_RATE;
    private static float END_TENSION;
    private static final float[] SPLINE = null;
    private static float START_TENSION;
    private static float sViscousFluidNormalize;
    private static float sViscousFluidScale;
    private int mCurrX;
    private int mCurrY;
    private float mDeceleration;
    private float mDeltaX;
    private float mDeltaY;
    private int mDuration;
    private float mDurationReciprocal;
    private int mFinalX;
    private int mFinalY;
    private boolean mFinished;
    private boolean mFlywheel;
    private Interpolator mInterpolator;
    private int mMaxX;
    private int mMaxY;
    private int mMinX;
    private int mMinY;
    private int mMode;
    private final float mPpi;
    private long mStartTime;
    private int mStartX;
    private int mStartY;
    private float mVelocity;

    static {
        DECELERATION_RATE = (float) (Math.log(0.75d) / Math.log(0.9d));
        START_TENSION = 0.4f;
        END_TENSION = 1.0f - 0.4f;
        SPLINE = new float[101];
        float r0 = 0.0f;
        int r2 = 0;
    L4:
        if (r2 > 100) goto L13;
        float r4 = r2 / 100.0f;
        float r3 = 1.0f;
    L6:
        float r5 = ((r3 - r0) / 2.0f) + r0;
        float r7 = 1.0f - r5;
        float r6 = (3.0f * r5) * r7;
        float r8 = (r5 * r5) * r5;
        float r72 = (((r7 * START_TENSION) + (END_TENSION * r5)) * r6) + r8;
        if (Math.abs(r72 - r4) < 1.0E-5d) goto L8;
        if (r72 > r4) goto L11;
        r0 = r5;
        goto L6
    L11:
        r3 = r5;
        goto L6
    L8:
        SPLINE[r2] = r6 + r8;
        r2 = r2 + 1;
        goto L4
    L13:
        SPLINE[100] = 1.0f;
        sViscousFluidScale = 8.0f;
        sViscousFluidNormalize = 1.0f;
        sViscousFluidNormalize = 1.0f / viscousFluid(1.0f);
    }

    public Scroller(Context r2) {
        this(r2, null);
    }

    public Scroller(Context r2, Interpolator r3) {
        this(r2, r3, true);
    }

    public Scroller(Context r2, Interpolator r3, boolean r4) {
        this.mFinished = true;
        this.mInterpolator = r3;
        this.mPpi = r2.getResources().getDisplayMetrics().density * 160.0f;
        this.mDeceleration = computeDeceleration(ViewConfiguration.getScrollFriction());
        this.mFlywheel = r4;
    }

    private float computeDeceleration(float r2) {
        return (this.mPpi * 386.0878f) * r2;
    }

    public static float viscousFluid(float r3) {
        float r32 = r3 * sViscousFluidScale;
        if (r32 >= 1.0f) goto L5;
        float r33 = r32 - (1.0f - ((float) Math.exp(-r32)));
    L7:
        return r33 * sViscousFluidNormalize;
    L5:
        r33 = 0.36787945f + ((1.0f - ((float) Math.exp(1.0f - r32))) * 0.63212055f);
        goto L7
    }

    public void abortAnimation() {
        this.mCurrX = this.mFinalX;
        this.mCurrY = this.mFinalY;
        this.mFinished = true;
    }

    public boolean computeScrollOffset() {
        if (this.mFinished == false) goto L6;
        return false;
    L6:
        int r0 = (int) (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
        int r1 = this.mDuration;
        if (r0 >= r1) goto L22;
        int r3 = this.mMode;
        if (r3 == 0) goto L17;
        if (r3 != 1) goto L23;
        float r02 = r0 / r1;
        int r32 = (int) (r02 * 100.0f);
        float r4 = r32 / 100.0f;
        int r5 = r32 + 1;
        float[] r12 = SPLINE;
        float r33 = r12[r32];
        float r34 = r33 + (((r02 - r4) / ((r5 / 100.0f) - r4)) * (r12[r5] - r33));
        int r03 = this.mStartX + Math.round((this.mFinalX - r0) * r34);
        this.mCurrX = r03;
        int r04 = Math.min(r03, this.mMaxX);
        this.mCurrX = r04;
        this.mCurrX = Math.max(r04, this.mMinX);
        int r05 = this.mStartY + Math.round(r34 * (this.mFinalY - r0));
        this.mCurrY = r05;
        int r06 = Math.min(r05, this.mMaxY);
        this.mCurrY = r06;
        int r07 = Math.max(r06, this.mMinY);
        this.mCurrY = r07;
        if (this.mCurrX != this.mFinalX) goto L23;
        if (r07 != this.mFinalY) goto L23;
        this.mFinished = true;
    L23:
        return true;
    L17:
        float r08 = r0 * this.mDurationReciprocal;
        Interpolator r13 = this.mInterpolator;
        if (r13 != null) goto L20;
        float r09 = viscousFluid(r08);
    L21:
        this.mCurrX = this.mStartX + Math.round(this.mDeltaX * r09);
        this.mCurrY = this.mStartY + Math.round(r09 * this.mDeltaY);
        goto L23
    L20:
        r09 = r13.getInterpolation(r08);
        goto L21
    L22:
        this.mCurrX = this.mFinalX;
        this.mCurrY = this.mFinalY;
        this.mFinished = true;
        goto L23
    }

    public void fling(int r12, int r13, int r14, int r15, int r16, int r17, int r18, int r19) {
        if (this.mFlywheel == true) goto L5;
    L11:
        int r0 = r14;
        int r1 = r15;
    L12:
        this.mMode = 1;
        this.mFinished = false;
        float r2 = (float) Math.sqrt((r0 * r0) + (r1 * r1));
        this.mVelocity = r2;
        double r3 = Math.log((START_TENSION * r2) / 800.0f);
        this.mDuration = (int) (Math.exp(r3 / (DECELERATION_RATE - 1.0d)) * 1000.0d);
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStartX = r12;
        this.mStartY = r13;
        float r6 = 1.0f;
        if (r2 != 0.0f) goto L15;
        float r02 = 1.0f;
    L16:
        if (r2 == 0.0f) goto L19;
        r6 = r1 / r2;
    L19:
        float r110 = DECELERATION_RATE;
        int r111 = (int) (800.0d * Math.exp((r110 / (r110 - 1.0d)) * r3));
        this.mMinX = r16;
        this.mMaxX = r17;
        this.mMinY = r18;
        this.mMaxY = r19;
        float r112 = r111;
        int r122 = r12 + Math.round(r02 * r112);
        this.mFinalX = r122;
        int r123 = Math.min(r122, this.mMaxX);
        this.mFinalX = r123;
        this.mFinalX = Math.max(r123, this.mMinX);
        int r132 = r13 + Math.round(r112 * r6);
        this.mFinalY = r132;
        int r124 = Math.min(r132, this.mMaxY);
        this.mFinalY = r124;
        this.mFinalY = Math.max(r124, this.mMinY);
        return;
    L15:
        r02 = r0 / r2;
        goto L16
    L5:
        if (this.mFinished == true) goto L11;
        float r03 = getCurrVelocity();
        float r113 = this.mFinalX - this.mStartX;
        float r22 = this.mFinalY - this.mStartY;
        float r32 = (float) Math.sqrt((r113 * r113) + (r22 * r22));
        float r114 = (r113 / r32) * r03;
        float r23 = (r22 / r32) * r03;
        float r33 = r14;
        if (Math.signum(r33) != Math.signum(r114)) goto L11;
        float r5 = r15;
        if (Math.signum(r5) != Math.signum(r23)) goto L11;
        r0 = (int) (r33 + r114);
        r1 = (int) (r5 + r23);
        goto L12
    }

    public final void forceFinished(boolean r1) {
        this.mFinished = r1;
    }

    public float getCurrVelocity() {
        return this.mVelocity - ((this.mDeceleration * timePassed()) / 2000.0f);
    }

    public final int getCurrX() {
        return this.mCurrX;
    }

    public final int getCurrY() {
        return this.mCurrY;
    }

    public final int getFinalY() {
        return this.mFinalY;
    }

    public final int getStartX() {
        return this.mStartX;
    }

    public final int getStartY() {
        return this.mStartY;
    }

    public final boolean isFinished() {
        return this.mFinished;
    }

    public void startScroll(int r3, int r4, int r5, int r6, int r7) {
        this.mMode = 0;
        this.mFinished = false;
        this.mDuration = r7;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStartX = r3;
        this.mStartY = r4;
        this.mFinalX = r3 + r5;
        this.mFinalY = r4 + r6;
        this.mDeltaX = r5;
        this.mDeltaY = r6;
        this.mDurationReciprocal = 1.0f / this.mDuration;
    }

    public int timePassed() {
        return (int) (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
    }
}
