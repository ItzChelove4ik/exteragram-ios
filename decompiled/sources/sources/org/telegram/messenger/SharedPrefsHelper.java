package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Iterator;

/* loaded from: classes.dex */
public class SharedPrefsHelper {
    private static String WEB_VIEW_SHOWN_DIALOG_FORMAT = "confirm_shown_%d_%d";
    private static SharedPreferences webViewBotsPrefs;

    static {
    }

    public SharedPrefsHelper() {
    }

    public static void cleanupAccount(int r5) {
        SharedPreferences r0 = webViewBotsPrefs;
        if (r0 == null) goto L17;
        SharedPreferences.Editor r02 = r0.edit();
        Iterator<String> r1 = webViewBotsPrefs.getAll().keySet().iterator();
    L6:
        if (r1.hasNext() == false) goto L10;
        String r2 = r1.next();
        if (r2.startsWith("confirm_shown_" + r5 + "_") == false) goto L6;
        r02.remove(r2);
        goto L6
    L10:
        r02.apply();
        return;
    }

    public static SharedPreferences getWebViewBotsPrefs() {
        return webViewBotsPrefs;
    }

    public static void init(Context r2) {
        webViewBotsPrefs = r2.getSharedPreferences("webview_bots", 0);
    }

    public static boolean isWebViewConfirmShown(int r2, long r3) {
        return webViewBotsPrefs.getBoolean(String.format(WEB_VIEW_SHOWN_DIALOG_FORMAT, new Object[]{Integer.valueOf(r2), Long.valueOf(r3)}), false);
    }

    public static void setWebViewConfirmShown(int r2, long r3, boolean r5) {
        webViewBotsPrefs.edit().putBoolean(String.format(WEB_VIEW_SHOWN_DIALOG_FORMAT, new Object[]{Integer.valueOf(r2), Long.valueOf(r3)}), r5).apply();
    }
}
