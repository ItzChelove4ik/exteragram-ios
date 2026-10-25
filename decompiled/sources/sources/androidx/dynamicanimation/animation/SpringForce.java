package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;
import c.f$$ExternalSyntheticBUOutline1;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class SpringForce {
    private double mDampedFreq;
    double mDampingRatio;
    private double mFinalPosition;
    private double mGammaMinus;
    private double mGammaPlus;
    private boolean mInitialized;
    private final DynamicAnimation.MassState mMassState;
    double mNaturalFreq;
    private double mValueThreshold;
    private double mVelocityThreshold;

    public SpringForce() {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = Double.MAX_VALUE;
        this.mMassState = new DynamicAnimation.MassState();
    }

    public SpringForce(float r3) {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = Double.MAX_VALUE;
        this.mMassState = new DynamicAnimation.MassState();
        this.mFinalPosition = r3;
    }

    private void init() {
        if (this.mInitialized == false) goto L6;
        return;
    L6:
        if (this.mFinalPosition == Double.MAX_VALUE) goto L17;
        double r0 = this.mDampingRatio;
        if (r0 <= 1.0d) goto L11;
        double r6 = this.mNaturalFreq;
        this.mGammaPlus = ((-r0) * r6) + (r6 * Math.sqrt((r0 * r0) - 1.0d));
        double r02 = this.mDampingRatio;
        double r62 = this.mNaturalFreq;
        this.mGammaMinus = ((-r02) * r62) - (r62 * Math.sqrt((r02 * r02) - 1.0d));
    L15:
        this.mInitialized = true;
        return;
    L11:
        if (r0 < 0.0d) goto L15;
        if (r0 >= 1.0d) goto L15;
        this.mDampedFreq = this.mNaturalFreq * Math.sqrt(1.0d - (r0 * r0));
        goto L15
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("Error: Final position of the spring must be set before the animation starts");
    }

    public float getDampingRatio() {
        return (float) this.mDampingRatio;
    }

    public float getFinalPosition() {
        return (float) this.mFinalPosition;
    }

    public float getStiffness() {
        double r0 = this.mNaturalFreq;
        return (float) (r0 * r0);
    }

    public boolean isAtEquilibrium(float r5, float r6) {
        if (Math.abs(r6) < this.mVelocityThreshold) goto L5;
        return false;
    L5:
        if (Math.abs(r5 - getFinalPosition()) >= this.mValueThreshold) goto L10;
        return true;
    L10:
        return false;
    }

    public SpringForce setDampingRatio(float r3) {
        if (r3 < 0.0f) goto L6;
        this.mDampingRatio = r3;
        this.mInitialized = false;
        return this;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Damping ratio must be non-negative");
        return null;
    }

    public SpringForce setFinalPosition(float r3) {
        this.mFinalPosition = r3;
        return this;
    }

    public SpringForce setStiffness(float r3) {
        if (r3 <= 0.0f) goto L6;
        this.mNaturalFreq = Math.sqrt(r3);
        this.mInitialized = false;
        return this;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Spring stiffness constant must be positive.");
        return null;
    }

    public void setValueThreshold(double r3) {
        double r32 = Math.abs(r3);
        this.mValueThreshold = r32;
        this.mVelocityThreshold = r32 * 62.5d;
    }

    public DynamicAnimation.MassState updateValues(double r17, double r19, long r21) {
        init();
        double r1 = r21 / 1000.0d;
        double r3 = r17 - this.mFinalPosition;
        double r5 = this.mDampingRatio;
        if (r5 <= 1.0d) goto L6;
        double r52 = this.mGammaMinus;
        double r12 = this.mGammaPlus;
        double r7 = r3 - (((r52 * r3) - r19) / (r52 - r12));
        double r32 = ((r3 * r52) - r19) / (r52 - r12);
        double r53 = (Math.pow(2.718281828459045d, r52 * r1) * r7) + (Math.pow(2.718281828459045d, this.mGammaPlus * r1) * r32);
        double r122 = this.mGammaMinus;
        double r72 = (r7 * r122) * Math.pow(2.718281828459045d, r122 * r1);
        double r123 = this.mGammaPlus;
        double r73 = r72 + ((r32 * r123) * Math.pow(2.718281828459045d, r123 * r1));
    L9:
        DynamicAnimation.MassState r13 = this.mMassState;
        r13.mValue = (float) (r53 + this.mFinalPosition);
        r13.mVelocity = (float) r73;
        return r13;
    L6:
        if (r5 != 1.0d) goto L8;
        double r54 = this.mNaturalFreq;
        double r74 = r19 + (r54 * r3);
        double r33 = r3 + (r74 * r1);
        r53 = Math.pow(2.718281828459045d, (-r54) * r1) * r33;
        double r34 = r33 * Math.pow(2.718281828459045d, (-this.mNaturalFreq) * r1);
        double r124 = this.mNaturalFreq;
        r73 = (r74 * Math.pow(2.718281828459045d, (-r124) * r1)) + (r34 * (-r124));
        goto L9
    L8:
        double r75 = 1.0d / this.mDampedFreq;
        double r125 = this.mNaturalFreq;
        double r76 = r75 * (((r5 * r125) * r3) + r19);
        r53 = Math.pow(2.718281828459045d, ((-r5) * r125) * r1) * ((Math.cos(this.mDampedFreq * r1) * r3) + (Math.sin(this.mDampedFreq * r1) * r76));
        double r126 = this.mNaturalFreq;
        double r10 = this.mDampingRatio;
        double r9 = Math.pow(2.718281828459045d, ((-r10) * r126) * r1);
        double r11 = this.mDampedFreq;
        double r14 = ((-r11) * r3) * Math.sin(r11 * r1);
        double r35 = this.mDampedFreq;
        r73 = (((-r126) * r53) * r10) + (r9 * (r14 + ((r76 * r35) * Math.cos(r35 * r1))));
        goto L9
    }
}
