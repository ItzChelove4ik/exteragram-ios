package com.google.android.material.progressindicator;

import android.animation.Animator;
import androidx.core.math.MathUtils;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import com.google.android.material.progressindicator.DrawingDelegate;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
abstract class IndeterminateAnimatorDelegate<T extends Animator> {
    protected final List<DrawingDelegate.ActiveIndicator> activeIndicators;
    protected IndeterminateDrawable drawable;

    public IndeterminateAnimatorDelegate(int r4) {
        this.activeIndicators = new ArrayList();
        int r0 = 0;
    L3:
        if (r0 >= r4) goto L5;
        this.activeIndicators.add(new DrawingDelegate.ActiveIndicator());
        r0 = r0 + 1;
        goto L3
    }

    public abstract void cancelAnimatorImmediately();

    public float getFractionInRange(int r1, int r2, int r3) {
        return MathUtils.clamp((r1 - r2) / r3, 0.0f, 1.0f);
    }

    public abstract void invalidateSpecValues();

    public abstract void registerAnimatorsCompleteCallback(Animatable2Compat$AnimationCallback r1);

    public void registerDrawable(IndeterminateDrawable r1) {
        this.drawable = r1;
    }

    public abstract void requestCancelAnimatorAfterCurrentCycle();

    public abstract void resetPropertiesForNewStart();

    public abstract void setAnimationFraction(float r1);

    public abstract void startAnimator();

    public abstract void unregisterAnimatorsCompleteCallback();
}
