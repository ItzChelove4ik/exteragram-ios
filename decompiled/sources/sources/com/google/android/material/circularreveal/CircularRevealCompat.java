package com.google.android.material.circularreveal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewAnimationUtils;
import com.google.android.material.circularreveal.CircularRevealWidget;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class CircularRevealCompat {

    /* renamed from: com.google.android.material.circularreveal.CircularRevealCompat$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ CircularRevealWidget val$view;

        public AnonymousClass1(CircularRevealWidget r1) {
            this.val$view = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$view.destroyCircularRevealCache();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.val$view.buildCircularRevealCache();
        }
    }

    private CircularRevealCompat() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Animator createCircularReveal(CircularRevealWidget r3, float r4, float r5, float r6) {
        ObjectAnimator r0 = ObjectAnimator.ofObject(r3, CircularRevealWidget.CircularRevealProperty.CIRCULAR_REVEAL, CircularRevealWidget.CircularRevealEvaluator.CIRCULAR_REVEAL, new CircularRevealWidget.RevealInfo[]{new CircularRevealWidget.RevealInfo(r4, r5, r6)});
        CircularRevealWidget.RevealInfo r1 = r3.getRevealInfo();
        if (r1 == null) goto L6;
        Animator r32 = ViewAnimationUtils.createCircularReveal((View) r3, (int) r4, (int) r5, r1.radius, r6);
        AnimatorSet r42 = new AnimatorSet();
        r42.playTogether(new Animator[]{r0, r32});
        return r42;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Caller must set a non-null RevealInfo before calling this.");
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Animator createCircularReveal(CircularRevealWidget r4, float r5, float r6, float r7, float r8) {
        ObjectAnimator r0 = ObjectAnimator.ofObject(r4, CircularRevealWidget.CircularRevealProperty.CIRCULAR_REVEAL, CircularRevealWidget.CircularRevealEvaluator.CIRCULAR_REVEAL, new CircularRevealWidget.RevealInfo[]{new CircularRevealWidget.RevealInfo(r5, r6, r7), new CircularRevealWidget.RevealInfo(r5, r6, r8)});
        Animator r42 = ViewAnimationUtils.createCircularReveal((View) r4, (int) r5, (int) r6, r7, r8);
        AnimatorSet r52 = new AnimatorSet();
        r52.playTogether(new Animator[]{r0, r42});
        return r52;
    }

    public static Animator.AnimatorListener createCircularRevealListener(CircularRevealWidget r1) {
        return new AnonymousClass1(r1);
    }
}
