package org.telegram.ui.Components;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.animation.ValueAnimator;
import android.util.Property;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import java.util.function.Consumer;

/* loaded from: classes3.dex */
public abstract class ScaleStateListAnimator {
    public static /* synthetic */ void $r8$lambda$J6zip7LVAceW8YA6qsT51JKlmDU(Consumer r0, ValueAnimator r1) {
        r0.accept((Float) r1.getAnimatedValue());
    }

    /* renamed from: $r8$lambda$tDVRH9-xfWOFEBlCBT529PtiqFs, reason: not valid java name */
    public static /* synthetic */ void m12500$r8$lambda$tDVRH9xfWOFEBlCBT529PtiqFs(Consumer r0, ValueAnimator r1) {
        r0.accept((Float) r1.getAnimatedValue());
    }

    public static void apply(View r3) {
        apply(r3, 0.1f, 1.5f, null, null);
    }

    public static void apply(View r1, float r2, float r3) {
        apply(r1, r2, r3, null, null);
    }

    public static void apply(View r16, float r17, float r18, Consumer<Float> r19, Consumer<Float> r20) {
        if (r16 != null) goto L5;
        return;
    L5:
        AnimatorSet r3 = new AnimatorSet();
        AnimatorSet r4 = new AnimatorSet();
        Property r5 = View.SCALE_X;
        float r7 = 1.0f - r17;
        ObjectAnimator r9 = ObjectAnimator.ofFloat(r16, r5, new float[]{r7});
        Property r11 = View.SCALE_Y;
        r4.playTogether(new Animator[]{r9, ObjectAnimator.ofFloat(r16, r11, new float[]{r7})});
        r4.setDuration(80);
        AnimatorSet r72 = new AnimatorSet();
        AnimatorSet r92 = new AnimatorSet();
        r92.playTogether(new Animator[]{ObjectAnimator.ofFloat(r16, r5, new float[]{1.0f}), ObjectAnimator.ofFloat(r16, r11, new float[]{1.0f})});
        r92.setInterpolator(new OvershootInterpolator(r18));
        r92.setDuration(350);
        StateListAnimator r112 = new StateListAnimator();
        if (r19 == null) goto L8;
        char r172 = 1;
        ValueAnimator r8 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r8.setDuration(80);
        r8.addUpdateListener(new ScaleStateListAnimator$$ExternalSyntheticLambda0(r19));
        r3.playTogether(new Animator[]{r4, r8});
        r112.addState(new int[]{R.attr.state_pressed}, r3);
    L9:
        if (r20 == null) goto L11;
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        r1.setDuration(350);
        r1.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r1.addUpdateListener(new ScaleStateListAnimator$$ExternalSyntheticLambda1(r20));
        Animator[] r2 = new Animator[2];
        r2[0] = r92;
        r2[r172] = r1;
        r72.playTogether(r2);
        r112.addState(new int[0], r72);
    L12:
        r16.setStateListAnimator(r112);
        return;
    L11:
        r112.addState(new int[0], r92);
        goto L12
    L8:
        r172 = 1;
        r112.addState(new int[]{R.attr.state_pressed}, r4);
        goto L9
    }

    public static void reset(View r1) {
        r1.setStateListAnimator(null);
    }
}
