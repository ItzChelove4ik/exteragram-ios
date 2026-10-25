package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public class ForegroundLinearLayout extends LinearLayoutCompat {
    private Drawable foreground;
    boolean foregroundBoundsChanged;
    private int foregroundGravity;
    protected boolean mForegroundInPadding;
    private final Rect overlayBounds;
    private final Rect selfBounds;

    public ForegroundLinearLayout(Context r2) {
        this(r2, null);
    }

    public ForegroundLinearLayout(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public ForegroundLinearLayout(Context r9, AttributeSet r10, int r11) {
        super(r9, r10, r11);
        this.selfBounds = new Rect();
        this.overlayBounds = new Rect();
        this.foregroundGravity = 119;
        this.mForegroundInPadding = true;
        this.foregroundBoundsChanged = false;
        TypedArray r92 = ThemeEnforcement.obtainStyledAttributes(r9, r10, R.styleable.ForegroundLinearLayout, r11, 0, new int[0]);
        this.foregroundGravity = r92.getInt(R.styleable.ForegroundLinearLayout_android_foregroundGravity, this.foregroundGravity);
        Drawable r102 = r92.getDrawable(R.styleable.ForegroundLinearLayout_android_foreground);
        if (r102 == null) goto L5;
        setForeground(r102);
    L5:
        this.mForegroundInPadding = r92.getBoolean(R.styleable.ForegroundLinearLayout_foregroundInsidePadding, true);
        r92.recycle();
    }

    @Override // android.view.View
    public void draw(Canvas r9) {
        super.draw(r9);
        Drawable r0 = this.foreground;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.foregroundBoundsChanged == false) goto L11;
        this.foregroundBoundsChanged = false;
        Rect r2 = this.selfBounds;
        Rect r3 = this.overlayBounds;
        int r4 = getRight() - getLeft();
        int r5 = getBottom() - getTop();
        if (this.mForegroundInPadding == false) goto L9;
        r2.set(0, 0, r4, r5);
    L10:
        Gravity.apply(this.foregroundGravity, r0.getIntrinsicWidth(), r0.getIntrinsicHeight(), r2, r3);
        r0.setBounds(r3);
        goto L11
    L9:
        r2.set(getPaddingLeft(), getPaddingTop(), r4 - getPaddingRight(), r5 - getPaddingBottom());
    L11:
        r0.draw(r9);
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float r1, float r2) {
        super.drawableHotspotChanged(r1, r2);
        Drawable r0 = this.foreground;
        if (r0 == null) goto L6;
        r0.setHotspot(r1, r2);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable r0 = this.foreground;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isStateful() == false) goto L9;
        this.foreground.setState(getDrawableState());
        return;
    }

    @Override // android.view.View
    public Drawable getForeground() {
        return this.foreground;
    }

    @Override // android.view.View
    public int getForegroundGravity() {
        return this.foregroundGravity;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable r0 = this.foreground;
        if (r0 == null) goto L6;
        r0.jumpToCurrentState();
        return;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        this.foregroundBoundsChanged = r1 | this.foregroundBoundsChanged;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        this.foregroundBoundsChanged = true;
    }

    @Override // android.view.View
    public void setForeground(Drawable r3) {
        Drawable r0 = this.foreground;
        if (r0 == r3) goto L17;
        if (r0 == null) goto L6;
        r0.setCallback(null);
        unscheduleDrawable(this.foreground);
    L6:
        this.foreground = r3;
        this.foregroundBoundsChanged = true;
        if (r3 == null) goto L14;
        setWillNotDraw(false);
        r3.setCallback(this);
        if (r3.isStateful() == false) goto L12;
        r3.setState(getDrawableState());
    L12:
        if (this.foregroundGravity != 119) goto L15;
        r3.getPadding(new Rect());
    L15:
        requestLayout();
        invalidate();
        return;
    L14:
        setWillNotDraw(true);
        goto L15
    }

    @Override // android.view.View
    public void setForegroundGravity(int r2) {
        if (this.foregroundGravity != r2) goto L5;
        return;
    L5:
        if ((8388615 & r2) != 0) goto L8;
        r2 = r2 | PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
    L8:
        if ((r2 & 112) != 0) goto L10;
        r2 = r2 | 48;
    L10:
        this.foregroundGravity = r2;
        if (r2 == 119) goto L13;
    L15:
        requestLayout();
        return;
    L13:
        if (this.foreground == null) goto L15;
        Rect r22 = new Rect();
        this.foreground.getPadding(r22);
        goto L15
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.foreground) goto L11;
        return false;
    L11:
        return true;
    }
}
