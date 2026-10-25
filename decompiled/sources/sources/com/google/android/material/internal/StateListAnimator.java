package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class StateListAnimator {
    private final Animator.AnimatorListener animationListener;
    private Tuple lastMatch;
    ValueAnimator runningAnimator;
    private final ArrayList<Tuple> tuples;

    /* renamed from: com.google.android.material.internal.StateListAnimator$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ StateListAnimator this$0;

        public AnonymousClass1(StateListAnimator r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            StateListAnimator r1 = this.this$0;
            if (r1.runningAnimator != r2) goto L6;
            r1.runningAnimator = null;
            return;
        }
    }

    public static class Tuple {
        final ValueAnimator animator;
        final int[] specs;

        public Tuple(int[] r1, ValueAnimator r2) {
            this.specs = r1;
            this.animator = r2;
        }
    }

    public StateListAnimator() {
        this.tuples = new ArrayList();
        this.lastMatch = null;
        this.runningAnimator = null;
        this.animationListener = new AnonymousClass1(this);
    }

    private void cancel() {
        ValueAnimator r0 = this.runningAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.runningAnimator = null;
        return;
    }

    private void start(Tuple r1) {
        ValueAnimator r12 = r1.animator;
        this.runningAnimator = r12;
        r12.start();
    }

    public void addState(int[] r2, ValueAnimator r3) {
        Tuple r0 = new Tuple(r2, r3);
        r3.addListener(this.animationListener);
        this.tuples.add(r0);
    }

    public void jumpToCurrentState() {
        ValueAnimator r0 = this.runningAnimator;
        if (r0 == null) goto L6;
        r0.end();
        this.runningAnimator = null;
        return;
    }

    public void setState(int[] r5) {
        int r0 = this.tuples.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        Tuple r2 = this.tuples.get(r1);
        if (StateSet.stateSetMatches(r2.specs, r5) == true) goto L9;
        r1 = r1 + 1;
    L9:
        Tuple r52 = this.lastMatch;
        if (r2 == r52) goto L21;
        if (r52 == null) goto L14;
        cancel();
    L14:
        this.lastMatch = r2;
        if (r2 == null) goto L20;
        start(r2);
        return;
    L20:
        return;
    L21:
        return;
    L8:
        r2 = null;
        goto L9
    }
}
