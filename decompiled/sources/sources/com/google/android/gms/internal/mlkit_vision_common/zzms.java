package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes4.dex */
public abstract class zzms {
    private static zzmr zza;

    public static synchronized zzmj zza(zzme r3) {
        monitor-enter(zzms.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zzmr(null);     // Catch: Throwable -> L7
    L9:
        zzmj r32 = (zzmj) zza.get(r3);     // Catch: Throwable -> L7
        monitor-exit(zzms.class);
        return r32;
    }

    public static synchronized zzmj zzb(String r1) {
        monitor-enter(zzms.class);
        zzmj r0 = zza(zzme.zzd("vision-common").zzd());     // Catch: Throwable -> L7
        monitor-exit(zzms.class);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }
}
