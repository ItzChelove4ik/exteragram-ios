package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;

/* loaded from: classes5.dex */
public class FadeThroughUpdateListener implements ValueAnimator.AnimatorUpdateListener {
    private final float[] alphas;
    private final View fadeInView;
    private final View fadeOutView;

    public FadeThroughUpdateListener(View r1, View r2) {
        this.fadeOutView = r1;
        this.fadeInView = r2;
        this.alphas = new float[2];
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator r3) {
        FadeThroughUtils.calculateFadeOutAndInAlphas(((Float) r3.getAnimatedValue()).floatValue(), this.alphas);
        View r32 = this.fadeOutView;
        if (r32 == null) goto L5;
        r32.setAlpha(this.alphas[0]);
    L5:
        View r33 = this.fadeInView;
        if (r33 == null) goto L9;
        r33.setAlpha(this.alphas[1]);
        return;
    }
}
