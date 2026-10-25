package androidx.vectordrawable.graphics.drawable;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

/* loaded from: classes4.dex */
abstract class VectorDrawableCommon extends Drawable {
    Drawable mDelegateDrawable;

    public VectorDrawableCommon() {
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme r1) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.applyTheme(r0, r1);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void clearColorFilter() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.clearColorFilter();
        return;
    L6:
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getCurrent();
    L7:
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getMinimumHeight();
    L7:
        return super.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getMinimumWidth();
    L7:
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getPadding(r2);
    L7:
        return super.getPadding(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getState();
    L7:
        return super.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getTransparentRegion();
    L7:
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.jumpToCurrentState(r0);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.setLevel(r2);
    L7:
        return super.onLevelChange(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.setChangingConfigurations(r2);
        return;
    L6:
        super.setChangingConfigurations(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(int r2, PorterDuff.Mode r3) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.setColorFilter(r2, r3);
        return;
    L6:
        super.setColorFilter(r2, r3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean r1) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.setFilterBitmap(r1);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float r1, float r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setHotspot(r0, r1, r2);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int r1, int r2, int r3, int r4) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setHotspotBounds(r0, r1, r2, r3, r4);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.setState(r2);
    L7:
        return super.setState(r2);
    }
}
