package com.google.android.material.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes5.dex */
public interface VisibilityAnimatorProvider {
    Animator createAppear(ViewGroup r1, View r2);

    Animator createDisappear(ViewGroup r1, View r2);
}
