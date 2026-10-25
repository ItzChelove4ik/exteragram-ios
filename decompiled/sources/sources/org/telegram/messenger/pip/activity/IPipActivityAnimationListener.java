package org.telegram.messenger.pip.activity;

/* loaded from: classes.dex */
public interface IPipActivityAnimationListener {
    default void onEnterAnimationEnd(long r1) {
    }

    default void onEnterAnimationStart(long r1) {
    }

    default void onLeaveAnimationEnd(long r1) {
    }

    default void onLeaveAnimationStart(long r1) {
    }

    void onTransitionAnimationFrame();

    void onTransitionAnimationProgress(float r1);
}
