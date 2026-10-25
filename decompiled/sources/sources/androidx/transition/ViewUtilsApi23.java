package androidx.transition;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

/* loaded from: classes4.dex */
class ViewUtilsApi23 extends ViewUtilsApi22 {
    private static boolean sTryHiddenSetTransitionVisibility = true;

    public static class Api29Impl {
        public static void setTransitionVisibility(View r0, int r1) {
            r0.setTransitionVisibility(r1);
        }
    }

    static {
    }

    public ViewUtilsApi23() {
    }

    @Override // androidx.transition.ViewUtilsApi19
    @SuppressLint({"NewApi"})
    public void setTransitionVisibility(View r3, int r4) {
        if (Build.VERSION.SDK_INT != 28) goto L7;
        super.setTransitionVisibility(r3, r4);
        return;
    L7:
        if (sTryHiddenSetTransitionVisibility == false) goto L14;
        Api29Impl.setTransitionVisibility(r3, r4);     // Catch: NoSuchMethodError -> L10
        return;
    L10:
        sTryHiddenSetTransitionVisibility = false;
        return;
    }
}
