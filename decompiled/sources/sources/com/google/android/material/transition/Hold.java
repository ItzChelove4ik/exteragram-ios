package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;

/* loaded from: classes5.dex */
public final class Hold extends Visibility {
    public Hold() {
    }

    @Override // androidx.transition.Visibility
    public Animator onAppear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
        return ValueAnimator.ofFloat(new float[]{0.0f});
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
        return ValueAnimator.ofFloat(new float[]{0.0f});
    }
}
