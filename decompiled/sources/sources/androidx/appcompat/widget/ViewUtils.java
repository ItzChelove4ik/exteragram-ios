package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public abstract class ViewUtils {
    static final boolean SDK_LEVEL_SUPPORTS_AUTOSIZE = false;
    private static Method sComputeFitSystemWindowsMethod;
    private static boolean sInitComputeFitSystemWindowsMethod;

    public static class Api29Impl {
        public static void computeFitSystemWindows(View r2, Rect r3, Rect r4) {
            Insets r22 = r2.computeSystemWindowInsets(new WindowInsets.Builder().setSystemWindowInsets(Insets.of(r3)).build(), r4).getSystemWindowInsets();
            r3.set(r22.left, r22.top, r22.right, r22.bottom);
        }
    }

    static {
        if (Build.VERSION.SDK_INT < 27) goto L5;
        boolean r0 = true;
    L6:
        SDK_LEVEL_SUPPORTS_AUTOSIZE = r0;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public static void computeFitSystemWindows(View r7, Rect r8, Rect r9) {
        if (Build.VERSION.SDK_INT < 29) goto L7;
        Api29Impl.computeFitSystemWindows(r7, r8, r9);
        return;
    L7:
        if (sInitComputeFitSystemWindowsMethod == true) goto L14;
        sInitComputeFitSystemWindowsMethod = true;
        Method r0 = View.class.getDeclaredMethod("computeFitSystemWindows", new Class[]{Rect.class, Rect.class});     // Catch: NoSuchMethodException -> L13
        sComputeFitSystemWindowsMethod = r0;     // Catch: NoSuchMethodException -> L13
        if (r0.isAccessible() == true) goto L14;
        sComputeFitSystemWindowsMethod.setAccessible(true);     // Catch: NoSuchMethodException -> L13
    L13:
        Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
    L14:
        Method r02 = sComputeFitSystemWindowsMethod;
        if (r02 == null) goto L25;
        r02.invoke(r7, new Object[]{r8, r9});     // Catch: Exception -> L18
        return;
    L18:
        e = move-exception;
        Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e);
        return;
    }

    public static boolean isLayoutRtl(View r1) {
        if (r1.getLayoutDirection() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    @SuppressLint({"BanUncheckedReflection"})
    public static void makeOptionalFitsSystemWindows(View r5) {
        Method r2 = r5.getClass().getMethod("makeOptionalFitsSystemWindows", null);     // Catch: IllegalAccessException -> L6 InvocationTargetException -> L8 NoSuchMethodException -> L14
        if (r2.isAccessible() == true) goto L10;
        r2.setAccessible(true);     // Catch: IllegalAccessException -> L6 InvocationTargetException -> L8 NoSuchMethodException -> L14
    L10:
        r2.invoke(r5, null);     // Catch: IllegalAccessException -> L6 InvocationTargetException -> L8 NoSuchMethodException -> L14
        return;
    L6:
        e = move-exception;
        Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e);
        return;
    L14:
        Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        return;
    L8:
        e = move-exception;
        Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e);
    }
}
