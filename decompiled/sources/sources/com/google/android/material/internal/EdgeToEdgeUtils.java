package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.WindowCompat;
import com.google.android.material.color.MaterialColors;

/* loaded from: classes5.dex */
public class EdgeToEdgeUtils {
    private static final int EDGE_TO_EDGE_BAR_ALPHA = 128;

    private EdgeToEdgeUtils() {
    }

    public static void applyEdgeToEdge(Window r1, boolean r2) {
        applyEdgeToEdge(r1, r2, null, null);
    }

    public static void applyEdgeToEdge(Window r5, boolean r6, Integer r7, Integer r8) {
        boolean r0 = false;
        if (r7 != null) goto L5;
    L8:
        boolean r2 = true;
    L9:
        if (r8 != null) goto L11;
    L12:
        r0 = true;
    L13:
        if (r2 == true) goto L15;
        if (r0 == true) goto L15;
    L20:
        WindowCompat.setDecorFitsSystemWindows(r5, !r6);
        int r02 = getStatusBarColor(r5.getContext(), r6);
        int r62 = getNavigationBarColor(r5.getContext(), r6);
        setStatusBarColor(r5, r02);
        setNavigationBarColor(r5, r62);
        setLightStatusBar(r5, isUsingLightSystemBar(r02, MaterialColors.isColorLight(r7.intValue())));
        setLightNavigationBar(r5, isUsingLightSystemBar(r62, MaterialColors.isColorLight(r8.intValue())));
        return;
    L15:
        int r1 = MaterialColors.getColor(r5.getContext(), R.attr.colorBackground, -16777216);
        if (r2 == false) goto L18;
        r7 = Integer.valueOf(r1);
    L18:
        if (r0 == false) goto L20;
        r8 = Integer.valueOf(r1);
        goto L20
    L11:
        if (r8.intValue() != 0) goto L13;
    L5:
        if (r7.intValue() == 0) goto L8;
        r2 = false;
        goto L9
    }

    private static int getNavigationBarColor(Context r4, boolean r5) {
        if (r5 == true) goto L5;
    L8:
        if (r5 == false) goto L12;
        return 0;
    L12:
        return MaterialColors.getColor(r4, R.attr.navigationBarColor, -16777216);
    L5:
        if (Build.VERSION.SDK_INT >= 27) goto L8;
        return ColorUtils.setAlphaComponent(MaterialColors.getColor(r4, R.attr.navigationBarColor, -16777216), 128);
    }

    public static int getNavigationBarColor(Window r2) {
        if (Build.VERSION.SDK_INT < 35) goto L5;
        return 0;
    L5:
        return r2.getNavigationBarColor();
    }

    private static int getStatusBarColor(Context r1, boolean r2) {
        if (r2 == false) goto L6;
        return 0;
    L6:
        return MaterialColors.getColor(r1, R.attr.statusBarColor, -16777216);
    }

    private static boolean isUsingLightSystemBar(int r1, boolean r2) {
        if (MaterialColors.isColorLight(r1) == true) goto L9;
        if (r1 != 0) goto L7;
        if (r2 == true) goto L12;
        return false;
    L12:
        return true;
    L7:
        return false;
    L9:
        return true;
    }

    public static void setLightNavigationBar(Window r1, boolean r2) {
        WindowCompat.getInsetsController(r1, r1.getDecorView()).setAppearanceLightNavigationBars(r2);
    }

    public static void setLightStatusBar(Window r1, boolean r2) {
        WindowCompat.getInsetsController(r1, r1.getDecorView()).setAppearanceLightStatusBars(r2);
    }

    public static void setNavigationBarColor(Window r2, int r3) {
        if (Build.VERSION.SDK_INT >= 35) goto L6;
        r2.setNavigationBarColor(r3);
        return;
    }

    public static void setStatusBarColor(Window r2, int r3) {
        if (Build.VERSION.SDK_INT >= 35) goto L6;
        r2.setStatusBarColor(r3);
        return;
    }
}
