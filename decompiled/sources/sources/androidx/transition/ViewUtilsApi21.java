package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;

/* loaded from: classes4.dex */
abstract class ViewUtilsApi21 extends ViewUtilsApi19 {
    private static boolean sTryHiddenTransformMatrixToGlobal = true;
    private static boolean sTryHiddenTransformMatrixToLocal = true;

    public static class Api29Impl {
        public static void setAnimationMatrix(View r0, Matrix r1) {
            r0.setAnimationMatrix(r1);
        }

        public static void transformMatrixToGlobal(View r0, Matrix r1) {
            r0.transformMatrixToGlobal(r1);
        }

        public static void transformMatrixToLocal(View r0, Matrix r1) {
            r0.transformMatrixToLocal(r1);
        }
    }

    static {
    }

    public ViewUtilsApi21() {
    }

    @Override // androidx.transition.ViewUtilsApi19
    @SuppressLint({"NewApi"})
    public void transformMatrixToGlobal(View r1, Matrix r2) {
        if (sTryHiddenTransformMatrixToGlobal == false) goto L10;
        Api29Impl.transformMatrixToGlobal(r1, r2);     // Catch: NoSuchMethodError -> L6
        return;
    L6:
        sTryHiddenTransformMatrixToGlobal = false;
        return;
    }

    @Override // androidx.transition.ViewUtilsApi19
    @SuppressLint({"NewApi"})
    public void transformMatrixToLocal(View r1, Matrix r2) {
        if (sTryHiddenTransformMatrixToLocal == false) goto L10;
        Api29Impl.transformMatrixToLocal(r1, r2);     // Catch: NoSuchMethodError -> L6
        return;
    L6:
        sTryHiddenTransformMatrixToLocal = false;
        return;
    }
}
