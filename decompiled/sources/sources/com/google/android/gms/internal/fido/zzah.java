package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
abstract class zzah {
    private static final Object zza = null;
    private static volatile zzag zzc = null;
    private static volatile boolean zzd = false;
    private static volatile zzag zze;

    static {
        zza = new Object();
    }

    public static void zza() {
        zzd = true;
    }

    public static void zzb() {
        if (zze != null) goto L6;
        zze = new zzag(null);
        return;
    }

    public static void zzc() {
        if (zzc != null) goto L6;
        zzc = new zzag(null);
        return;
    }

    public static boolean zzd() {
        Object r0 = zza;
        monitor-enter(r0);
        monitor-exit(r0);     // Catch: Throwable -> L7
        return false;
    L7:
        th = move-exception;
        throw th;
    }
}
