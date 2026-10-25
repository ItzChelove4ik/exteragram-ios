package androidx.dynamicanimation.animation;

import android.util.AndroidRuntimeException;
import androidx.dynamicanimation.animation.DynamicAnimation;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class SpringAnimation extends DynamicAnimation<SpringAnimation> {
    private boolean mEndRequested;
    private float mPendingPosition;
    private SpringForce mSpring;

    public SpringAnimation(FloatValueHolder r1) {
        super(r1);
        this.mSpring = null;
        this.mPendingPosition = Float.MAX_VALUE;
        this.mEndRequested = false;
    }

    public <K> SpringAnimation(K r1, FloatPropertyCompat<K> r2) {
        super(r1, r2);
        this.mSpring = null;
        this.mPendingPosition = Float.MAX_VALUE;
        this.mEndRequested = false;
    }

    public <K> SpringAnimation(K r1, FloatPropertyCompat<K> r2, float r3) {
        super(r1, r2);
        this.mSpring = null;
        this.mPendingPosition = Float.MAX_VALUE;
        this.mEndRequested = false;
        this.mSpring = new SpringForce(r3);
    }

    private void sanityCheck() {
        SpringForce r0 = this.mSpring;
        if (r0 == null) goto L13;
        double r02 = r0.getFinalPosition();
        if (r02 <= this.mMaxValue) goto L7;
        ByteString$$ExternalSyntheticBUOutline0.m("Final position of the spring cannot be greater than the max value.");
        return;
    L7:
        if (r02 < this.mMinValue) goto L9;
        return;
    L9:
        ByteString$$ExternalSyntheticBUOutline0.m("Final position of the spring cannot be less than the min value.");
        return;
    L13:
        ByteString$$ExternalSyntheticBUOutline0.m("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
    }

    public void animateToFinalPosition(float r2) {
        if (isRunning() == false) goto L7;
        this.mPendingPosition = r2;
        return;
    L7:
        if (this.mSpring != null) goto L9;
        this.mSpring = new SpringForce(r2);
    L9:
        this.mSpring.setFinalPosition(r2);
        start();
    }

    public boolean canSkipToEnd() {
        if (this.mSpring.mDampingRatio <= 0.0d) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void cancel() {
        super.cancel();
        float r0 = this.mPendingPosition;
        if (r0 == Float.MAX_VALUE) goto L10;
        SpringForce r2 = this.mSpring;
        if (r2 != null) goto L7;
        this.mSpring = new SpringForce(r0);
    L8:
        this.mPendingPosition = Float.MAX_VALUE;
        return;
    L7:
        r2.setFinalPosition(r0);
        goto L8
    }

    public SpringForce getSpring() {
        return this.mSpring;
    }

    public boolean isAtEquilibrium(float r1, float r2) {
        return this.mSpring.isAtEquilibrium(r1, r2);
    }

    public SpringAnimation setSpring(SpringForce r1) {
        this.mSpring = r1;
        return this;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void setValueThreshold(float r1) {
    }

    public void skipToEnd() {
        if (canSkipToEnd() == true) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("Spring animations can only come to an end when there is damping");
        return;
    L5:
        if (getAnimationHandler().isCurrentThread() == false) goto L11;
        if (this.mRunning == false) goto L14;
        this.mEndRequested = true;
        return;
    L14:
        return;
    L11:
        throw new AndroidRuntimeException("Animations may only be started on the same thread as the animation handler");
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void start() {
        sanityCheck();
        this.mSpring.setValueThreshold(getValueThreshold());
        super.start();
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public boolean updateValueAndVelocity(long r20) {
        boolean r1 = this.mEndRequested;
        float r2 = this.mPendingPosition;
        if (r1 == true) goto L5;
        SpringForce r7 = this.mSpring;
        if (r2 == Float.MAX_VALUE) goto L12;
        long r17 = r20 / 2;
        DynamicAnimation.MassState r12 = r7.updateValues(this.mValue, this.mVelocity, r17);
        this.mSpring.setFinalPosition(this.mPendingPosition);
        this.mPendingPosition = Float.MAX_VALUE;
        DynamicAnimation.MassState r13 = this.mSpring.updateValues(r12.mValue, r12.mVelocity, r17);
        this.mValue = r13.mValue;
        this.mVelocity = r13.mVelocity;
    L13:
        float r14 = Math.max(this.mValue, this.mMinValue);
        this.mValue = r14;
        float r15 = Math.min(r14, this.mMaxValue);
        this.mValue = r15;
        if (isAtEquilibrium(r15, this.mVelocity) == false) goto L17;
        this.mValue = this.mSpring.getFinalPosition();
        this.mVelocity = 0.0f;
        return true;
    L17:
        return false;
    L12:
        DynamicAnimation.MassState r16 = r7.updateValues(this.mValue, this.mVelocity, r20);
        this.mValue = r16.mValue;
        this.mVelocity = r16.mVelocity;
        goto L13
    L5:
        if (r2 == Float.MAX_VALUE) goto L7;
        this.mSpring.setFinalPosition(r2);
        this.mPendingPosition = Float.MAX_VALUE;
    L7:
        this.mValue = this.mSpring.getFinalPosition();
        this.mVelocity = 0.0f;
        this.mEndRequested = false;
        return true;
    }
}
