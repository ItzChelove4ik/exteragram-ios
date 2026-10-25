package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.SharedConfig;

/* loaded from: classes7.dex */
public abstract class BlurredFrameLayout extends FrameLayout {
    public int backgroundColor;
    public int backgroundPaddingBottom;
    public int backgroundPaddingTop;
    protected Paint backgroundPaint;
    private Rect blurBounds;
    public boolean drawBlur;
    public boolean isTopView;
    protected final SizeNotifierFrameLayout sizeNotifierFrameLayout;

    public BlurredFrameLayout(Context r1, SizeNotifierFrameLayout r2) {
        super(r1);
        this.backgroundColor = 0;
        this.isTopView = true;
        this.drawBlur = true;
        this.blurBounds = new Rect();
        this.sizeNotifierFrameLayout = r2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        if (SharedConfig.chatBlurEnabled() == true) goto L5;
    L22:
        Canvas r2 = r8;
    L23:
        super.dispatchDraw(r2);
        return;
    L5:
        if (this.sizeNotifierFrameLayout == null) goto L22;
        if (this.drawBlur == false) goto L22;
        if (this.backgroundColor == 0) goto L22;
        if (this.backgroundPaint != null) goto L13;
        this.backgroundPaint = new Paint();
    L13:
        this.backgroundPaint.setColor(this.backgroundColor);
        this.blurBounds.set(0, this.backgroundPaddingTop, getMeasuredWidth(), getMeasuredHeight() - this.backgroundPaddingBottom);
        float r3 = 0.0f;
        View r0 = this;
    L14:
        SizeNotifierFrameLayout r1 = this.sizeNotifierFrameLayout;
        if (r0 == r1) goto L21;
        r3 = r3 + r0.getY();
        Object r02 = r0.getParent();
        if ((r02 instanceof View) == false) goto L19;
        r0 = (View) r02;
        goto L14
    L19:
        super.dispatchDraw(r8);
        return;
    L21:
        r2 = r8;
        r1.drawBlurRect(r2, r3, this.blurBounds, this.backgroundPaint, this.isTopView);
        goto L23
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        if (SharedConfig.chatBlurEnabled() == false) goto L7;
        SizeNotifierFrameLayout r0 = this.sizeNotifierFrameLayout;
        if (r0 == null) goto L7;
        r0.blurBehindViews.add(this);
    L7:
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        SizeNotifierFrameLayout r0 = this.sizeNotifierFrameLayout;
        if (r0 == null) goto L5;
        r0.blurBehindViews.remove(this);
    L5:
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        if (SharedConfig.chatBlurEnabled() == true) goto L5;
    L8:
        super.setBackgroundColor(r2);
        return;
    L5:
        if (this.sizeNotifierFrameLayout == null) goto L8;
        this.backgroundColor = r2;
    }

    @Override // android.view.View
    public void setTranslationY(float r2) {
        if (SharedConfig.chatBlurEnabled() == true) goto L5;
    L7:
        super.setTranslationY(r2);
        return;
    L5:
        if (r2 == getTranslationY()) goto L7;
        invalidate();
        goto L7
    }
}
