package com.google.android.material.transition;

import androidx.transition.Transition;

/* loaded from: classes5.dex */
abstract class TransitionListenerAdapter implements Transition.TransitionListener {
    public TransitionListenerAdapter() {
    }

    @Override // androidx.transition.Transition.TransitionListener
    public void onTransitionCancel(Transition r1) {
    }

    @Override // androidx.transition.Transition.TransitionListener
    public void onTransitionEnd(Transition r1) {
    }

    @Override // androidx.transition.Transition.TransitionListener
    public /* bridge */ /* synthetic */ void onTransitionEnd(Transition r1, boolean r2) {
        super.onTransitionEnd(r1, r2);
    }

    @Override // androidx.transition.Transition.TransitionListener
    public void onTransitionPause(Transition r1) {
    }

    @Override // androidx.transition.Transition.TransitionListener
    public void onTransitionResume(Transition r1) {
    }

    @Override // androidx.transition.Transition.TransitionListener
    public void onTransitionStart(Transition r1) {
    }

    @Override // androidx.transition.Transition.TransitionListener
    public /* bridge */ /* synthetic */ void onTransitionStart(Transition r1, boolean r2) {
        super.onTransitionStart(r1, r2);
    }
}
