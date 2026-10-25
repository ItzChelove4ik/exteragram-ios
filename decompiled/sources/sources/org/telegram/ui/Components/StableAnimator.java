package org.telegram.ui.Components;

import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class StableAnimator extends TimeAnimator {
    private Object animatedValue;
    private float[] floatValues;
    private int times;
    private int totalTimes;
    private ValueAnimator.AnimatorUpdateListener updateListener;

    /* renamed from: $r8$lambda$7Vq-i5d0lvpwPvAZGbnQvZM25gM, reason: not valid java name */
    public static /* synthetic */ void m13043$r8$lambda$7Vqi5d0lvpwPvAZGbnQvZM25gM(StableAnimator r0, TimeAnimator r1, long r2, long r4) {
        r0.lambda$start$0(r1, r2, r4);
    }

    public StableAnimator() {
        this.times = 0;
        this.totalTimes = 0;
    }

    private /* synthetic */ void lambda$start$0(TimeAnimator r1, long r2, long r4) {
        int r12 = this.times;
        if (r12 <= 0) goto L16;
        int r22 = this.totalTimes;
        if (r22 <= 0) goto L16;
        int r13 = r12 - 1;
        this.times = r13;
        if (this.updateListener == null) goto L18;
        float[] r42 = this.floatValues;
        if (r42 != null) goto L11;
    L14:
        end();
        return;
    L11:
        if (r42.length != 2) goto L14;
        float r14 = getInterpolator().getInterpolation(1.0f - (r13 / r22));
        float[] r23 = this.floatValues;
        float r43 = r23[0];
        this.animatedValue = Float.valueOf(r43 + ((r23[1] - r43) * r14));
        this.updateListener.onAnimationUpdate(this);
        return;
    L18:
        return;
    L16:
        end();
    }

    public static StableAnimator ofFloat(float... r1) {
        StableAnimator r0 = new StableAnimator();
        r0.setFloatValues(r1);
        return r0;
    }

    @Override // android.animation.ValueAnimator
    public void addUpdateListener(ValueAnimator.AnimatorUpdateListener r1) {
        this.updateListener = r1;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void end() {
        this.updateListener = null;
        super.end();
    }

    @Override // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return this.animatedValue;
    }

    @Override // android.animation.ValueAnimator
    public void setFloatValues(float[] r1) {
        super.setFloatValues(r1);
        this.floatValues = r1;
    }

    @Override // android.animation.TimeAnimator, android.animation.ValueAnimator, android.animation.Animator
    public void start() {
        setTimeListener(new StableAnimator$$ExternalSyntheticLambda0(this));
        int r0 = (int) (getDuration() / AndroidUtilities.screenRefreshTime);
        this.times = r0;
        this.totalTimes = r0;
        super.start();
    }
}
