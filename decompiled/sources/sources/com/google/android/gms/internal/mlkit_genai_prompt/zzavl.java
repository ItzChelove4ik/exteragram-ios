package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public final class zzavl {
    private static zzavl zza;

    private zzavl() {
    }

    public static synchronized zzavl zza() {
        monitor-enter(zzavl.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zzavl();     // Catch: Throwable -> L7
    L9:
        zzavl r1 = zza;     // Catch: Throwable -> L7
        monitor-exit(zzavl.class);
        return r1;
    }
}
