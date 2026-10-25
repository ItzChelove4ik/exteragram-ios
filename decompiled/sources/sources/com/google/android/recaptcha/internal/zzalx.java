package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzalx extends zzafv implements zzagy {
    private static final zzalx zzk = null;
    private static volatile zzahe zzl;
    private int zza;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private zzalv zzh;
    private int zzi;
    private zzamd zzj;

    static {
        zzalx r0 = new zzalx();
        zzk = r0;
        zzafv.zzL(zzalx.class, r0);
    }

    private zzalx() {
    }

    public static zzalw zza() {
        return (zzalw) zzk.zzF();
    }

    public static /* synthetic */ zzalx zzc() {
        return zzk;
    }

    public final /* synthetic */ void zzb(int r1) {
        this.zzi = r1;
    }

    public final /* synthetic */ void zzd(int r1) {
        this.zzd = r1 - 2;
    }

    public final /* synthetic */ void zze(int r1) {
        this.zzf = r1 - 2;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r9, Object r10, Object r11) {
        int r92 = r9 - 1;
        if (r92 == 0) goto L38;
        if (r92 == 2) goto L36;
        if (r92 == 3) goto L34;
        if (r92 == 4) goto L32;
        if (r92 == 5) goto L30;
        if (r92 != 6) goto L28;
        zzahe r8 = zzl;
        if (r8 == null) goto L17;
        return r8;
    L17:
        monitor-enter(zzalx.class);
        zzahe r82 = zzl;     // Catch: Throwable -> L21
        if (r82 != null) goto L23;
        r82 = new zzafr(zzk);     // Catch: Throwable -> L21
        zzl = r82;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzalx.class);     // Catch: Throwable -> L21
        return r82;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzk;
    L32:
        return new zzalw(null);
    L34:
        return new zzalx();
    L36:
        return zzafv.zzM(zzk, "\u0000\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\f\u0002\u000b\u0003\f\u0004\f\u0005ဉ\u0000\u0006\u000b\u0007ဉ\u0001", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
    L38:
        return (byte) 1;
    }
}
