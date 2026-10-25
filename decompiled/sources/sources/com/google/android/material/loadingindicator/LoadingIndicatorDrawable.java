package com.google.android.material.loadingindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.R;
import com.google.android.material.progressindicator.AnimatorDurationScaleProvider;

/* loaded from: classes5.dex */
public final class LoadingIndicatorDrawable extends Drawable implements Drawable.Callback {
    int alpha;
    private LoadingIndicatorAnimatorDelegate animatorDelegate;
    AnimatorDurationScaleProvider animatorDurationScaleProvider;
    private final Context context;
    private LoadingIndicatorDrawingDelegate drawingDelegate;
    Paint paint;
    private final LoadingIndicatorSpec specs;
    private Drawable staticDummyDrawable;

    public LoadingIndicatorDrawable(Context r1, LoadingIndicatorSpec r2, LoadingIndicatorDrawingDelegate r3, LoadingIndicatorAnimatorDelegate r4) {
        this.context = r1;
        this.specs = r2;
        this.drawingDelegate = r3;
        this.animatorDelegate = r4;
        this.animatorDurationScaleProvider = new AnimatorDurationScaleProvider();
        this.paint = new Paint();
        r4.registerDrawable(this);
        setAlpha(255);
    }

    public static LoadingIndicatorDrawable create(Context r3, LoadingIndicatorSpec r4) {
        LoadingIndicatorDrawable r0 = new LoadingIndicatorDrawable(r3, r4, new LoadingIndicatorDrawingDelegate(r4), new LoadingIndicatorAnimatorDelegate(r4));
        r0.setStaticDummyDrawable(VectorDrawableCompat.create(r3.getResources(), R.drawable.ic_mtrl_arrow_circle, null));
        return r0;
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

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r5) {
        Rect r0 = new Rect();
        Rect r1 = getBounds();
        if (r1.isEmpty() == false) goto L5;
        return;
    L5:
        if (isVisible() == true) goto L7;
        return;
    L7:
        if (r5.getClipBounds(r0) == true) goto L10;
        return;
    L10:
        if (isSystemAnimatorDisabled() == false) goto L15;
        Drawable r02 = this.staticDummyDrawable;
        if (r02 == null) goto L15;
        r02.setBounds(r1);
        this.staticDummyDrawable.setTint(this.specs.indicatorColors[0]);
        this.staticDummyDrawable.draw(r5);
        return;
    L15:
        r5.save();
        this.drawingDelegate.adjustCanvas(r5, r1);
        this.drawingDelegate.drawContainer(r5, this.paint, this.specs.containerColor, getAlpha());
        this.drawingDelegate.drawIndicator(r5, this.paint, this.animatorDelegate.indicatorState, getAlpha());
        r5.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    public LoadingIndicatorAnimatorDelegate getAnimatorDelegate() {
        return this.animatorDelegate;
    }

    public LoadingIndicatorDrawingDelegate getDrawingDelegate() {
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

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public Drawable getStaticDummyDrawable() {
        return this.staticDummyDrawable;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        Drawable.Callback r12 = getCallback();
        if (r12 == null) goto L6;
        r12.invalidateDrawable(this);
        return;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        Drawable.Callback r12 = getCallback();
        if (r12 == null) goto L6;
        r12.scheduleDrawable(this, r2, r3);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        if (this.alpha == r2) goto L6;
        this.alpha = r2;
        invalidateSelf();
        return;
    }

    public void setAnimatorDelegate(LoadingIndicatorAnimatorDelegate r1) {
        this.animatorDelegate = r1;
        r1.registerDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.paint.setColorFilter(r2);
        invalidateSelf();
    }

    public void setDrawingDelegate(LoadingIndicatorDrawingDelegate r1) {
        this.drawingDelegate = r1;
    }

    public void setStaticDummyDrawable(Drawable r1) {
        this.staticDummyDrawable = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean r1, boolean r2) {
        return setVisible(r1, r2, r1);
    }

    public boolean setVisible(boolean r2, boolean r3, boolean r4) {
        boolean r32 = super.setVisible(r2, r3);
        this.animatorDelegate.cancelAnimatorImmediately();
        if (r2 == false) goto L8;
        if (r4 == false) goto L8;
        if (isSystemAnimatorDisabled() == true) goto L8;
        this.animatorDelegate.startAnimator();
    L8:
        return r32;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
        Drawable.Callback r12 = getCallback();
        if (r12 == null) goto L6;
        r12.unscheduleDrawable(this, r2);
        return;
    }
}
