package androidx.transition;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.ViewGroup;

/* loaded from: classes4.dex */
abstract class ViewGroupUtils {
    private static boolean sTryHiddenSuppressLayout = true;

    public static class Api29Impl {
        public static int getChildDrawingOrder(ViewGroup r0, int r1) {
            return r0.getChildDrawingOrder(r1);
        }

        public static void suppressLayout(ViewGroup r0, boolean r1) {
            r0.suppressLayout(r1);
        }
    }

    static {
    }

    @SuppressLint({"NewApi"})
    private static void hiddenSuppressLayout(ViewGroup r1, boolean r2) {
        if (sTryHiddenSuppressLayout == false) goto L10;
        Api29Impl.suppressLayout(r1, r2);     // Catch: NoSuchMethodError -> L6
        return;
    L6:
        sTryHiddenSuppressLayout = false;
        return;
    }

    public static void suppressLayout(ViewGroup r2, boolean r3) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        Api29Impl.suppressLayout(r2, r3);
        return;
    L6:
        hiddenSuppressLayout(r2, r3);
    }
}
