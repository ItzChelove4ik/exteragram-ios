package com.google.android.material.progressindicator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.R;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
import com.google.android.material.progressindicator.DrawingDelegate;
import java.util.List;

/* loaded from: classes5.dex */
public final class IndeterminateDrawable<S extends BaseProgressIndicatorSpec> extends DrawableWithAnimatedVisibilityChange {
    private IndeterminateAnimatorDelegate<ObjectAnimator> animatorDelegate;
    private DrawingDelegate<S> drawingDelegate;
    private Drawable staticDummyDrawable;

    public IndeterminateDrawable(Context r1, BaseProgressIndicatorSpec r2, DrawingDelegate<S> r3, IndeterminateAnimatorDelegate<ObjectAnimator> r4) {
        super(r1, r2);
        setDrawingDelegate(r3);
        setAnimatorDelegate(r4);
    }

    public static IndeterminateDrawable<CircularProgressIndicatorSpec> createCircularDrawable(Context r1, CircularProgressIndicatorSpec r2) {
        return createCircularDrawable(r1, r2, new CircularDrawingDelegate(r2));
    }

    public static IndeterminateDrawable<CircularProgressIndicatorSpec> createCircularDrawable(Context r3, CircularProgressIndicatorSpec r4, CircularDrawingDelegate r5) {
        if (r4.indeterminateAnimationType != 1) goto L5;
        IndeterminateAnimatorDelegate r1 = new CircularIndeterminateRetreatAnimatorDelegate(r3, r4);
    L6:
        IndeterminateDrawable<CircularProgressIndicatorSpec> r0 = new IndeterminateDrawable(r3, r4, r5, r1);
        r0.setStaticDummyDrawable(VectorDrawableCompat.create(r3.getResources(), R.drawable.ic_mtrl_arrow_circle, null));
        return r0;
    L5:
        r1 = new CircularIndeterminateAdvanceAnimatorDelegate(r4);
        goto L6
    }

    public static IndeterminateDrawable<LinearProgressIndicatorSpec> createLinearDrawable(Context r1, LinearProgressIndicatorSpec r2) {
        return createLinearDrawable(r1, r2, new LinearDrawingDelegate(r2));
    }

    public static IndeterminateDrawable<LinearProgressIndicatorSpec> createLinearDrawable(Context r2, LinearProgressIndicatorSpec r3, LinearDrawingDelegate r4) {
        if (r3.indeterminateAnimationType != 0) goto L5;
        IndeterminateAnimatorDelegate r1 = new LinearIndeterminateContiguousAnimatorDelegate(r3);
    L7:
        return new IndeterminateDrawable(r2, r3, r4, r1);
    L5:
        r1 = new LinearIndeterminateDisjointAnimatorDelegate(r2, r3);
        goto L7
    }

