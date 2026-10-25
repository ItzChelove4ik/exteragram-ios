package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class AnimationCoordinator {
    private int animationsRunning;
    private final List<Animator> durationAnimations;
    private final List<DynamicAnimation<?>> dynamicAnimations;
    private final List<Listener> listeners;
    private boolean started;

    /* renamed from: com.google.android.material.animation.AnimationCoordinator$1, reason: invalid class name */
    public class AnonymousClass1 implements DynamicAnimation.OnAnimationEndListener {
        final /* synthetic */ AnimationCoordinator this$0;

        public AnonymousClass1(AnimationCoordinator r1) {
            this.this$0 = r1;
        }

        @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
        public void onAnimationEnd(DynamicAnimation r1, boolean r2, float r3, float r4) {
            r1.removeEndListener(this);
            AnimationCoordinator.access$000(this.this$0);
        }
    }

    /* renamed from: com.google.android.material.animation.AnimationCoordinator$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ AnimationCoordinator this$0;

        public AnonymousClass2(AnimationCoordinator r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            AnimationCoordinator.access$000(this.this$0);
        }
    }

    public interface Listener {
        void onAnimationsEnd();

        void onAnimationsStart();
    }

    public AnimationCoordinator() {
        this.durationAnimations = new ArrayList();
        this.dynamicAnimations = new ArrayList();
        this.listeners = new ArrayList();
        this.animationsRunning = 0;
        this.started = false;
    }

    public static /* synthetic */ void access$000(AnimationCoordinator r0) {
        r0.onAnimationFinished();
    }

    private void notifyAnimationsEnd() {
        Iterator<Listener> r0 = this.listeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().onAnimationsEnd();
        goto L4
    L6:
        this.started = false;
    }

    private void onAnimationFinished() {
        int r0 = this.animationsRunning - 1;
        this.animationsRunning = r0;
        if (r0 != 0) goto L6;
        notifyAnimationsEnd();
        return;
    }

    public void addAnimator(Animator r1) {
        this.durationAnimations.add(r1);
    }

    public void addDynamicAnimation(DynamicAnimation<?> r1) {
        this.dynamicAnimations.add(r1);
    }

    public void addListener(Listener r1) {
        this.listeners.add(r1);
    }

    public void clear() {
        ArrayList r0 = new ArrayList(this.durationAnimations);
        this.durationAnimations.clear();
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        Object r4 = r0.get(r3);
        r3 = r3 + 1;
        ((Animator) r4).end();
        goto L3
    L5:
        ArrayList r02 = new ArrayList(this.dynamicAnimations);
        this.dynamicAnimations.clear();
        int r12 = r02.size();
        int r32 = 0;
    L6:
        if (r32 >= r12) goto L14;
        Object r42 = r02.get(r32);
        r32 = r32 + 1;
        DynamicAnimation r43 = (DynamicAnimation) r42;
        if ((r43 instanceof SpringAnimation) == true) goto L9;
        r43.cancel();
        goto L6
    L9:
        SpringAnimation r44 = (SpringAnimation) r43;
        if (r44.canSkipToEnd() == true) goto L11;
        r44.cancel();
        goto L6
    L11:
        r44.skipToEnd();
        goto L6
    L14:
        this.listeners.clear();
        this.animationsRunning = 0;
        this.started = false;
    }

    public void removeListener(Listener r1) {
        this.listeners.remove(r1);
    }

    public void start() {
        if (this.started == true) goto L27;
        this.started = true;
        Iterator<Listener> r1 = this.listeners.iterator();
    L7:
        if (r1.hasNext() == false) goto L9;
        r1.next().onAnimationsStart();
        goto L7
    L9:
        this.animationsRunning = this.dynamicAnimations.size();
        if (this.durationAnimations.isEmpty() == true) goto L13;
        this.animationsRunning++;
    L13:
        if (this.animationsRunning != 0) goto L16;
        notifyAnimationsEnd();
        return;
    L16:
        AnonymousClass1 r0 = new AnonymousClass1(this);
        Iterator<DynamicAnimation<?>> r12 = this.dynamicAnimations.iterator();
    L18:
        if (r12.hasNext() == false) goto L21;
        DynamicAnimation<?> r2 = r12.next();
        r2.addEndListener(r0);
        r2.start();
        goto L18
    L21:
        if (this.durationAnimations.isEmpty() == true) goto L26;
        AnimatorSet r02 = new AnimatorSet();
        AnimatorSetCompat.playTogether(r02, new ArrayList(this.durationAnimations));
        r02.addListener(new AnonymousClass2(this));
        r02.start();
        return;
    L26:
        return;
    }
}
