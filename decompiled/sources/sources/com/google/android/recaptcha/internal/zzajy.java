package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzajy extends zzafv implements zzagy {
    private static final zzajy zze = null;
    private static volatile zzahe zzf;
    private int zza;
    private zzalt zzd;

    static {
        zzajy r0 = new zzajy();
        zze = r0;
        zzafv.zzL(zzajy.class, r0);
    }

    private zzajy() {
    }

    public static /* synthetic */ zzajy zza() {
        return zze;
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
        monitor-enter(zzajy.class);
        zzahe r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajy.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzajx(null);
    L34:
        return new zzajy();
    L36:
        return zzafv.zzM(zze, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zza", "zzd"});
    L38:
        return (byte) 1;
    }
}
