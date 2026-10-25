package com.google.android.material.internal;

import android.animation.TimeInterpolator;

/* loaded from: classes5.dex */
public class ReversableAnimatedValueInterpolator implements TimeInterpolator {
    private final TimeInterpolator sourceInterpolator;

    public ReversableAnimatedValueInterpolator(TimeInterpolator r1) {
        this.sourceInterpolator = r1;
    }

    public static TimeInterpolator of(boolean r0, TimeInterpolator r1) {
        if (r0 == false) goto L5;
        return r1;
    L5:
        return new ReversableAnimatedValueInterpolator(r1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float r1) {
        return 1.0f - this.sourceInterpolator.getInterpolation(r1);
    }
}
