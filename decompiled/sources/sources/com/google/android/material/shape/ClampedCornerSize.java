package com.google.android.material.shape;

import android.graphics.RectF;
import androidx.core.math.MathUtils;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class ClampedCornerSize implements CornerSize {
    private final float target;

    public ClampedCornerSize(float r1) {
        this.target = r1;
    }

    public static ClampedCornerSize createFromCornerSize(AbsoluteCornerSize r1) {
        return new ClampedCornerSize(r1.getCornerSize());
    }

    private static float getMaxCornerSize(RectF r2) {
        return Math.min(r2.width() / 2.0f, r2.height() / 2.0f);
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof ClampedCornerSize) == true) goto L9;
        return false;
    L9:
        if (this.target != ((ClampedCornerSize) r4).target) goto L11;
        return true;
    L11:
        return false;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF r2) {
        return MathUtils.clamp(this.target, 0.0f, getMaxCornerSize(r2));
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.target)});
    }
}
