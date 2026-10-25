package androidx.core.view;

import android.os.Build;
import android.view.DisplayCutout;
import androidx.core.util.ObjectsCompat;

/* loaded from: classes.dex */
public final class DisplayCutoutCompat {
    private final DisplayCutout mDisplayCutout;

    public static class Api28Impl {
        public static int getSafeInsetBottom(DisplayCutout r0) {
            return r0.getSafeInsetBottom();
        }

        public static int getSafeInsetLeft(DisplayCutout r0) {
            return r0.getSafeInsetLeft();
        }

        public static int getSafeInsetRight(DisplayCutout r0) {
            return r0.getSafeInsetRight();
        }

        public static int getSafeInsetTop(DisplayCutout r0) {
            return r0.getSafeInsetTop();
        }
    }

    private DisplayCutoutCompat(DisplayCutout r1) {
        this.mDisplayCutout = r1;
    }

    public static DisplayCutoutCompat wrap(DisplayCutout r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return new DisplayCutoutCompat(r1);
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if (r3 != null) goto L7;
        return false;
    L7:
        if (DisplayCutoutCompat.class == r3.getClass()) goto L10;
        return false;
    L10:
        return ObjectsCompat.equals(this.mDisplayCutout, ((DisplayCutoutCompat) r3).mDisplayCutout);
    }

    public int getSafeInsetBottom() {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return 0;
    L5:
        return Api28Impl.getSafeInsetBottom(this.mDisplayCutout);
    }

    public int getSafeInsetLeft() {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return 0;
    L5:
        return Api28Impl.getSafeInsetLeft(this.mDisplayCutout);
    }

    public int getSafeInsetRight() {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return 0;
    L5:
        return Api28Impl.getSafeInsetRight(this.mDisplayCutout);
    }

    public int getSafeInsetTop() {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return 0;
    L5:
        return Api28Impl.getSafeInsetTop(this.mDisplayCutout);
    }

    public int hashCode() {
        DisplayCutout r0 = this.mDisplayCutout;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
    }
}
