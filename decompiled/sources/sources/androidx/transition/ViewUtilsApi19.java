package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;

/* loaded from: classes4.dex */
abstract class ViewUtilsApi19 {
    private static boolean sTryHiddenTransitionAlpha = true;
    private static Field sViewFlagsField;
    private static boolean sViewFlagsFieldFetched;

    public static class Api29Impl {
        public static float getTransitionAlpha(View r0) {
            return r0.getTransitionAlpha();
        }

        public static void setTransitionAlpha(View r0, float r1) {
            r0.setTransitionAlpha(r1);
        }
    }

    static {
    }

    public ViewUtilsApi19() {
    }

    public void clearNonTransitionAlpha(View r1) {
    }

    @SuppressLint({"NewApi"})
    public float getTransitionAlpha(View r1) {
        if (sTryHiddenTransitionAlpha == false) goto L8;
        return Api29Impl.getTransitionAlpha(r1);
    L6:
        sTryHiddenTransitionAlpha = false;
    L8:
        return r1.getAlpha();
    }

    public void saveNonTransitionAlpha(View r1) {
    }

    @SuppressLint({"BanUncheckedReflection"})
    public abstract void setLeftTopRightBottom(View r1, int r2, int r3, int r4, int r5);

    @SuppressLint({"NewApi"})
    public void setTransitionAlpha(View r1, float r2) {
        if (sTryHiddenTransitionAlpha == true) goto L9;
    L7:
        r1.setAlpha(r2);
        return;
    L9:
        Api29Impl.setTransitionAlpha(r1, r2);     // Catch: NoSuchMethodError -> L6
        return;
    L6:
        sTryHiddenTransitionAlpha = false;
        goto L7
    }

    @SuppressLint({"SoonBlockedPrivateApi"})
    public void setTransitionVisibility(View r3, int r4) {
        if (sViewFlagsFieldFetched == false) goto L16;
    L9:
        Field r2 = sViewFlagsField;
        if (r2 == null) goto L18;
        int r22 = r2.getInt(r3);     // Catch: IllegalAccessException -> L13
        sViewFlagsField.setInt(r3, (r22 & (-13)) | r4);     // Catch: IllegalAccessException -> L13
        return;
    L19:
        return;
    L18:
        return;
    L16:
        Field r0 = View.class.getDeclaredField("mViewFlags");     // Catch: NoSuchFieldException -> L7
        sViewFlagsField = r0;     // Catch: NoSuchFieldException -> L7
        r0.setAccessible(true);     // Catch: NoSuchFieldException -> L7
    L8:
        sViewFlagsFieldFetched = true;
    L7:
        Log.i("ViewUtilsApi19", "fetchViewFlagsField: ");
        goto L8
    }

    public abstract void transformMatrixToGlobal(View r1, Matrix r2);

    public abstract void transformMatrixToLocal(View r1, Matrix r2);
}
