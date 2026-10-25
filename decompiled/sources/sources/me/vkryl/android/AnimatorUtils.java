package me.vkryl.android;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class AnimatorUtils {
    public static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE_INTERPOLATOR = null;
    public static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = null;
    public static final AnticipateOvershootInterpolator ANTICIPATE_OVERSHOOT_INTERPOLATOR = null;
    public static final DecelerateInterpolator DECELERATE_INTERPOLATOR = null;
    public static final LinearInterpolator LINEAR_INTERPOLATOR = null;
    public static final DecelerateInterpolator NAVIGATION_INTERPOLATOR = null;
    public static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = null;
    public static final Interpolator QUADRATIC_EASE_IN_OUT_INTERPOLATOR = null;
    public static final Interpolator QUADRATIC_OUT_INTERPOLATOR = null;

    /* renamed from: me.vkryl.android.AnimatorUtils$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements View.OnLayoutChangeListener {
        final /* synthetic */ Animator val$animator;
        final /* synthetic */ View val$view;

        public AnonymousClass1(View r1, Animator r2) {
            this.val$view = r1;
            this.val$animator = r2;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            this.val$view.removeOnLayoutChangeListener(this);
            this.val$animator.start();
        }
    }

    public static /* synthetic */ float $r8$lambda$JI7rc6j6ef3U4gcSi5NiklRZPkY(float r1) {
        float r12 = 1.0f - r1;
        return 1.0f - (r12 * r12);
    }

    public static /* synthetic */ float $r8$lambda$U8unIFNBgpUICoxvqzSjoUxGuKM(float r2) {
        if (r2 >= 0.5f) goto L7;
        return (2.0f * r2) * r2;
    L7:
        return ((4.0f - (2.0f * r2)) * r2) - 1.0f;
    }

    static {
        ANTICIPATE_OVERSHOOT_INTERPOLATOR = new AnticipateOvershootInterpolator();
        DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
        ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
        NAVIGATION_INTERPOLATOR = new DecelerateInterpolator(1.78f);
        LINEAR_INTERPOLATOR = new LinearInterpolator();
        OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(3.2f);
        ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
        QUADRATIC_EASE_IN_OUT_INTERPOLATOR = new AnimatorUtils$$ExternalSyntheticLambda0();
        QUADRATIC_OUT_INTERPOLATOR = new AnimatorUtils$$ExternalSyntheticLambda1();
    }

    public static float getFraction(ValueAnimator r0) {
        return r0.getAnimatedFraction();
    }

    public static ValueAnimator simpleValueAnimator() {
        return ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    }

    public static void startAnimator(View r1, Animator r2) {
        startAnimator(r1, r2, false);
    }

    public static void startAnimator(View r1, Animator r2, boolean r3) {
        if (r1 == null) goto L15;
        if (r2 != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("animator must be not null");
        return;
    L5:
        if (r1.getMeasuredWidth() != 0) goto L7;
    L11:
        r1.addOnLayoutChangeListener(new AnonymousClass1(r1, r2));
        return;
    L7:
        if (r1.getMeasuredHeight() == 0) goto L11;
        if (r3 == true) goto L11;
        r2.start();
        return;
    L15:
        f$$ExternalSyntheticBUOutline1.m("view must be not null");
    }
}
