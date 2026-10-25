package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
public final class zztx {
    private static zztx zza;

    private zztx() {
    }

    public static synchronized zztx zza() {
        monitor-enter(zztx.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zztx();     // Catch: Throwable -> L7
    L9:
        zztx r1 = zza;     // Catch: Throwable -> L7
        monitor-exit(zztx.class);
        return r1;
    }
}
