package com.google.android.gms.internal.mlkit_vision_common;

/* loaded from: classes4.dex */
public final class zzmw {
    private static zzmw zza;

    private zzmw() {
    }

    public static synchronized zzmw zza() {
        monitor-enter(zzmw.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zzmw();     // Catch: Throwable -> L7
    L9:
        zzmw r1 = zza;     // Catch: Throwable -> L7
        monitor-exit(zzmw.class);
        return r1;
    }

    public static final boolean zzb() {
        return zzmv.zza("mlkit-dev-profiling");
    }
}
