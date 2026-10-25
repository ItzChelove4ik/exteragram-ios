package com.google.android.gms.internal.clearcut;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.UserManager;

/* loaded from: classes4.dex */
public abstract class zzaa {
    private static volatile UserManager zzdc;
    private static volatile boolean zzdd;

    static {
        zzdd = !zzf();
    }

    public static boolean zze(Context r1) {
        if (zzf() == true) goto L5;
        return false;
    L5:
        if (zzf(r1) == true) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean zzf() {
        return true;
    }

    @TargetApi(24)
    private static boolean zzf(Context r2) {
        boolean r0 = zzdd;
        if (r0 == true) goto L26;
        UserManager r02 = zzdc;
        if (r02 == null) goto L7;
    L22:
        boolean r22 = r02.isUserUnlocked();
        zzdd = r22;
        if (r22 == false) goto L25;
        zzdc = null;
    L25:
        return r22;
    L7:
        monitor-enter(zzaa.class);
        r02 = zzdc;     // Catch: Throwable -> L15
        if (r02 != null) goto L18;
        UserManager r23 = (UserManager) r2.getSystemService(UserManager.class);     // Catch: Throwable -> L15
        zzdc = r23;     // Catch: Throwable -> L15
        if (r23 != null) goto L17;
        zzdd = true;     // Catch: Throwable -> L15
        monitor-exit(zzaa.class);     // Catch: Throwable -> L15
        return true;
    L17:
        r02 = r23;
    L18:
        monitor-exit(zzaa.class);     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        throw th;
    L26:
        return r0;
    }
}
