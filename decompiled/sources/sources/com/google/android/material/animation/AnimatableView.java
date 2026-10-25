package com.google.android.material.animation;

/* loaded from: classes5.dex */
public interface AnimatableView {

    public interface Listener {
        void onAnimationEnd();
    }

    void startAnimation(Listener r1);

    void stopAnimation();
}
