package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;

/* loaded from: classes4.dex */
public abstract class InstantApps {
    private static Context zza;
    private static Boolean zzb;

    public static synchronized boolean isInstantApp(Context r4) {
        monitor-enter(InstantApps.class);
        Context r1 = r4.getApplicationContext();     // Catch: Throwable -> L13
        Context r2 = zza;     // Catch: Throwable -> L13
        if (r2 == null) goto L16;
        Boolean r3 = zzb;     // Catch: Throwable -> L13
        if (r3 == null) goto L16;
        if (r2 != r1) goto L16;
        boolean r42 = r3.booleanValue();     // Catch: Throwable -> L13
        monitor-exit(InstantApps.class);
        return r42;
    L16:
        zzb = null;     // Catch: Throwable -> L13
        if (PlatformVersion.isAtLeastO() == false) goto L29;
        zzb = Boolean.valueOf(r1.getPackageManager().isInstantApp());     // Catch: Throwable -> L13
    L23:
        zza = r1;     // Catch: Throwable -> L13
        boolean r43 = zzb.booleanValue();     // Catch: Throwable -> L13
        monitor-exit(InstantApps.class);
        return r43;
    L29:
        r4.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");     // Catch: Throwable -> L13 ClassNotFoundException -> L22
        zzb = Boolean.TRUE;     // Catch: Throwable -> L13 ClassNotFoundException -> L22
    L22:
        zzb = Boolean.FALSE;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        throw th;
    }
}
