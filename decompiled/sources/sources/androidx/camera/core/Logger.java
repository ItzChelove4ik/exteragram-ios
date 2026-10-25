package androidx.camera.core;

import android.os.Build;
import android.util.Log;

/* loaded from: classes3.dex */
public abstract class Logger {
    private static int sMinLogLevel = 3;

    static {
    }

    public static void d(String r1, String r2) {
        String r12 = truncateTag(r1);
        if (isLogLevelEnabled(r12, 3) == false) goto L6;
        Log.d(r12, r2);
        return;
    }

    public static void d(String r1, String r2, Throwable r3) {
        String r12 = truncateTag(r1);
        if (isLogLevelEnabled(r12, 3) == false) goto L6;
        Log.d(r12, r2, r3);
        return;
    }

    public static void e(String r1, String r2) {
        String r12 = truncateTag(r1);
        if (isLogLevelEnabled(r12, 6) == false) goto L6;
        Log.e(r12, r2);
        return;
    }

    public static void e(String r1, String r2, Throwable r3) {
        String r12 = truncateTag(r1);
        if (isLogLevelEnabled(r12, 6) == false) goto L6;
        Log.e(r12, r2, r3);
        return;
    }

    public static void i(String r1, String r2) {
        String r12 = truncateTag(r1);
        if (isLogLevelEnabled(r12, 4) == false) goto L6;
        Log.i(r12, r2);
        return;
    }

    public static boolean isDebugEnabled(String r1) {
        return isLogLevelEnabled(truncateTag(r1), 3);
    }

    public static boolean isErrorEnabled(String r1) {
        return isLogLevelEnabled(truncateTag(r1), 6);
    }

    public static boolean isInfoEnabled(String r1) {
        return isLogLevelEnabled(truncateTag(r1), 4);
    }

    private static boolean isLogLevelEnabled(String r1, int r2) {
        if (sMinLogLevel > r2) goto L5;
        return true;
    L5:
        if (Log.isLoggable(r1, r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isVerboseEnabled(String r1) {
        return isLogLevelEnabled(truncateTag(r1), 2);
    }

    public static boolean isWarnEnabled(String r1) {
        return isLogLevelEnabled(truncateTag(r1), 5);
    }

    public static void resetMinLogLevel() {
        sMinLogLevel = 3;
    }

    public static void setMinLogLevel(int r0) {
        sMinLogLevel = r0;
    }

    private static String truncateTag(String r2) {
        if (Build.VERSION.SDK_INT <= 25) goto L5;
        return r2;
    L5:
        if (23 < r2.length()) goto L7;
        return r2;
    L7:
        return r2.substring(0, 23);
    }

    public static void w(String r1, String r2) {
        String r12 = truncateTag(r1);
        if (isLogLevelEnabled(r12, 5) == false) goto L6;
        Log.w(r12, r2);
        return;
    }

    public static void w(String r1, String r2, Throwable r3) {
        String r12 = truncateTag(r1);
        if (isLogLevelEnabled(r12, 5) == false) goto L6;
        Log.w(r12, r2, r3);
        return;
    }
}
