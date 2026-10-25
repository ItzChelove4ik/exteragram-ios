package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* loaded from: classes7.dex */
public class HideViewAfterAnimation extends AnimatorListenerAdapter {
    private final boolean goneOnHide;
    private final View view;

    public HideViewAfterAnimation(View r1) {
        this.view = r1;
        this.goneOnHide = true;
    }

    public HideViewAfterAnimation(View r1, boolean r2) {
        this.view = r1;
        this.goneOnHide = r2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator r1) {
        super.onAnimationEnd(r1);
        View r12 = this.view;
        if (this.goneOnHide == false) goto L5;
        int r0 = 8;
    L6:
        r12.setVisibility(r0);
        return;
    L5:
        r0 = 4;
        goto L6
    }
}
