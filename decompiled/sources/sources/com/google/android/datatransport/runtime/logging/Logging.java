package com.google.android.datatransport.runtime.logging;

import android.os.Build;
import android.util.Log;

/* loaded from: classes4.dex */
public abstract class Logging {
    private static String concatTag(String r1, String r2) {
        String r12 = r1 + r2;
        if (r12.length() > 23) goto L5;
        return r12;
    L5:
        return r12.substring(0, 23);
    }

    public static void d(String r1, String r2, Object r3) {
        String r12 = getTag(r1);
        if (Log.isLoggable(r12, 3) == false) goto L6;
        Log.d(r12, String.format(r2, new Object[]{r3}));
        return;
    }

    public static void d(String r1, String r2, Object... r3) {
        String r12 = getTag(r1);
        if (Log.isLoggable(r12, 3) == false) goto L6;
        Log.d(r12, String.format(r2, r3));
        return;
    }

    public static void e(String r1, String r2, Throwable r3) {
        String r12 = getTag(r1);
        if (Log.isLoggable(r12, 6) == false) goto L6;
        Log.e(r12, r2, r3);
        return;
    }

    private static String getTag(String r3) {
        if (Build.VERSION.SDK_INT >= 26) goto L7;
        return concatTag("TRuntime.", r3);
    L7:
        return "TRuntime." + r3;
    }

    public static void i(String r1, String r2, Object r3) {
        String r12 = getTag(r1);
        if (Log.isLoggable(r12, 4) == false) goto L6;
        Log.i(r12, String.format(r2, new Object[]{r3}));
        return;
    }

    public static void w(String r1, String r2, Object r3) {
        String r12 = getTag(r1);
        if (Log.isLoggable(r12, 5) == false) goto L6;
        Log.w(r12, String.format(r2, new Object[]{r3}));
        return;
    }
}
