package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
abstract class zzls {
    private static final Object zza = null;
    private static volatile zzlr zzc = null;
    private static volatile boolean zzd = false;
    private static volatile zzlr zze;

    static {
        zza = new Object();
    }

    public static void zza() {
        zzd = true;
    }

    public static boolean zzb() {
        Object r0 = zza;
        monitor-enter(r0);
        monitor-exit(r0);     // Catch: Throwable -> L7
        return false;
    L7:
        th = move-exception;
        throw th;
    }

    public static void zzc() {
        if (zze != null) goto L6;
        zze = new zzlr(null);
        return;
    }

    public static void zzd() {
        if (zzc != null) goto L6;
        zzc = new zzlr(null);
        return;
    }
}
