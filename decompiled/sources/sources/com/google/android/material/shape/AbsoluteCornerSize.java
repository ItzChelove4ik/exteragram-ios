package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class AbsoluteCornerSize implements CornerSize {
    private final float size;

    public AbsoluteCornerSize(float r1) {
        this.size = r1;
    }

    public boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof AbsoluteCornerSize) == true) goto L9;
        return false;
    L9:
        if (this.size != ((AbsoluteCornerSize) r4).size) goto L11;
        return true;
    L11:
        return false;
    }

    public float getCornerSize() {
        return this.size;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF r1) {
        return this.size;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.size)});
    }

    public String toString() {
        return getCornerSize() + "px";
    }
}
