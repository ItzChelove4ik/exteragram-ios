package com.google.android.material.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

/* loaded from: classes5.dex */
public class FadeThroughDrawable extends Drawable {
    private final float[] alphas;
    private final Drawable fadeInDrawable;
    private final Drawable fadeOutDrawable;
    private float progress;

    /* renamed from: com.google.android.material.internal.FadeThroughDrawable$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class EmptyDrawable extends Drawable {
        private EmptyDrawable() {
        }

        public /* synthetic */ EmptyDrawable(AnonymousClass1 r1) {
            this();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public FadeThroughDrawable(Drawable r4, Drawable r5) {
        if (r4 == null) goto L5;
        Drawable r1 = r4.getConstantState().newDrawable().mutate();
    L6:
        this.fadeOutDrawable = r1;
        if (r5 == null) goto L9;
        Drawable r0 = r5.getConstantState().newDrawable().mutate();
    L10:
        this.fadeInDrawable = r0;
        int r2 = 3;
        if (r4 == null) goto L13;
        int r42 = DrawableCompat.getLayoutDirection(r4);
    L14:
        if (r5 == null) goto L16;
        r2 = DrawableCompat.getLayoutDirection(r5);
    L16:
        DrawableCompat.setLayoutDirection(r1, r42);
        DrawableCompat.setLayoutDirection(r0, r2);
        r0.setAlpha(0);
        this.alphas = new float[2];
        return;
    L13:
        r42 = 3;
        goto L14
    L9:
        r0 = new EmptyDrawable(null);
        goto L10
    L5:
        r1 = new EmptyDrawable(null);
        goto L6
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r2) {
        this.fadeOutDrawable.draw(r2);
        this.fadeInDrawable.draw(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return Math.max(this.fadeOutDrawable.getIntrinsicHeight(), this.fadeInDrawable.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.max(this.fadeOutDrawable.getIntrinsicWidth(), this.fadeInDrawable.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return Math.max(this.fadeOutDrawable.getMinimumHeight(), this.fadeInDrawable.getMinimumHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return Math.max(this.fadeOutDrawable.getMinimumWidth(), this.fadeInDrawable.getMinimumWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (this.fadeOutDrawable.isStateful() == false) goto L5;
        return true;
    L5:
        if (this.fadeInDrawable.isStateful() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r4) {
        float r0 = this.progress;
        Drawable r1 = this.fadeOutDrawable;
        if (r0 > 0.5f) goto L5;
        r1.setAlpha(r4);
        this.fadeInDrawable.setAlpha(0);
    L6:
        invalidateSelf();
        return;
    L5:
        r1.setAlpha(0);
        this.fadeInDrawable.setAlpha(r4);
        goto L6
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r2, int r3, int r4, int r5) {
        super.setBounds(r2, r3, r4, r5);
        this.fadeOutDrawable.setBounds(r2, r3, r4, r5);
        this.fadeInDrawable.setBounds(r2, r3, r4, r5);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.fadeOutDrawable.setColorFilter(r2);
        this.fadeInDrawable.setColorFilter(r2);
        invalidateSelf();
    }

    public void setProgress(float r4) {
        if (this.progress == r4) goto L6;
        this.progress = r4;
        FadeThroughUtils.calculateFadeOutAndInAlphas(r4, this.alphas);
        this.fadeOutDrawable.setAlpha((int) (this.alphas[0] * 255.0f));
        this.fadeInDrawable.setAlpha((int) (this.alphas[1] * 255.0f));
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] r2) {
        boolean r0 = this.fadeOutDrawable.setState(r2);
        boolean r1 = this.fadeInDrawable.setState(r2);
        if (r0 == true) goto L8;
        if (r1 == true) goto L10;
        return false;
    L10:
        return true;
    L8:
        return true;
    }
}
