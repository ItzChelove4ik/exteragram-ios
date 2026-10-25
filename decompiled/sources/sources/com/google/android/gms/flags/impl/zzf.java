package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes4.dex */
public abstract class zzf {
    private static SharedPreferences zza;

    static {
    }

    public static SharedPreferences zza(Context r2) {
        monitor-enter(SharedPreferences.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = (SharedPreferences) com.google.android.gms.internal.flags.zzd.zza(new zze(r2));     // Catch: Throwable -> L7
    L9:
        SharedPreferences r22 = zza;     // Catch: Throwable -> L7
        monitor-exit(SharedPreferences.class);     // Catch: Throwable -> L7
        return r22;
    }
}
