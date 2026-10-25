package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzako extends zzafv implements zzagy {
    private static final zzako zzg = null;
    private static volatile zzahe zzh;
    private int zza;
    private zzaer zzd;
    private zzaer zze;
    private boolean zzf;

    static {
        zzako r0 = new zzako();
        zzg = r0;
        zzafv.zzL(zzako.class, r0);
    }

    private zzako() {
        zzaer r0 = zzaer.zza;
        this.zzd = r0;
        this.zze = r0;
    }

    public static zzako zzd() {
        return zzg;
    }

    public static /* synthetic */ zzako zze() {
        return zzg;
    }

    @Deprecated
    public final zzaer zza() {
        return this.zzd;
    }

    @Deprecated
    public final zzaer zzb() {
        return this.zze;
    }

    public final boolean zzc() {
        return this.zzf;
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
        zzahe r0 = zzh;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzako.class);
        zzahe r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzako.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzakn(null);
    L34:
        return new zzako();
    L36:
        return zzafv.zzM(zzg, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\n\u0002\n\u0003ဇ\u0000", new Object[]{"zza", "zzd", "zze", "zzf"});
    L38:
        return (byte) 1;
    }
}
