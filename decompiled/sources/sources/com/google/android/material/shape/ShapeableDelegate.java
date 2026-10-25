package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import com.google.android.material.canvas.CanvasCompat;

/* loaded from: classes5.dex */
public abstract class ShapeableDelegate {
    boolean forceCompatClippingEnabled;
    RectF maskBounds;
    boolean offsetZeroCornerEdgeBoundsEnabled;
    ShapeAppearanceModel shapeAppearanceModel;
    final Path shapePath;

    public ShapeableDelegate() {
        this.forceCompatClippingEnabled = false;
        this.offsetZeroCornerEdgeBoundsEnabled = false;
        this.maskBounds = new RectF();
        this.shapePath = new Path();
    }

    public static ShapeableDelegate create(View r2) {
        if (Build.VERSION.SDK_INT < 33) goto L7;
        return new ShapeableDelegateV33(r2);
    L7:
        return new ShapeableDelegateV22(r2);
    }

    private boolean isMaskBoundsValid() {
        RectF r2 = this.maskBounds;
        if (r2.left <= r2.right) goto L5;
        return false;
    L5:
        if (r2.top > r2.bottom) goto L10;
        return true;
    L10:
        return false;
    }

    private void updateShapePath() {
        if (isMaskBoundsValid() == true) goto L5;
        return;
    L5:
        if (this.shapeAppearanceModel == null) goto L9;
        ShapeAppearancePathProvider.getInstance().calculatePath(this.shapeAppearanceModel, 1.0f, this.maskBounds, this.shapePath);
        return;
    }

    public abstract void invalidateClippingMethod(View r1);

    public boolean isForceCompatClippingEnabled() {
        return this.forceCompatClippingEnabled;
    }

    public void maybeClip(Canvas r2, CanvasCompat.CanvasOperation r3) {
        if (shouldUseCompatClipping() == true) goto L5;
    L8:
        r3.run(r2);
        return;
    L5:
        if (this.shapePath.isEmpty() == true) goto L8;
        r2.save();
        r2.clipPath(this.shapePath);
        r3.run(r2);
        r2.restore();
    }

    public void onMaskChanged(View r1, RectF r2) {
        this.maskBounds = r2;
        updateShapePath();
        invalidateClippingMethod(r1);
    }

    public void onShapeAppearanceChanged(View r1, ShapeAppearanceModel r2) {
        this.shapeAppearanceModel = r2;
        updateShapePath();
        invalidateClippingMethod(r1);
    }

    public void setForceCompatClippingEnabled(View r2, boolean r3) {
        if (r3 == this.forceCompatClippingEnabled) goto L6;
        this.forceCompatClippingEnabled = r3;
        invalidateClippingMethod(r2);
        return;
    }

    public void setOffsetZeroCornerEdgeBoundsEnabled(View r1, boolean r2) {
        this.offsetZeroCornerEdgeBoundsEnabled = r2;
        invalidateClippingMethod(r1);
    }

    public abstract boolean shouldUseCompatClipping();
}
