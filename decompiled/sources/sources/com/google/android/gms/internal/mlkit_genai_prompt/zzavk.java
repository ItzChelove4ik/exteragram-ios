package com.google.android.gms.internal.mlkit_genai_prompt;

/* loaded from: classes4.dex */
public abstract class zzavk {
    private static zzavj zza;

    public static synchronized zzavc zza(String r1) {
        monitor-enter(zzavk.class);
        zzavc r0 = zzb(zzaux.zzd("genai-prompt").zzd());     // Catch: Throwable -> L7
        monitor-exit(zzavk.class);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    public static synchronized zzavc zzb(zzaux r3) {
        monitor-enter(zzavk.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zzavj(null);     // Catch: Throwable -> L7
    L9:
        zzavc r32 = (zzavc) zza.get(r3);     // Catch: Throwable -> L7
        monitor-exit(zzavk.class);
        return r32;
    }
}
