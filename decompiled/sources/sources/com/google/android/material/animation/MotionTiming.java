package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

/* loaded from: classes5.dex */
public class MotionTiming {
    private long delay;
    private long duration;
    private TimeInterpolator interpolator;
    private int repeatCount;
    private int repeatMode;

    public MotionTiming(long r2, long r4) {
        this.interpolator = null;
        this.repeatCount = 0;
        this.repeatMode = 1;
        this.delay = r2;
        this.duration = r4;
    }

    public MotionTiming(long r2, long r4, TimeInterpolator r6) {
        this.repeatCount = 0;
        this.repeatMode = 1;
        this.delay = r2;
        this.duration = r4;
        this.interpolator = r6;
    }

    public static MotionTiming createFromAnimator(ValueAnimator r6) {
        MotionTiming r0 = new MotionTiming(r6.getStartDelay(), r6.getDuration(), r6.getInterpolator());
        r0.repeatCount = r6.getRepeatCount();
        r0.repeatMode = r6.getRepeatMode();
        return r0;
    }

    public void apply(Animator r3) {
        r3.setStartDelay(getDelay());
        r3.setDuration(getDuration());
        r3.setInterpolator(getInterpolator());
        if ((r3 instanceof ValueAnimator) == false) goto L6;
        ValueAnimator r32 = (ValueAnimator) r3;
        r32.setRepeatCount(getRepeatCount());
        r32.setRepeatMode(getRepeatMode());
        return;
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if ((r7 instanceof MotionTiming) == true) goto L8;
        return false;
    L8:
        MotionTiming r72 = (MotionTiming) r7;
        if (getDelay() == r72.getDelay()) goto L12;
        return false;
    L12:
        if (getDuration() == r72.getDuration()) goto L15;
        return false;
    L15:
        if (getRepeatCount() == r72.getRepeatCount()) goto L18;
        return false;
    L18:
        if (getRepeatMode() == r72.getRepeatMode()) goto L21;
        return false;
    L21:
        return getInterpolator().getClass().equals(r72.getInterpolator().getClass());
    }

    public long getDelay() {
        return this.delay;
    }

    public long getDuration() {
        return this.duration;
    }

    public TimeInterpolator getInterpolator() {
        TimeInterpolator r0 = this.interpolator;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public int getRepeatCount() {
        return this.repeatCount;
    }

    public int getRepeatMode() {
        return this.repeatMode;
    }

    public int hashCode() {
        return (((((((((int) (getDelay() ^ (getDelay() >>> 32))) * 31) + ((int) (getDuration() ^ (getDuration() >>> 32)))) * 31) + getInterpolator().getClass().hashCode()) * 31) + getRepeatCount()) * 31) + getRepeatMode();
    }

    public String toString() {
        return "\n" + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + getDelay() + " duration: " + getDuration() + " interpolator: " + getInterpolator().getClass() + " repeatCount: " + getRepeatCount() + " repeatMode: " + getRepeatMode() + "}\n";
    }
}
