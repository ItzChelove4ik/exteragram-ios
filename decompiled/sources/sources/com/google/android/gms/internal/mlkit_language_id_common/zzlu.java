package com.google.android.gms.internal.mlkit_language_id_common;

/* loaded from: classes4.dex */
public final class zzlu {
    private static zzlu zza;

    private zzlu() {
    }

    public static synchronized zzlu zza() {
        monitor-enter(zzlu.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zzlu();     // Catch: Throwable -> L7
    L9:
        zzlu r1 = zza;     // Catch: Throwable -> L7
        monitor-exit(zzlu.class);
        return r1;
    }
}
