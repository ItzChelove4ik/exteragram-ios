package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.transition.VisibilityAnimatorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
abstract class MaterialVisibility<P extends VisibilityAnimatorProvider> extends Visibility {
    private final List<VisibilityAnimatorProvider> additionalAnimatorProviders;
    private final P primaryAnimatorProvider;
    private VisibilityAnimatorProvider secondaryAnimatorProvider;

    public MaterialVisibility(P r2, VisibilityAnimatorProvider r3) {
        this.additionalAnimatorProviders = new ArrayList();
        this.primaryAnimatorProvider = r2;
        this.secondaryAnimatorProvider = r3;
    }

    private static void addAnimatorIfNeeded(List<Animator> r0, VisibilityAnimatorProvider r1, ViewGroup r2, View r3, boolean r4) {
        if (r1 == null) goto L11;
        if (r4 == false) goto L6;
        Animator r12 = r1.createAppear(r2, r3);
    L7:
        if (r12 == null) goto L10;
        r0.add(r12);
        return;
    L10:
        return;
    L6:
        r12 = r1.createDisappear(r2, r3);
        goto L7
    }

    private Animator createAnimator(ViewGroup r5, View r6, boolean r7) {
        AnimatorSet r0 = new AnimatorSet();
        ArrayList r1 = new ArrayList();
        addAnimatorIfNeeded(r1, this.primaryAnimatorProvider, r5, r6, r7);
        addAnimatorIfNeeded(r1, this.secondaryAnimatorProvider, r5, r6, r7);
        Iterator<VisibilityAnimatorProvider> r2 = this.additionalAnimatorProviders.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        addAnimatorIfNeeded(r1, r2.next(), r5, r6, r7);
        goto L4
    L6:
        maybeApplyThemeValues(r5.getContext(), r7);
        AnimatorSetCompat.playTogether(r0, r1);
        return r0;
    }

    private void maybeApplyThemeValues(Context r2, boolean r3) {
        TransitionUtils.maybeApplyThemeDuration(this, r2, getDurationThemeAttrResId(r3));
        TransitionUtils.maybeApplyThemeInterpolator(this, r2, getEasingThemeAttrResId(r3), getDefaultEasingInterpolator(r3));
    }

    public void addAdditionalAnimatorProvider(VisibilityAnimatorProvider r1) {
        this.additionalAnimatorProviders.add(r1);
    }

    public void clearAdditionalAnimatorProvider() {
        this.additionalAnimatorProviders.clear();
    }

    public TimeInterpolator getDefaultEasingInterpolator(boolean r1) {
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    public int getDurationThemeAttrResId(boolean r1) {
        return 0;
    }

    public int getEasingThemeAttrResId(boolean r1) {
        return 0;
    }

    public P getPrimaryAnimatorProvider() {
        return this.primaryAnimatorProvider;
    }

    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return this.secondaryAnimatorProvider;
    }

    @Override // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }

    @Override // androidx.transition.Visibility
    public Animator onAppear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
        return createAnimator(r1, r2, true);
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup r1, View r2, TransitionValues r3, TransitionValues r4) {
        return createAnimator(r1, r2, false);
    }

    public boolean removeAdditionalAnimatorProvider(VisibilityAnimatorProvider r1) {
        return this.additionalAnimatorProviders.remove(r1);
    }

    public void setSecondaryAnimatorProvider(VisibilityAnimatorProvider r1) {
        this.secondaryAnimatorProvider = r1;
    }
}
