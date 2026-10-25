package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzafe extends zzafv implements zzagy {
    private static final zzafe zze = null;
    private static volatile zzahe zzf;
    private long zza;
    private int zzd;

    static {
        zzafe r0 = new zzafe();
        zze = r0;
        zzafv.zzL(zzafe.class, r0);
    }

    private zzafe() {
    }

    public static zzafd zzc() {
        return (zzafd) zze.zzF();
    }

    public static zzafe zzd() {
        return zze;
    }

    public static /* synthetic */ zzafe zzh() {
        return zze;
    }

    public final long zza() {
        return this.zza;
    }

    public final int zzb() {
        return this.zzd;
    }

    public final /* synthetic */ void zze(long r1) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L38;
        if (r12 == 2) goto L36;
        if (r12 == 3) goto L34;
        if (r12 == 4) goto L32;
        if (r12 == 5) goto L30;
        if (r12 != 6) goto L28;
        zzahe r0 = zzf;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzafe.class);
        zzahe r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzafe.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzafd(null);
    L34:
        return new zzafe();
    L36:
        return new zzahi(zze, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zza", "zzd"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(int r1) {
        this.zzd = r1;
    }
}
