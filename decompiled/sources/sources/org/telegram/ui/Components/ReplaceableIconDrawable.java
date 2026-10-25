package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class ReplaceableIconDrawable extends Drawable implements Animator.AnimatorListener, Drawable.Callback {
    private ValueAnimator animation;
    private ColorFilter colorFilter;
    private Context context;
    private Drawable currentDrawable;
    private int currentResId;
    public boolean exactlyBounds;
    private Drawable outDrawable;
    ArrayList<View> parentViews;
    private float progress;

    public static /* synthetic */ void $r8$lambda$_9m8W73ZevoAWAnoZAte4vyfwrA(ReplaceableIconDrawable r0, ValueAnimator r1) {
        r0.lambda$setIcon$0(r1);
    }

    public ReplaceableIconDrawable(Context r2) {
        this.currentResId = 0;
        this.progress = 1.0f;
        this.parentViews = new ArrayList();
        this.context = r2;
    }

    private /* synthetic */ void lambda$setIcon$0(ValueAnimator r1) {
        this.progress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidateSelf();
    }

    private void updateBounds(Drawable r4, Rect r5) {
        if (r4 != null) goto L5;
        return;
    L5:
        if (this.exactlyBounds == false) goto L9;
        r4.setBounds(r5);
        return;
    L9:
        if (r4.getIntrinsicHeight() >= 0) goto L11;
        int r3 = r5.top;
        int r0 = r5.bottom;
    L13:
        if (r4.getIntrinsicWidth() >= 0) goto L15;
        int r1 = r5.left;
        int r52 = r5.right;
    L16:
        r4.setBounds(r1, r3, r52, r0);
        return;
    L15:
        int r12 = (r5.width() - r4.getIntrinsicWidth()) / 2;
        int r53 = r5.left;
        int r2 = r53 + r12;
        r52 = (r53 + r12) + r4.getIntrinsicWidth();
        r1 = r2;
        goto L16
    L11:
        int r32 = (r5.height() - r4.getIntrinsicHeight()) / 2;
        int r02 = r5.top;
        int r13 = r02 + r32;
        r0 = (r02 + r32) + r4.getIntrinsicHeight();
        r3 = r13;
        goto L13
    }

    public void addView(View r2) {
        if (this.parentViews.contains(r2) == true) goto L6;
        this.parentViews.add(r2);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r9) {
        int r0 = getBounds().centerX();
        int r1 = getBounds().centerY();
        if (this.progress != 1.0f) goto L5;
    L7:
        Drawable r2 = this.currentDrawable;
        if (r2 == null) goto L10;
        r2.setAlpha(255);
        this.currentDrawable.draw(r9);
    L10:
        float r22 = this.progress;
        if (r22 != 1.0f) goto L13;
    L16:
        Drawable r02 = this.outDrawable;
        if (r02 == null) goto L20;
        r02.setAlpha(255);
        this.outDrawable.draw(r9);
        return;
    L20:
        return;
    L13:
        if (this.outDrawable == null) goto L16;
        float r3 = 1.0f - r22;
        r9.save();
        r9.scale(r3, r3, r0, r1);
        this.outDrawable.setAlpha((int) (r3 * 255.0f));
        this.outDrawable.draw(r9);
        r9.restore();
        return;
    L5:
        if (this.currentDrawable == null) goto L7;
        r9.save();
        float r23 = this.progress;
        r9.scale(r23, r23, r0, r1);
        this.currentDrawable.setAlpha((int) (this.progress * 255.0f));
        this.currentDrawable.draw(r9);
        r9.restore();
        goto L10
    }

    public Drawable getIcon() {
        return this.currentDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        super.invalidateSelf();
        if (this.parentViews == null) goto L8;
        int r0 = 0;
    L6:
        if (r0 >= this.parentViews.size()) goto L10;
        this.parentViews.get(r0).invalidate();
        r0 = r0 + 1;
        goto L6
    L10:
        return;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator r1) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator r1) {
        this.outDrawable = null;
        invalidateSelf();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator r1) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r2) {
        super.onBoundsChange(r2);
        updateBounds(this.currentDrawable, r2);
        updateBounds(this.outDrawable, r2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        scheduleSelf(r2, r3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        Drawable r0 = this.currentDrawable;
        if (r0 == null) goto L5;
        r0.setAlpha(r2);
    L5:
        Drawable r1 = this.outDrawable;
        if (r1 == null) goto L9;
        r1.setAlpha(r2);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.colorFilter = r2;
        Drawable r0 = this.currentDrawable;
        if (r0 == null) goto L5;
        r0.setColorFilter(r2);
    L5:
        Drawable r02 = this.outDrawable;
        if (r02 == null) goto L8;
        r02.setColorFilter(r2);
    L8:
        invalidateSelf();
    }

    public void setIcon(int r2, boolean r3) {
        if (this.currentResId != r2) goto L5;
        return;
    L5:
        setIcon(ContextCompat.getDrawable(this.context, r2).mutate(), r3);
        this.currentResId = r2;
    }

    public void setIcon(Drawable r4, boolean r5) {
        if (r4 != null) goto L10;
        Drawable r42 = this.currentDrawable;
        if (r42 == null) goto L7;
        r42.setCallback(null);
    L7:
        this.currentDrawable = null;
        this.outDrawable = null;
        invalidateSelf();
        return;
    L10:
        if (getBounds() != null) goto L12;
    L13:
        r5 = false;
    L14:
        Drawable r1 = this.currentDrawable;
        if (r4 != r1) goto L18;
        r1.setColorFilter(this.colorFilter);
        return;
    L18:
        this.currentResId = 0;
        if (r1 == null) goto L21;
        r1.setCallback(null);
    L21:
        this.outDrawable = this.currentDrawable;
        this.currentDrawable = r4;
        r4.setCallback(this);
        this.currentDrawable.setColorFilter(this.colorFilter);
        updateBounds(this.currentDrawable, getBounds());
        updateBounds(this.outDrawable, getBounds());
        ValueAnimator r43 = this.animation;
        if (r43 == null) goto L24;
        r43.removeAllListeners();
        this.animation.cancel();
    L24:
        if (r5 == true) goto L27;
        this.progress = 1.0f;
        this.outDrawable = null;
        invalidateSelf();
        return;
    L27:
        ValueAnimator r44 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animation = r44;
        r44.addUpdateListener(new ReplaceableIconDrawable$$ExternalSyntheticLambda0(this));
        this.animation.addListener(this);
        this.animation.setDuration(150);
        this.animation.start();
        return;
    L12:
        if (getBounds().isEmpty() == false) goto L14;
        goto L13
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
        unscheduleSelf(r2);
    }
}
