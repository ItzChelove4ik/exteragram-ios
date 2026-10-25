package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzajp extends zzafv implements zzagy {
    private static final zzajp zzh = null;
    private static volatile zzahe zzi;
    private int zza;
    private zzafe zzd;
    private zzahx zze;
    private zzafe zzf;
    private zzahx zzg;

    static {
        zzajp r0 = new zzajp();
        zzh = r0;
        zzafv.zzL(zzajp.class, r0);
    }

    private zzajp() {
    }

    public static /* synthetic */ zzajp zza() {
        return zzh;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r2, Object r3, Object r4) {
        int r22 = r2 - 1;
        if (r22 == 0) goto L38;
        if (r22 == 2) goto L36;
        if (r22 == 3) goto L34;
        if (r22 == 4) goto L32;
        if (r22 == 5) goto L30;
        if (r22 != 6) goto L28;
        zzahe r1 = zzi;
        if (r1 == null) goto L17;
        return r1;
    L17:
        monitor-enter(zzajp.class);
        zzahe r12 = zzi;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzafr(zzh);     // Catch: Throwable -> L21
        zzi = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzajp.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzajo(null);
    L34:
        return new zzajp();
    L36:
        return zzafv.zzM(zzh, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zza", "zzd", "zze", "zzf", "zzg"});
    L38:
        return (byte) 1;
    }
}
