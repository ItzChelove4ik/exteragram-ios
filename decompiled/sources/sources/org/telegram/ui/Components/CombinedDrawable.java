package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
public class CombinedDrawable extends Drawable implements Drawable.Callback {
    private int backHeight;
    private int backWidth;
    private Drawable background;
    private boolean both;
    private boolean center;
    private boolean fullSize;
    private Drawable icon;
    private int iconHeight;
    private int iconWidth;
    private int left;
    private int offsetX;
    private int offsetY;
    private int top;
    public float translateX;
    public float translateY;

    public CombinedDrawable(Drawable r1, Drawable r2) {
        this.background = r1;
        this.icon = r2;
        if (r2 == null) goto L6;
        r2.setCallback(this);
        return;
    }

    public CombinedDrawable(Drawable r1, Drawable r2, int r3, int r4) {
        this.background = r1;
        this.icon = r2;
        this.left = r3;
        this.top = r4;
        if (r2 == null) goto L6;
        r2.setCallback(this);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r8) {
        r8.save();
        r8.translate(this.translateX, this.translateY);
        if (this.center == false) goto L5;
        Rect r0 = getBounds();
        setBounds(r0.centerX() - (getIntrinsicWidth() / 2), r0.centerY() - (getIntrinsicHeight() / 2), r0.centerX() + (getIntrinsicWidth() / 2), r0.centerY() + (getIntrinsicHeight() / 2));
    L5:
        Drawable r02 = this.background;
        if (r02 == null) goto L9;
        r02.setBounds(getBounds());
        this.background.draw(r8);
    L9:
        if (this.icon != null) goto L11;
    L21:
        r8.restore();
        return;
    L11:
        if (this.fullSize == false) goto L17;
        Rect r03 = getBounds();
        int r1 = this.left;
        Drawable r2 = this.icon;
        if (r1 == 0) goto L15;
        int r3 = r03.left + r1;
        int r4 = r03.top;
        int r5 = this.top;
        r2.setBounds(r3, r4 + r5, r03.right - r1, r03.bottom - r5);
    L20:
        this.icon.draw(r8);
        goto L21
    L15:
        r2.setBounds(r03);
        goto L20
    L17:
        if (this.iconWidth == 0) goto L19;
        int r04 = ((getBounds().centerX() - (this.iconWidth / 2)) + this.left) + this.offsetX;
        int r12 = getBounds().centerY();
        int r22 = this.iconHeight;
        int r13 = ((r12 - (r22 / 2)) + this.top) + this.offsetY;
        this.icon.setBounds(r04, r13, this.iconWidth + r04, r22 + r13);
        goto L20
    L19:
        int r05 = (getBounds().centerX() - (this.icon.getIntrinsicWidth() / 2)) + this.left;
        int r14 = (getBounds().centerY() - (this.icon.getIntrinsicHeight() / 2)) + this.top;
        Drawable r23 = this.icon;
        r23.setBounds(r05, r14, r23.getIntrinsicWidth() + r05, this.icon.getIntrinsicHeight() + r14);
        goto L20
    }

    public Drawable getBackground() {
        return this.background;
    }

    public Drawable getBackgroundDrawable() {
        return this.background;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.icon.getConstantState();
    }

    public Drawable getIcon() {
        return this.icon;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int r0 = this.backHeight;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.background.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int r0 = this.backWidth;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.background.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        int r0 = this.backHeight;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.background.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        int r0 = this.backWidth;
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.background.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.icon.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.icon.getState();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.icon.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.icon.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] r1) {
        return true;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        scheduleSelf(r2, r3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.icon.setAlpha(r2);
        this.background.setAlpha(r2);
    }

    public void setBackgroundDrawable(Drawable r1) {
        this.background = r1;
        invalidateSelf();
    }

    public void setCenter(boolean r1) {
        this.center = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.icon.setColorFilter(r2);
        if (this.both == false) goto L6;
        this.background.setColorFilter(r2);
        return;
    }

    public void setCustomSize(int r1, int r2) {
        this.backWidth = r1;
        this.backHeight = r2;
    }

    public void setFullsize(boolean r1) {
        this.fullSize = r1;
    }

    public void setIconOffset(int r1, int r2) {
        this.offsetX = r1;
        this.offsetY = r2;
    }

    public CombinedDrawable setIconSize(int r1, int r2) {
        this.iconWidth = r1;
        this.iconHeight = r2;
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] r1) {
        this.icon.setState(r1);
        return true;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
        unscheduleSelf(r2);
    }
}
