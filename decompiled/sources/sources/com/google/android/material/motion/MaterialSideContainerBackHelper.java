package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes5.dex */
public class MaterialSideContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private final float maxScaleXDistanceGrow;
    private final float maxScaleXDistanceShrink;
    private final float maxScaleYDistance;

    /* renamed from: com.google.android.material.motion.MaterialSideContainerBackHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ MaterialSideContainerBackHelper this$0;
        final /* synthetic */ int val$gravity;
        final /* synthetic */ boolean val$leftSwipeEdge;

        public AnonymousClass1(MaterialSideContainerBackHelper r1, boolean r2, int r3) {
            this.this$0 = r1;
            this.val$leftSwipeEdge = r2;
            this.val$gravity = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.this$0.view.setTranslationX(0.0f);
            this.this$0.updateBackProgress(0.0f, this.val$leftSwipeEdge, this.val$gravity);
        }
    }

    public MaterialSideContainerBackHelper(View r2) {
        super(r2);
        Resources r22 = r2.getResources();
        this.maxScaleXDistanceShrink = r22.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_shrink);
        this.maxScaleXDistanceGrow = r22.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_grow);
        this.maxScaleYDistance = r22.getDimension(R.dimen.m3_back_progress_side_container_max_scale_y_distance);
    }

    private boolean checkAbsoluteGravity(int r1, int r2) {
        if ((Gravity.getAbsoluteGravity(r1, this.view.getLayoutDirection()) & r2) != r2) goto L6;
        return true;
    L6:
        return false;
    }

    private int getEdgeMargin(boolean r2) {
        ViewGroup.LayoutParams r1 = this.view.getLayoutParams();
        if ((r1 instanceof ViewGroup.MarginLayoutParams) == false) goto L10;
        ViewGroup.MarginLayoutParams r12 = (ViewGroup.MarginLayoutParams) r1;
        if (r2 == false) goto L9;
        return r12.leftMargin;
    L9:
        return r12.rightMargin;
    L10:
        return 0;
    }

    public void cancelBackProgress() {
        if (super.onCancelBackProgress() != null) goto L5;
        return;
    L5:
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f})});
        V r1 = this.view;
        if ((r1 instanceof ViewGroup) == false) goto L11;
        ViewGroup r12 = (ViewGroup) r1;
        int r2 = 0;
    L9:
        if (r2 >= r12.getChildCount()) goto L11;
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(r12.getChildAt(r2), View.SCALE_Y, new float[]{1.0f})});
        r2 = r2 + 1;
    L11:
        r0.setDuration(this.cancelDuration);
        r0.start();
    }

    public void finishBackProgress(BackEventCompat r8, int r9, Animator.AnimatorListener r10, ValueAnimator.AnimatorUpdateListener r11) {
        if (r8.getSwipeEdge() != 0) goto L5;
        boolean r0 = true;
    L6:
        boolean r3 = checkAbsoluteGravity(r9, 3);
        float r4 = (this.view.getWidth() * this.view.getScaleX()) + getEdgeMargin(r3);
        V r5 = this.view;
        Property r6 = View.TRANSLATION_X;
        if (r3 == false) goto L9;
        r4 = -r4;
    L9:
        ObjectAnimator r1 = ObjectAnimator.ofFloat(r5, r6, new float[]{r4});
        if (r11 == null) goto L12;
        r1.addUpdateListener(r11);
    L12:
        r1.setInterpolator(new FastOutSlowInInterpolator());
        r1.setDuration(AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, r8.getProgress()));
        r1.addListener(new AnonymousClass1(this, r0, r9));
        if (r10 == null) goto L15;
        r1.addListener(r10);
    L15:
        r1.start();
        return;
    L5:
        r0 = false;
        goto L6
    }

    public void startBackProgress(BackEventCompat r1) {
        super.onStartBackProgress(r1);
    }

    public void updateBackProgress(float r11, boolean r12, int r13) {
        float r112 = interpolateProgress(r11);
        boolean r132 = checkAbsoluteGravity(r13, 3);
        int r0 = 0;
        if (r12 != r132) goto L5;
        boolean r122 = true;
    L6:
        int r1 = this.view.getWidth();
        int r2 = this.view.getHeight();
        float r3 = r1;
        if (r3 <= 0.0f) goto L48;
        float r22 = r2;
        if (r22 <= 0.0f) goto L55;
        float r5 = this.maxScaleXDistanceShrink / r3;
        float r6 = this.maxScaleXDistanceGrow / r3;
        float r7 = this.maxScaleYDistance / r22;
        V r23 = this.view;
        if (r132 == false) goto L14;
        r3 = 0.0f;
    L14:
        r23.setPivotX(r3);
        if (r122 == true) goto L18;
        r6 = -r5;
    L18:
        float r24 = AnimationUtils.lerp(0.0f, r6, r112);
        float r52 = r24 + 1.0f;
        float r113 = 1.0f - AnimationUtils.lerp(0.0f, r7, r112);
        if (Float.isNaN(r52) == false) goto L21;
        return;
    L21:
        if (Float.isNaN(r113) == true) goto L56;
        this.view.setScaleX(r52);
        this.view.setScaleY(r113);
        V r10 = this.view;
        if ((r10 instanceof ViewGroup) == false) goto L54;
        ViewGroup r102 = (ViewGroup) r10;
    L27:
        if (r0 >= r102.getChildCount()) goto L57;
        View r62 = r102.getChildAt(r0);
        if (r132 == false) goto L32;
        int r72 = (r1 - r62.getRight()) + r62.getWidth();
    L33:
        r62.setPivotX(r72);
        r62.setPivotY(-r62.getTop());
        if (r122 == false) goto L36;
        float r73 = 1.0f - r24;
    L38:
        if (r113 == 0.0f) goto L40;
        float r8 = (r52 / r113) * r73;
    L42:
        if (Float.isNaN(r73) == true) goto L47;
        if (Float.isNaN(r8) == true) goto L47;
        r62.setScaleX(r73);
        r62.setScaleY(r8);
    L47:
        r0 = r0 + 1;
        goto L27
    L40:
        r8 = 1.0f;
        goto L42
    L36:
        r73 = 1.0f;
        goto L38
    L32:
        r72 = -r62.getLeft();
        goto L33
    L57:
        return;
    L54:
        return;
    L56:
        return;
    L55:
        return;
    L48:
        return;
    L5:
        r122 = false;
        goto L6
    }

    public void updateBackProgress(BackEventCompat r2, int r3) {
        if (super.onUpdateBackProgress(r2) != null) goto L6;
        return;
    L6:
        if (r2.getSwipeEdge() != 0) goto L8;
        boolean r0 = true;
    L9:
        updateBackProgress(r2.getProgress(), r0, r3);
        return;
    L8:
        r0 = false;
        goto L9
    }
}
