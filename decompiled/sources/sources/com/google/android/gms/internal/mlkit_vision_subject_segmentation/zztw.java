package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
public abstract class zztw {
    private static zztv zza;

    public static synchronized zztl zza(zztd r3) {
        monitor-enter(zztw.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zztv(null);     // Catch: Throwable -> L7
    L9:
        zztl r32 = (zztl) zza.get(r3);     // Catch: Throwable -> L7
        monitor-exit(zztw.class);
        return r32;
    }

    public static synchronized zztl zzb(String r1) {
        monitor-enter(zztw.class);
        zztl r0 = zza(zztd.zzd("subject-segmentation").zzd());     // Catch: Throwable -> L7
        monitor-exit(zztw.class);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }
}
