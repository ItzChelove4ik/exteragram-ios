package com.google.android.gms.common.util;

import android.os.Build;

/* loaded from: classes.dex */
public abstract class PlatformVersion {
    @Deprecated
    public static boolean isAtLeastLollipop() {
        return true;
    }

    public static boolean isAtLeastN() {
        return true;
    }

    public static boolean isAtLeastO() {
        if (Build.VERSION.SDK_INT < 26) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isAtLeastP() {
        if (Build.VERSION.SDK_INT < 28) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isAtLeastQ() {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isAtLeastR() {
        if (Build.VERSION.SDK_INT < 30) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isAtLeastS() {
        if (Build.VERSION.SDK_INT < 31) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isAtLeastT() {
        if (Build.VERSION.SDK_INT < 33) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isAtLeastU() {
        if (Build.VERSION.SDK_INT < 34) goto L6;
        return true;
    L6:
        return false;
    }
}
