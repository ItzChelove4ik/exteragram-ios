package androidx.core.graphics;

import android.graphics.Rect;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline0;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline1;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline2;
import androidx.appcompat.widget.DrawableUtils$$ExternalSyntheticApiModelOutline3;

/* loaded from: classes.dex */
public final class Insets {
    public static final Insets NONE = null;
    public final int bottom;
    public final int left;
    public final int right;
    public final int top;

    /* loaded from: classes3.dex */
    public static class Api29Impl {
        public static android.graphics.Insets of(int r0, int r1, int r2, int r3) {
            return android.graphics.Insets.of(r0, r1, r2, r3);
        }
    }

    static {
        NONE = new Insets(0, 0, 0, 0);
    }

    private Insets(int r1, int r2, int r3, int r4) {
        this.left = r1;
        this.top = r2;
        this.right = r3;
        this.bottom = r4;
    }

    public static Insets max(Insets r4, Insets r5) {
        return of(Math.max(r4.left, r5.left), Math.max(r4.top, r5.top), Math.max(r4.right, r5.right), Math.max(r4.bottom, r5.bottom));
    }

    public static Insets min(Insets r4, Insets r5) {
        return of(Math.min(r4.left, r5.left), Math.min(r4.top, r5.top), Math.min(r4.right, r5.right), Math.min(r4.bottom, r5.bottom));
    }

    public static Insets of(int r1, int r2, int r3, int r4) {
        if (r1 != 0) goto L9;
        if (r2 != 0) goto L9;
        if (r3 != 0) goto L9;
        if (r4 != 0) goto L9;
        return NONE;
    L9:
        return new Insets(r1, r2, r3, r4);
    }

    public static Insets of(Rect r3) {
        return of(r3.left, r3.top, r3.right, r3.bottom);
    }

    public static Insets toCompatInsets(android.graphics.Insets r3) {
        return of(DrawableUtils$$ExternalSyntheticApiModelOutline0.m(r3), DrawableUtils$$ExternalSyntheticApiModelOutline1.m(r3), DrawableUtils$$ExternalSyntheticApiModelOutline2.m(r3), DrawableUtils$$ExternalSyntheticApiModelOutline3.m(r3));
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L23:
        return false;
    L8:
        if (Insets.class != r5.getClass()) goto L23;
        Insets r52 = (Insets) r5;
        if (this.bottom == r52.bottom) goto L14;
        return false;
    L14:
        if (this.left == r52.left) goto L17;
        return false;
    L17:
        if (this.right == r52.right) goto L20;
        return false;
    L20:
        if (this.top == r52.top) goto L22;
        return false;
    L22:
        return true;
    }

    public int hashCode() {
        return (((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom;
    }

    public android.graphics.Insets toPlatformInsets() {
        return Api29Impl.of(this.left, this.top, this.right, this.bottom);
    }

    public String toString() {
        return "Insets{left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + '}';
    }
}
