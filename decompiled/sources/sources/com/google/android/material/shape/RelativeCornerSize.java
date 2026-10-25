package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class RelativeCornerSize implements CornerSize {
    private final float percent;

    public RelativeCornerSize(float r1) {
        this.percent = r1;
    }

    public static RelativeCornerSize createFromCornerSize(RectF r1, CornerSize r2) {
        if ((r2 instanceof RelativeCornerSize) == false) goto L7;
        return (RelativeCornerSize) r2;
    L7:
        return new RelativeCornerSize(r2.getCornerSize(r1) / getMaxCornerSize(r1));
    }

    private static float getMaxCornerSize(RectF r1) {
        return Math.min(r1.width(), r1.height());
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof RelativeCornerSize) == true) goto L9;
        return false;
    L9:
        if (this.percent != ((RelativeCornerSize) r4).percent) goto L11;
        return true;
    L11:
        return false;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF r1) {
        return this.percent * getMaxCornerSize(r1);
    }

    public float getRelativePercent() {
        return this.percent;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.percent)});
    }

    public String toString() {
        return ((int) (getRelativePercent() * 100.0f)) + "%";
    }
}
