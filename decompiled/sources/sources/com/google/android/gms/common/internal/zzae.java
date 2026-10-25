package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;

/* loaded from: classes.dex */
public abstract class zzae {
    private static final Object zza = null;
    private static boolean zzb;
    private static String zzc;
    private static int zzd;

    static {
        zza = new Object();
    }

    public static int zzb(Context r0) {
        zzc(r0);
        return zzd;
    }

    private static void zzc(Context r3) {
        Object r0 = zza;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (zzb == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        zzb = true;     // Catch: Throwable -> L8
        String r1 = r3.getPackageName();     // Catch: Throwable -> L8
        Bundle r32 = Wrappers.packageManager(r3).getApplicationInfo(r1, 128).metaData;     // Catch: Throwable -> L8 PackageManager.NameNotFoundException -> L18
        if (r32 != null) goto L16;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L16:
        zzc = r32.getString("com.google.app.id");     // Catch: Throwable -> L8 PackageManager.NameNotFoundException -> L18
        zzd = r32.getInt("com.google.android.gms.version");     // Catch: Throwable -> L8 PackageManager.NameNotFoundException -> L18
    L20:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L18:
        e = move-exception;
        Log.wtf("MetadataValueReader", "This should never happen.", e);     // Catch: Throwable -> L8
        goto L20
    }
}
