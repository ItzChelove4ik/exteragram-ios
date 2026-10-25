package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

@Deprecated
/* loaded from: classes5.dex */
public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {
    private AnimatorSet currentAnimation;

    /* renamed from: com.google.android.material.transformation.ExpandableTransformationBehavior$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ ExpandableTransformationBehavior this$0;

        public AnonymousClass1(ExpandableTransformationBehavior r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ExpandableTransformationBehavior.access$002(this.this$0, null);
        }
    }

    public ExpandableTransformationBehavior() {
    }

    public ExpandableTransformationBehavior(Context r1, AttributeSet r2) {
        super(r1, r2);
    }

    public static /* synthetic */ AnimatorSet access$002(ExpandableTransformationBehavior r0, AnimatorSet r1) {
        r0.currentAnimation = r1;
        return r1;
    }

    public abstract AnimatorSet onCreateExpandedStateChangeAnimation(View r1, View r2, boolean r3, boolean r4);

    @Override // com.google.android.material.transformation.ExpandableBehavior
    public boolean onExpandedStateChange(View r4, View r5, boolean r6, boolean r7) {
        AnimatorSet r0 = this.currentAnimation;
        if (r0 == null) goto L5;
        boolean r2 = true;
    L6:
        if (r2 == false) goto L8;
        r0.cancel();
    L8:
        AnimatorSet r42 = onCreateExpandedStateChangeAnimation(r4, r5, r6, r2);
        this.currentAnimation = r42;
        r42.addListener(new AnonymousClass1(this));
        this.currentAnimation.start();
        if (r7 == true) goto L11;
        this.currentAnimation.end();
    L11:
        return true;
    L5:
        r2 = false;
        goto L6
    }
}
