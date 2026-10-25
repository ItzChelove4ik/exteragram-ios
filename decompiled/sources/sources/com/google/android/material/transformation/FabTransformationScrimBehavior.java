package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* loaded from: classes5.dex */
public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {
    public static final long COLLAPSE_DELAY = 0;
    public static final long COLLAPSE_DURATION = 150;
    public static final long EXPAND_DELAY = 75;
    public static final long EXPAND_DURATION = 150;
    private final MotionTiming collapseTiming;
    private final MotionTiming expandTiming;

    /* renamed from: com.google.android.material.transformation.FabTransformationScrimBehavior$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ FabTransformationScrimBehavior this$0;
        final /* synthetic */ View val$child;
        final /* synthetic */ boolean val$expanded;

        public AnonymousClass1(FabTransformationScrimBehavior r1, boolean r2, View r3) {
            this.this$0 = r1;
            this.val$expanded = r2;
            this.val$child = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$expanded == true) goto L6;
            this.val$child.setVisibility(4);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            if (this.val$expanded == false) goto L6;
            this.val$child.setVisibility(0);
            return;
        }
    }

    public FabTransformationScrimBehavior() {
        this.expandTiming = new MotionTiming(75, 150);
        this.collapseTiming = new MotionTiming(0, 150);
    }

    public FabTransformationScrimBehavior(Context r5, AttributeSet r6) {
        super(r5, r6);
        this.expandTiming = new MotionTiming(75, 150);
        this.collapseTiming = new MotionTiming(0, 150);
    }

    private void createScrimAnimation(View r3, boolean r4, boolean r5, List<Animator> r6, List<Animator.AnimatorListener> r7) {
        if (r4 == false) goto L4;
        MotionTiming r2 = this.expandTiming;
    L6:
        if (r4 == false) goto L10;
        if (r5 == true) goto L9;
        r3.setAlpha(0.0f);
    L9:
        ObjectAnimator r32 = ObjectAnimator.ofFloat(r3, View.ALPHA, new float[]{1.0f});
    L11:
        r2.apply(r32);
        r6.add(r32);
        return;
    L10:
        r32 = ObjectAnimator.ofFloat(r3, View.ALPHA, new float[]{0.0f});
        goto L11
    L4:
        r2 = this.collapseTiming;
        goto L6
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout r1, View r2, View r3) {
        return r3 instanceof FloatingActionButton;
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    public AnimatorSet onCreateExpandedStateChangeAnimation(View r7, View r8, boolean r9, boolean r10) {
        ArrayList r4 = new ArrayList();
        createScrimAnimation(r8, r9, r10, r4, new ArrayList());
        AnimatorSet r6 = new AnimatorSet();
        AnimatorSetCompat.playTogether(r6, r4);
        r6.addListener(new AnonymousClass1(this, r9, r8));
        return r6;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout r1, View r2, MotionEvent r3) {
        return super.onTouchEvent(r1, r2, r3);
    }
}
