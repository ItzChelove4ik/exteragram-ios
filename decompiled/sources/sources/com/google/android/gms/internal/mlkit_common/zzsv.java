package com.google.android.gms.internal.mlkit_common;

/* loaded from: classes4.dex */
public final class zzsv {
    private static zzsv zza;

    private zzsv() {
    }

    public static synchronized zzsv zza() {
        monitor-enter(zzsv.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zzsv();     // Catch: Throwable -> L7
    L9:
        zzsv r1 = zza;     // Catch: Throwable -> L7
        monitor-exit(zzsv.class);
        return r1;
    }

    public static void zzb() {
        zzsu.zza();
    }
}
