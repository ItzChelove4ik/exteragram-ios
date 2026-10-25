package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes5.dex */
public class MaterialBottomContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private final float maxScaleXDistance;
    private final float maxScaleYDistance;

    /* renamed from: com.google.android.material.motion.MaterialBottomContainerBackHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ MaterialBottomContainerBackHelper this$0;

        public AnonymousClass1(MaterialBottomContainerBackHelper r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.view.setTranslationY(0.0f);
            this.this$0.updateBackProgress(0.0f);
        }
    }

    public MaterialBottomContainerBackHelper(View r2) {
        super(r2);
        Resources r22 = r2.getResources();
        this.maxScaleXDistance = r22.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_x_distance);
        this.maxScaleYDistance = r22.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_y_distance);
    }

    private Animator createResetScaleAnimator() {
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f})});
        V r8 = this.view;
        if ((r8 instanceof ViewGroup) == false) goto L8;
        ViewGroup r82 = (ViewGroup) r8;
        int r1 = 0;
    L6:
        if (r1 >= r82.getChildCount()) goto L8;
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(r82.getChildAt(r1), View.SCALE_Y, new float[]{1.0f})});
        r1 = r1 + 1;
    L8:
        r0.setInterpolator(new FastOutSlowInInterpolator());
        return r0;
    }

    public void cancelBackProgress() {
        if (super.onCancelBackProgress() != null) goto L5;
        return;
    L5:
        Animator r0 = createResetScaleAnimator();
        r0.setDuration(this.cancelDuration);
        r0.start();
    }

    public void finishBackProgressNotPersistent(BackEventCompat r6, Animator.AnimatorListener r7) {
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Y, new float[]{this.view.getHeight() * this.view.getScaleY()});
        r0.setInterpolator(new FastOutSlowInInterpolator());
        r0.setDuration(AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, r6.getProgress()));
        r0.addListener(new AnonymousClass1(this));
        if (r7 == null) goto L5;
        r0.addListener(r7);
    L5:
        r0.start();
    }

    public void finishBackProgressPersistent(BackEventCompat r3, Animator.AnimatorListener r4) {
        Animator r0 = createResetScaleAnimator();
        r0.setDuration(AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, r3.getProgress()));
        if (r4 == null) goto L5;
        r0.addListener(r4);
    L5:
        r0.start();
    }

    public void startBackProgress(BackEventCompat r1) {
        super.onStartBackProgress(r1);
    }

    public void updateBackProgress(float r7) {
        float r72 = interpolateProgress(r7);
        float r0 = this.view.getWidth();
        float r1 = this.view.getHeight();
        if (r0 > 0.0f) goto L5;
        return;
    L5:
        if (r1 <= 0.0f) goto L28;
        float r3 = this.maxScaleXDistance / r0;
        float r02 = this.maxScaleYDistance / r1;
        float r32 = 1.0f - AnimationUtils.lerp(0.0f, r3, r72);
        float r73 = 1.0f - AnimationUtils.lerp(0.0f, r02, r72);
        if (Float.isNaN(r32) == false) goto L10;
        return;
    L10:
        if (Float.isNaN(r73) == true) goto L29;
        this.view.setScaleX(r32);
        this.view.setPivotY(r1);
        this.view.setScaleY(r73);
        V r6 = this.view;
        if ((r6 instanceof ViewGroup) == false) goto L27;
        ViewGroup r62 = (ViewGroup) r6;
        int r12 = 0;
    L16:
        if (r12 >= r62.getChildCount()) goto L30;
        View r4 = r62.getChildAt(r12);
        r4.setPivotY(-r4.getTop());
        if (r73 == 0.0f) goto L20;
        float r5 = r32 / r73;
    L21:
        r4.setScaleY(r5);
        r12 = r12 + 1;
        goto L16
    L20:
        r5 = 1.0f;
        goto L21
    L30:
        return;
    L27:
        return;
    L29:
        return;
    }

    public void updateBackProgress(BackEventCompat r2) {
        if (super.onUpdateBackProgress(r2) != null) goto L5;
        return;
    L5:
        updateBackProgress(r2.getProgress());
    }
}
