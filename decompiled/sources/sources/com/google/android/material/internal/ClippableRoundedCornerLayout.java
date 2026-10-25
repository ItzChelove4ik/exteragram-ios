package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* loaded from: classes5.dex */
public class ClippableRoundedCornerLayout extends FrameLayout {
    private float[] cornerRadii;
    private Path path;

    public ClippableRoundedCornerLayout(Context r1) {
        super(r1);
        this.cornerRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public ClippableRoundedCornerLayout(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.cornerRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public ClippableRoundedCornerLayout(Context r1, AttributeSet r2, int r3) {
        super(r1, r2, r3);
        this.cornerRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r3) {
        if (this.path != null) goto L6;
        super.dispatchDraw(r3);
        return;
    L6:
        int r0 = r3.save();
        r3.clipPath(this.path);
        super.dispatchDraw(r3);
        r3.restoreToCount(r0);
    }

    public float[] getCornerRadii() {
        return this.cornerRadii;
    }

    public void resetClipBoundsAndCornerRadii() {
        this.path = null;
        this.cornerRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        invalidate();
    }

    public void updateClipBoundsAndCornerRadii(float r2, float r3, float r4, float r5, float[] r6) {
        updateClipBoundsAndCornerRadii(new RectF(r2, r3, r4, r5), r6);
    }

    public void updateClipBoundsAndCornerRadii(Rect r8, float[] r9) {
        updateClipBoundsAndCornerRadii(r8.left, r8.top, r8.right, r8.bottom, r9);
    }

    public void updateClipBoundsAndCornerRadii(RectF r3, float[] r4) {
        if (this.path != null) goto L5;
        this.path = new Path();
    L5:
        this.cornerRadii = r4;
        this.path.reset();
        this.path.addRoundRect(r3, r4, Path.Direction.CW);
        this.path.close();
        invalidate();
    }

    public void updateCornerRadii(float[] r8) {
        updateClipBoundsAndCornerRadii(getLeft(), getTop(), getRight(), getBottom(), r8);
    }
}
