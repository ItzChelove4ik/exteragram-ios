package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.List;

/* loaded from: classes5.dex */
interface MotionStrategy {
    void addAnimationListener(Animator.AnimatorListener r1);

    AnimatorSet createAnimator();

    MotionSpec getCurrentMotionSpec();

    int getDefaultMotionSpecResource();

    List<Animator.AnimatorListener> getListeners();

    MotionSpec getMotionSpec();

    void onAnimationCancel();

    void onAnimationEnd();

    void onAnimationStart(Animator r1);

    void onChange(ExtendedFloatingActionButton.OnChangedCallback r1);

    void performNow();

    void removeAnimationListener(Animator.AnimatorListener r1);

    void setMotionSpec(MotionSpec r1);

    boolean shouldCancel();
}
