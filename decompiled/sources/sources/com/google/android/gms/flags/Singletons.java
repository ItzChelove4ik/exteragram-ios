package com.google.android.gms.flags;

/* loaded from: classes4.dex */
public final class Singletons {
    private static Singletons zza;
    private final FlagRegistry zzb;
    private final zzb zzc;

    static {
        Singletons r0 = new Singletons();
        monitor-enter(Singletons.class);
        zza = r0;     // Catch: Throwable -> L7
        monitor-exit(Singletons.class);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private Singletons() {
        this.zzb = new FlagRegistry();
        this.zzc = new zzb();
    }

    public static FlagRegistry flagRegistry() {
        return zzb().zzb;
    }

    private static Singletons zzb() {
        monitor-enter(Singletons.class);
        Singletons r1 = zza;     // Catch: Throwable -> L7
        monitor-exit(Singletons.class);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }
}
