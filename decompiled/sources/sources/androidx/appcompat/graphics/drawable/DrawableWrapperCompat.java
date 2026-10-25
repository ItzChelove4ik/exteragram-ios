package androidx.appcompat.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

/* loaded from: classes3.dex */
public abstract class DrawableWrapperCompat extends Drawable implements Drawable.Callback {
    private Drawable mDrawable;

    public DrawableWrapperCompat(Drawable r1) {
        setDrawable(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r1) {
        this.mDrawable.draw(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return this.mDrawable.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.mDrawable.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.mDrawable.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect r1) {
        return this.mDrawable.getPadding(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.mDrawable.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.mDrawable.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return DrawableCompat.isAutoMirrored(this.mDrawable);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mDrawable.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        this.mDrawable.setBounds(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int r1) {
        return this.mDrawable.setLevel(r1);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        scheduleSelf(r2, r3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.mDrawable.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean r1) {
        DrawableCompat.setAutoMirrored(this.mDrawable, r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int r1) {
        this.mDrawable.setChangingConfigurations(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.mDrawable.setColorFilter(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean r1) {
        this.mDrawable.setDither(r1);
    }

    public void setDrawable(Drawable r3) {
        Drawable r0 = this.mDrawable;
        if (r0 == null) goto L5;
        r0.setCallback(null);
    L5:
        this.mDrawable = r3;
        if (r3 == null) goto L9;
        r3.setCallback(this);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean r1) {
        this.mDrawable.setFilterBitmap(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float r1, float r2) {
        DrawableCompat.setHotspot(this.mDrawable, r1, r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int r1, int r2, int r3, int r4) {
        DrawableCompat.setHotspotBounds(this.mDrawable, r1, r2, r3, r4);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] r1) {
        return this.mDrawable.setState(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int r1) {
        DrawableCompat.setTint(this.mDrawable, r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList r1) {
        DrawableCompat.setTintList(this.mDrawable, r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode r1) {
        DrawableCompat.setTintMode(this.mDrawable, r1);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean r2, boolean r3) {
        if (super.setVisible(r2, r3) == false) goto L5;
        return true;
    L5:
        if (this.mDrawable.setVisible(r2, r3) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
        unscheduleSelf(r2);
    }
}
