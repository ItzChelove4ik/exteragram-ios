package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* loaded from: classes7.dex */
public class CrossfadeDrawable extends Drawable {
    private ValueAnimator animator;
    private Drawable.Callback bottomCallback;
    private final Drawable bottomDrawable;
    float globalAlpha;
    private float progress;
    private Drawable.Callback topCallback;
    private final Drawable topDrawable;

    /* renamed from: org.telegram.ui.Components.CrossfadeDrawable$1, reason: invalid class name */
    public class AnonymousClass1 implements Drawable.Callback {
        final /* synthetic */ CrossfadeDrawable this$0;

        public AnonymousClass1(CrossfadeDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r2) {
            if (CrossfadeDrawable.m10097$$Nest$fgetprogress(this.this$0) >= 1.0f) goto L6;
            this.this$0.invalidateSelf();
            return;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r2, Runnable r3, long r4) {
            if (CrossfadeDrawable.m10097$$Nest$fgetprogress(this.this$0) >= 1.0f) goto L6;
            this.this$0.scheduleSelf(r3, r4);
            return;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r2, Runnable r3) {
            if (CrossfadeDrawable.m10097$$Nest$fgetprogress(this.this$0) >= 1.0f) goto L6;
            this.this$0.unscheduleSelf(r3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.CrossfadeDrawable$2, reason: invalid class name */
    public class AnonymousClass2 implements Drawable.Callback {
        final /* synthetic */ CrossfadeDrawable this$0;

        public AnonymousClass2(CrossfadeDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r2) {
            if (CrossfadeDrawable.m10097$$Nest$fgetprogress(this.this$0) <= 0.0f) goto L6;
            this.this$0.invalidateSelf();
            return;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r2, Runnable r3, long r4) {
            if (CrossfadeDrawable.m10097$$Nest$fgetprogress(this.this$0) <= 0.0f) goto L6;
            this.this$0.scheduleSelf(r3, r4);
            return;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r2, Runnable r3) {
            if (CrossfadeDrawable.m10097$$Nest$fgetprogress(this.this$0) <= 0.0f) goto L6;
            this.this$0.unscheduleSelf(r3);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$HhGCBO1NpOiUAXmAe4hLKKXG2iw(CrossfadeDrawable r0, ValueAnimator r1) {
        r0.lambda$animateToProgress$0(r1);
    }

    /* renamed from: -$$Nest$fgetprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10097$$Nest$fgetprogress(CrossfadeDrawable r0) {
        return r0.progress;
    }

    public CrossfadeDrawable(Drawable r2, Drawable r3) {
        this.globalAlpha = 255.0f;
        this.topDrawable = r2;
        this.bottomDrawable = r3;
        if (r2 == null) goto L5;
        AnonymousClass1 r0 = new AnonymousClass1(this);
        this.topCallback = r0;
        r2.setCallback(r0);
    L5:
        if (r3 == null) goto L8;
        AnonymousClass2 r22 = new AnonymousClass2(this);
        this.bottomCallback = r22;
        r3.setCallback(r22);
        return;
    }

    private /* synthetic */ void lambda$animateToProgress$0(ValueAnimator r1) {
        setProgress(((Float) r1.getAnimatedValue()).floatValue());
        invalidateSelf();
    }

    private void updateChildVisibility(boolean r6) {
        Drawable r0 = this.topDrawable;
        boolean r2 = false;
        if (isVisible() == true) goto L5;
    L7:
        boolean r1 = false;
    L8:
        r0.setVisible(r1, r6);
        Drawable r02 = this.bottomDrawable;
        if (isVisible() == true) goto L11;
    L13:
        r02.setVisible(r2, r6);
        return;
    L11:
        if (this.progress <= 0.0f) goto L13;
        r2 = true;
        goto L13
    L5:
        if (this.progress >= 1.0f) goto L7;
        r1 = true;
        goto L8
    }

    public void animateToProgress(float r4) {
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{getProgress(), r4});
        this.animator = r02;
        r02.addUpdateListener(new CrossfadeDrawable$$ExternalSyntheticLambda0(this));
        this.animator.setDuration((long) (Math.abs(getProgress() - r4) * 200.0f));
        this.animator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animator.start();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r5) {
        updateChildVisibility(false);
        Drawable r0 = this.topDrawable;
        int r1 = (int) (this.globalAlpha * (1.0f - this.progress));
        r0.setAlpha(r1);
        Drawable r02 = this.bottomDrawable;
        int r2 = (int) (this.globalAlpha * this.progress);
        r02.setAlpha(r2);
        if (r1 <= 0) goto L5;
        this.topDrawable.draw(r5);
    L5:
        if (r2 <= 0) goto L8;
        this.bottomDrawable.draw(r5);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.topDrawable.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.topDrawable.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float getProgress() {
        return this.progress;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r2) {
        this.topDrawable.setBounds(r2);
        this.bottomDrawable.setBounds(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.globalAlpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.topDrawable.setColorFilter(r1);
    }

    public void setProgress(float r1) {
        this.progress = r1;
        updateChildVisibility(false);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean r1, boolean r2) {
        boolean r12 = super.setVisible(r1, r2);
        updateChildVisibility(r2);
        return r12;
    }
}
