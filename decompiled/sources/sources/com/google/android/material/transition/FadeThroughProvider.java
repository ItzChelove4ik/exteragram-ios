package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes5.dex */
public final class FadeThroughProvider implements VisibilityAnimatorProvider {
    static final float FADE_THROUGH_THRESHOLD = 0.35f;
    private float progressThreshold;

    /* renamed from: com.google.android.material.transition.FadeThroughProvider$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ float val$endFraction;
        final /* synthetic */ float val$endValue;
        final /* synthetic */ float val$startFraction;
        final /* synthetic */ float val$startValue;
        final /* synthetic */ View val$view;

        public AnonymousClass1(View r1, float r2, float r3, float r4, float r5) {
            this.val$view = r1;
            this.val$startValue = r2;
            this.val$endValue = r3;
            this.val$startFraction = r4;
            this.val$endFraction = r5;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r5) {
            float r52 = ((Float) r5.getAnimatedValue()).floatValue();
            this.val$view.setAlpha(TransitionUtils.lerp(this.val$startValue, this.val$endValue, this.val$startFraction, this.val$endFraction, r52));
        }
    }

    /* renamed from: com.google.android.material.transition.FadeThroughProvider$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ float val$originalAlpha;
        final /* synthetic */ View val$view;

        public AnonymousClass2(View r1, float r2) {
            this.val$view = r1;
            this.val$originalAlpha = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$view.setAlpha(this.val$originalAlpha);
        }
    }

    public FadeThroughProvider() {
        this.progressThreshold = FADE_THROUGH_THRESHOLD;
    }

    private static Animator createFadeThroughAnimator(View r7, float r8, float r9, float r10, float r11, float r12) {
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r0.addUpdateListener(new AnonymousClass1(r7, r8, r9, r10, r11));
        r0.addListener(new AnonymousClass2(r7, r12));
        return r0;
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup r7, View r8) {
        if (r8.getAlpha() != 0.0f) goto L6;
        float r72 = 1.0f;
    L5:
        float r2 = r72;
        return createFadeThroughAnimator(r8, 0.0f, r2, this.progressThreshold, 1.0f, r2);
    L6:
        r72 = r8.getAlpha();
        goto L5
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup r7, View r8) {
        if (r8.getAlpha() != 0.0f) goto L6;
        float r72 = 1.0f;
    L5:
        float r1 = r72;
        return createFadeThroughAnimator(r8, r1, 0.0f, 0.0f, this.progressThreshold, r1);
    L6:
        r72 = r8.getAlpha();
        goto L5
    }

    public float getProgressThreshold() {
        return this.progressThreshold;
    }

    public void setProgressThreshold(float r1) {
        this.progressThreshold = r1;
    }
}
