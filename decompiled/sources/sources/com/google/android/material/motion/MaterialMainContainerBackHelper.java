package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.RoundedCorner;
import android.view.View;
import android.view.WindowInsets;
import androidx.activity.BackEventCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.ViewUtils;

/* loaded from: classes5.dex */
public class MaterialMainContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private static final float MIN_SCALE = 0.9f;
    private float[] expandedCornerRadii;
    private Rect initialHideFromClipBounds;
    private Rect initialHideToClipBounds;
    private float initialTouchY;
    private final float maxTranslationY;
    private final float minEdgeGap;

    /* renamed from: com.google.android.material.motion.MaterialMainContainerBackHelper$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ MaterialMainContainerBackHelper this$0;
        final /* synthetic */ View val$collapsedView;

        public AnonymousClass1(MaterialMainContainerBackHelper r1, View r2) {
            this.this$0 = r1;
            this.val$collapsedView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            View r0 = this.val$collapsedView;
            if (r0 == null) goto L6;
            r0.setVisibility(0);
            return;
        }
    }

    public static /* synthetic */ Object $r8$lambda$76hsz7dP7pe15A7fYoJp3qKWxZM(float r0, Object r1, Object r2) {
        return lerpCornerRadii((float[]) r1, (float[]) r2, r0);
    }

    /* renamed from: $r8$lambda$TrbptAc0r5TTsrqPOAYLfpiNe-0, reason: not valid java name */
    public static /* synthetic */ void m1884$r8$lambda$TrbptAc0r5TTsrqPOAYLfpiNe0(ClippableRoundedCornerLayout r0, ValueAnimator r1) {
        r0.updateCornerRadii((float[]) r1.getAnimatedValue());
    }

    public MaterialMainContainerBackHelper(View r2) {
        super(r2);
        Resources r22 = r2.getResources();
        this.minEdgeGap = r22.getDimension(R.dimen.m3_back_progress_main_container_min_edge_gap);
        this.maxTranslationY = r22.getDimension(R.dimen.m3_back_progress_main_container_max_translation_y);
    }

    private float[] calculateExpandedCornerRadii() {
        if (Build.VERSION.SDK_INT < 31) goto L30;
        WindowInsets r0 = this.view.getRootWindowInsets();
        if (r0 == null) goto L30;
        DisplayMetrics r1 = this.view.getResources().getDisplayMetrics();
        int r3 = r1.widthPixels;
        int r12 = r1.heightPixels;
        int[] r5 = new int[2];
        this.view.getLocationOnScreen(r5);
        int r7 = r5[0];
        int r52 = r5[1];
        int r9 = this.view.getWidth();
        int r10 = this.view.getHeight();
        if (r7 != 0) goto L10;
        if (r52 != 0) goto L10;
        int r11 = getRoundedCornerRadius(r0, 0);
    L11:
        int r92 = r9 + r7;
        if (r92 < r3) goto L15;
        if (r52 != 0) goto L15;
        int r122 = getRoundedCornerRadius(r0, 1);
    L16:
        if (r92 >= r3) goto L18;
    L20:
        int r32 = 0;
    L22:
        if (r7 == 0) goto L24;
    L26:
        int r13 = 0;
    L27:
        float r02 = r11;
        float r14 = r122;
        float r33 = r32;
        float r132 = r13;
        return new float[]{r02, r02, r14, r14, r33, r33, r132, r132};
    L24:
        if ((r52 + r10) < r12) goto L26;
        r13 = getRoundedCornerRadius(r0, 3);
        goto L27
    L18:
        if ((r52 + r10) < r12) goto L20;
        r32 = getRoundedCornerRadius(r0, 2);
    L15:
        r122 = 0;
    L10:
        r11 = 0;
    L30:
        return new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    private ValueAnimator createCornerAnimator(ClippableRoundedCornerLayout r3) {
        ValueAnimator r2 = ValueAnimator.ofObject(new MaterialMainContainerBackHelper$$ExternalSyntheticLambda0(), new Object[]{r3.getCornerRadii(), getExpandedCornerRadii()});
        r2.addUpdateListener(new MaterialMainContainerBackHelper$$ExternalSyntheticLambda1(r3));
        return r2;
    }

    private AnimatorSet createResetScaleAndTranslationAnimator(View r11) {
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.TRANSLATION_X, new float[]{0.0f}), ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Y, new float[]{0.0f})});
        r0.addListener(new AnonymousClass1(this, r11));
        return r0;
    }

    private int getRoundedCornerRadius(WindowInsets r1, int r2) {
        RoundedCorner r0 = r1.getRoundedCorner(r2);
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getRadius();
    }

    private static float[] lerpCornerRadii(float[] r18, float r19, float r20) {
        return new float[]{AnimationUtils.lerp(r18[0], r19, r20), AnimationUtils.lerp(r18[1], r19, r20), AnimationUtils.lerp(r18[2], r19, r20), AnimationUtils.lerp(r18[3], r19, r20), AnimationUtils.lerp(r18[4], r19, r20), AnimationUtils.lerp(r18[5], r19, r20), AnimationUtils.lerp(r18[6], r19, r20), AnimationUtils.lerp(r18[7], r19, r20)};
    }

    private static float[] lerpCornerRadii(float[] r18, float[] r19, float r20) {
        return new float[]{AnimationUtils.lerp(r18[0], r19[0], r20), AnimationUtils.lerp(r18[1], r19[1], r20), AnimationUtils.lerp(r18[2], r19[2], r20), AnimationUtils.lerp(r18[3], r19[3], r20), AnimationUtils.lerp(r18[4], r19[4], r20), AnimationUtils.lerp(r18[5], r19[5], r20), AnimationUtils.lerp(r18[6], r19[6], r20), AnimationUtils.lerp(r18[7], r19[7], r20)};
    }

    private void resetInitialValues() {
        this.initialTouchY = 0.0f;
        this.initialHideToClipBounds = null;
        this.initialHideFromClipBounds = null;
    }

    public void cancelBackProgress(View r4) {
        if (super.onCancelBackProgress() != null) goto L5;
        return;
    L5:
        AnimatorSet r42 = createResetScaleAndTranslationAnimator(r4);
        V r0 = this.view;
        if ((r0 instanceof ClippableRoundedCornerLayout) == false) goto L8;
        r42.playTogether(new Animator[]{createCornerAnimator((ClippableRoundedCornerLayout) r0)});
    L8:
        r42.setDuration(this.cancelDuration);
        r42.start();
        resetInitialValues();
    }

    public void clearExpandedCornerRadii() {
        this.expandedCornerRadii = null;
    }

    public void finishBackProgress(long r1, View r3) {
        AnimatorSet r32 = createResetScaleAndTranslationAnimator(r3);
        r32.setDuration(r1);
        r32.start();
        resetInitialValues();
    }

    public float[] getExpandedCornerRadii() {
        if (this.expandedCornerRadii != null) goto L6;
        this.expandedCornerRadii = calculateExpandedCornerRadii();
    L6:
        return this.expandedCornerRadii;
    }

    public Rect getInitialHideFromClipBounds() {
        return this.initialHideFromClipBounds;
    }

    public Rect getInitialHideToClipBounds() {
        return this.initialHideToClipBounds;
    }

    public void startBackProgress(float r2, View r3) {
        this.initialHideToClipBounds = ViewUtils.calculateRectFromBounds(this.view);
        if (r3 == null) goto L5;
        this.initialHideFromClipBounds = ViewUtils.calculateOffsetRectFromBounds(this.view, r3);
    L5:
        this.initialTouchY = r2;
    }

    public void startBackProgress(BackEventCompat r1, View r2) {
        super.onStartBackProgress(r1);
        startBackProgress(r1.getTouchY(), r2);
    }

    public void updateBackProgress(float r7, boolean r8, float r9, float r10) {
        float r72 = interpolateProgress(r7);
        float r0 = this.view.getWidth();
        float r1 = this.view.getHeight();
        if (r0 > 0.0f) goto L5;
        return;
    L5:
        if (r1 <= 0.0f) goto L26;
        float r3 = AnimationUtils.lerp(1.0f, MIN_SCALE, r72);
        float r02 = AnimationUtils.lerp(0.0f, Math.max(0.0f, ((r0 - (MIN_SCALE * r0)) / 2.0f) - this.minEdgeGap), r72);
        if (r8 == false) goto L10;
        int r82 = 1;
    L11:
        float r03 = r02 * r82;
        float r83 = Math.min(Math.max(0.0f, ((r1 - (r3 * r1)) / 2.0f) - this.minEdgeGap), this.maxTranslationY);
        float r92 = r9 - this.initialTouchY;
        float r4 = Math.abs(r92) / r1;
        float r84 = AnimationUtils.lerp(0.0f, r83, r4) * Math.signum(r92);
        if (Float.isNaN(r3) == false) goto L14;
        return;
    L14:
        if (Float.isNaN(r03) == false) goto L16;
        return;
    L16:
        if (Float.isNaN(r84) == true) goto L27;
        this.view.setScaleX(r3);
        this.view.setScaleY(r3);
        this.view.setTranslationX(r03);
        this.view.setTranslationY(r84);
        V r85 = this.view;
        if ((r85 instanceof ClippableRoundedCornerLayout) == false) goto L25;
        ((ClippableRoundedCornerLayout) r85).updateCornerRadii(lerpCornerRadii(getExpandedCornerRadii(), r10, r72));
        return;
    L25:
        return;
    L27:
        return;
    L10:
        r82 = -1;
        goto L11
    }

    public void updateBackProgress(BackEventCompat r3, View r4, float r5) {
        if (super.onUpdateBackProgress(r3) != null) goto L5;
        return;
    L5:
        if (r4 == null) goto L10;
        if (r4.getVisibility() == 4) goto L10;
        r4.setVisibility(4);
    L10:
        if (r3.getSwipeEdge() != 0) goto L12;
        boolean r42 = true;
    L13:
        updateBackProgress(r3.getProgress(), r42, r3.getTouchY(), r5);
        return;
    L12:
        r42 = false;
        goto L13
    }
}
