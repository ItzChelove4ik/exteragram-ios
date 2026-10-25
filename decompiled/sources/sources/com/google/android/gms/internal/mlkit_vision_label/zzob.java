package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes4.dex */
public final class zzob {
    private static zzob zza;

    private zzob() {
    }

    public static synchronized zzob zza() {
        monitor-enter(zzob.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zzob();     // Catch: Throwable -> L7
    L9:
        zzob r1 = zza;     // Catch: Throwable -> L7
        monitor-exit(zzob.class);
        return r1;
    }
}
