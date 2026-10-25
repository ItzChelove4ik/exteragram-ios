package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes4.dex */
public abstract class zzoa {
    private static zznz zza;

    public static synchronized zznp zza(zznh r3) {
        monitor-enter(zzoa.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zznz(null);     // Catch: Throwable -> L7
    L9:
        zznp r32 = (zznp) zza.get(r3);     // Catch: Throwable -> L7
        monitor-exit(zzoa.class);
        return r32;
    }

    public static synchronized zznp zzb(String r1) {
        monitor-enter(zzoa.class);
        zznp r0 = zza(zznh.zzd("play-services-mlkit-image-labeling").zzd());     // Catch: Throwable -> L7
        monitor-exit(zzoa.class);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }
}
