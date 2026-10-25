package com.google.android.gms.internal.mlkit_language_id_common;

/* loaded from: classes4.dex */
public abstract class zzlt {
    private static zzls zza;

    public static synchronized zzli zza(zzlc r3) {
        monitor-enter(zzlt.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zzls(null);     // Catch: Throwable -> L7
    L9:
        zzli r32 = (zzli) zza.get(r3);     // Catch: Throwable -> L7
        monitor-exit(zzlt.class);
        return r32;
    }

    public static synchronized zzli zzb(String r1) {
        monitor-enter(zzlt.class);
        zzli r12 = zza(zzlc.zzd(r1).zzd());     // Catch: Throwable -> L7
        monitor-exit(zzlt.class);
        return r12;
    L7:
        th = move-exception;
        throw th;
    }
}