    private boolean isSystemAnimatorDisabled() {
        AnimatorDurationScaleProvider r0 = this.animatorDurationScaleProvider;
        if (r0 != null) goto L5;
    L8:
        return false;
    L5:
        if (r0.getSystemAnimatorDurationScale(this.context.getContentResolver()) != 0.0f) goto L8;
        return true;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ void clearAnimationCallbacks() {
        super.clearAnimationCallbacks();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r14) {
        if (getBounds().isEmpty() == false) goto L5;
        return;
    L5:
        if (isVisible() == true) goto L7;
        return;
    L7:
        if (r14.getClipBounds(this.clipBounds) == false) goto L56;
        int r8 = 0;
        if (isSystemAnimatorDisabled() == false) goto L15;
        Drawable r0 = this.staticDummyDrawable;
        if (r0 == null) goto L15;
        r0.setBounds(getBounds());
        this.staticDummyDrawable.setTint(this.baseSpec.indicatorColors[0]);
        this.staticDummyDrawable.draw(r14);
        return;
    L15:
        r14.save();
        this.drawingDelegate.validateSpecAndAdjustCanvas(r14, getBounds(), getGrowFraction(), isShowing(), isHiding());
        int r9 = this.baseSpec.indicatorTrackGapSize;
        int r6 = getAlpha();
        BaseProgressIndicatorSpec r02 = this.baseSpec;
        if ((r02 instanceof LinearProgressIndicatorSpec) == false) goto L18;
    L23:
        boolean r10 = true;
    L24:
        if (r10 == false) goto L29;
        if (r9 != 0) goto L29;
        if (r02.hasWavyEffect(false) == true) goto L29;
        boolean r11 = true;
    L30:
        if (r11 == false) goto L33;
        this.drawingDelegate.fillTrack(r14, this.paint, 0.0f, 1.0f, this.baseSpec.trackColor, r6, 0);
    L32:
        int r7 = r9;
    L39:
        if (r8 >= this.animatorDelegate.activeIndicators.size()) goto L46;
        DrawingDelegate.ActiveIndicator r03 = this.animatorDelegate.activeIndicators.get(r8);
        r03.phaseFraction = getPhaseFraction();
        this.drawingDelegate.fillIndicator(r14, this.paint, r03, getAlpha());
        if (r8 <= 0) goto L45;
        if (r11 == true) goto L45;
        if (r10 == false) goto L45;
        this.drawingDelegate.fillTrack(r14, this.paint, this.animatorDelegate.activeIndicators.get(r8 - 1).endFraction, r03.startFraction, this.baseSpec.trackColor, r6, r7);
    L45:
        r8 = r8 + 1;
        goto L39
    L46:
        r14.restore();
        return;
    L33:
        if (r10 == false) goto L32;
        DrawingDelegate.ActiveIndicator r04 = this.animatorDelegate.activeIndicators.get(0);
        List<DrawingDelegate.ActiveIndicator> r1 = this.animatorDelegate.activeIndicators;
        DrawingDelegate.ActiveIndicator r12 = r1.get(r1.size() - 1);
        DrawingDelegate<S> r13 = this.drawingDelegate;
        if ((r13 instanceof LinearDrawingDelegate) == false) goto L37;
        r7 = r9;
        r13.fillTrack(r14, this.paint, 0.0f, r04.startFraction, this.baseSpec.trackColor, r6, r7);
        this.drawingDelegate.fillTrack(r14, this.paint, r12.endFraction, 1.0f, this.baseSpec.trackColor, r6, r7);
        goto L39
    L37:
        r7 = r9;
        r14.save();
        r14.rotate(r12.rotationDegree);
        this.drawingDelegate.fillTrack(r14, this.paint, r12.endFraction, r04.startFraction + 1.0f, this.baseSpec.trackColor, r6, r7);
        r14.restore();
    L29:
        r11 = false;
        goto L30
    L18:
        if ((r02 instanceof CircularProgressIndicatorSpec) == true) goto L20;
    L22:
        r10 = false;
        goto L24
    L20:
        if (((CircularProgressIndicatorSpec) r02).indeterminateTrackVisible == false) goto L22;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    public IndeterminateAnimatorDelegate<ObjectAnimator> getAnimatorDelegate() {
        return this.animatorDelegate;
    }

    public DrawingDelegate<S> getDrawingDelegate() {
        return this.drawingDelegate;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.drawingDelegate.getPreferredHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.drawingDelegate.getPreferredWidth();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    public Drawable getStaticDummyDrawable() {
        return this.staticDummyDrawable;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean hideNow() {
        return super.hideNow();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean isHiding() {
        return super.isHiding();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean isShowing() {
        return super.isShowing();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ void registerAnimationCallback(Animatable2Compat$AnimationCallback r1) {
        super.registerAnimationCallback(r1);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int r1) {
        super.setAlpha(r1);
    }

    public void setAnimatorDelegate(IndeterminateAnimatorDelegate<ObjectAnimator> r1) {
        this.animatorDelegate = r1;
        r1.registerDrawable(this);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter r1) {
        super.setColorFilter(r1);
    }

    public void setDrawingDelegate(DrawingDelegate<S> r1) {
        this.drawingDelegate = r1;
    }

    public void setStaticDummyDrawable(Drawable r1) {
        this.staticDummyDrawable = r1;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setVisible(boolean r1, boolean r2) {
        return super.setVisible(r1, r2);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean setVisible(boolean r1, boolean r2, boolean r3) {
        return super.setVisible(r1, r2, r3);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public boolean setVisibleInternal(boolean r3, boolean r4, boolean r5) {
        boolean r0 = super.setVisibleInternal(r3, r4, r5);
        if (isSystemAnimatorDisabled() == false) goto L9;
        Drawable r1 = this.staticDummyDrawable;
        if (r1 == null) goto L9;
        return r1.setVisible(r3, r4);
    L9:
        if (isRunning() == true) goto L11;
        this.animatorDelegate.cancelAnimatorImmediately();
    L11:
        if (r3 == false) goto L15;
        if (r5 == false) goto L15;
        this.animatorDelegate.startAnimator();
    L15:
        return r0;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean unregisterAnimationCallback(Animatable2Compat$AnimationCallback r1) {
        return super.unregisterAnimationCallback(r1);
    }
}
