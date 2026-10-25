package androidx.transition;

import android.annotation.SuppressLint;
import android.view.View;

/* loaded from: classes4.dex */
abstract class ViewUtilsApi22 extends ViewUtilsApi21 {
    private static boolean sTryHiddenSetLeftTopRightBottom = true;

    public static class Api29Impl {
        public static void setLeftTopRightBottom(View r0, int r1, int r2, int r3, int r4) {
            r0.setLeftTopRightBottom(r1, r2, r3, r4);
        }
    }

    static {
    }

    public ViewUtilsApi22() {
    }

    @Override // androidx.transition.ViewUtilsApi19
    @SuppressLint({"NewApi"})
    public void setLeftTopRightBottom(View r1, int r2, int r3, int r4, int r5) {
        if (sTryHiddenSetLeftTopRightBottom == false) goto L10;
        Api29Impl.setLeftTopRightBottom(r1, r2, r3, r4, r5);     // Catch: NoSuchMethodError -> L6
        return;
    L6:
        sTryHiddenSetLeftTopRightBottom = false;
        return;
    }
}
