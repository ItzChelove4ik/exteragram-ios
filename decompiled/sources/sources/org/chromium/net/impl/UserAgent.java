package org.chromium.net.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class UserAgent {
    private static final Object sLock = null;
    private static int sVersionCode;

    static {
        sLock = new Object();
    }

    private static void appendCronetVersion(StringBuilder r1) {
        r1.append(" Cronet/");
        r1.append(ImplVersion.getCronetVersion());
    }

    public static String from(Context r3) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r3.getPackageName());
        r0.append('/');
        r0.append(versionFromContext(r3));
        r0.append(" (Linux; U; Android ");
        r0.append(Build.VERSION.RELEASE);
        r0.append("; ");
        r0.append(Locale.getDefault().toString());
        String r1 = Build.MODEL;
        if (r1.length() <= 0) goto L5;
        r0.append("; ");
        r0.append(r1);
    L5:
        String r32 = Build.ID;
        if (r32.length() <= 0) goto L8;
        r0.append("; Build/");
        r0.append(r32);
    L8:
        r0.append(";");
        appendCronetVersion(r0);
        r0.append(')');
        return r0.toString();
    }

    private static int versionFromContext(Context r3) {
        Object r0 = sLock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (sVersionCode == 0) goto L20;
    L14:
        int r32 = sVersionCode;     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r32;
    L20:
        sVersionCode = r3.getPackageManager().getPackageInfo(r3.getPackageName(), 0).versionCode;     // Catch: Throwable -> L10 PackageManager.NameNotFoundException -> L12
    L13:
        throw new IllegalStateException("Cannot determine package version");     // Catch: Throwable -> L10
    }
}
