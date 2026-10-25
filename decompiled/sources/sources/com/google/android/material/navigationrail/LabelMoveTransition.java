package com.google.android.material.navigationrail;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;

/* loaded from: classes5.dex */
class LabelMoveTransition extends Transition {
    private static final float HORIZONTAL_DISTANCE = -30.0f;
    private static final String LABEL_VISIBILITY = "NavigationRailLabelVisibility";

    /* renamed from: $r8$lambda$r-iQB7b8uku2O5hKLKJf8RXzmZM, reason: not valid java name */
    public static /* synthetic */ void m1886$r8$lambda$riQB7b8uku2O5hKLKJf8RXzmZM(View r1, ValueAnimator r2) {
        r1.setTranslationX((1.0f - r2.getAnimatedFraction()) * HORIZONTAL_DISTANCE);
    }

    public LabelMoveTransition() {
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues r2) {
        r2.values.put(LABEL_VISIBILITY, Integer.valueOf(r2.view.getVisibility()));
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues r2) {
        r2.values.put(LABEL_VISIBILITY, Integer.valueOf(r2.view.getVisibility()));
    }

    @Override // androidx.transition.Transition
    public Animator createAnimator(ViewGroup r4, TransitionValues r5, TransitionValues r6) {
        if (r5 == null) goto L19;
        if (r6 == null) goto L19;
        if (r5.values.get(LABEL_VISIBILITY) == null) goto L19;
        if (r6.values.get(LABEL_VISIBILITY) == null) goto L19;
        if (((Integer) r5.values.get(LABEL_VISIBILITY)).intValue() != 8) goto L17;
        if (((Integer) r6.values.get(LABEL_VISIBILITY)).intValue() != 0) goto L17;
        View r3 = r6.view;
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r42.addUpdateListener(new LabelMoveTransition$$ExternalSyntheticLambda0(r3));
        return r42;
    L17:
        return super.createAnimator(r4, r5, r6);
    L19:
        return super.createAnimator(r4, r5, r6);
    }
}
