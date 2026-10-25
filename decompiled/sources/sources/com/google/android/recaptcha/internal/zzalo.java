package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzalo extends zzafv implements zzagy {
    private static final zzalo zzk = null;
    private static volatile zzahe zzl;
    private int zza;
    private long zzd;
    private long zze;
    private int zzf;
    private long zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    static {
        zzalo r0 = new zzalo();
        zzk = r0;
        zzafv.zzL(zzalo.class, r0);
    }

    private zzalo() {
    }

    public static zzalo zzd(byte[] r1) {
        return (zzalo) zzafv.zzV(zzk, r1);
    }

    public static /* synthetic */ zzalo zze() {
        return zzk;
    }

    public final long zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzf;
    }

    public final long zzc() {
        return this.zzg;
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
        monitor-enter(zzalo.class);
        zzahe r82 = zzl;     // Catch: Throwable -> L21
        if (r82 != null) goto L23;
        r82 = new zzafr(zzk);     // Catch: Throwable -> L21
        zzl = r82;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzalo.class);     // Catch: Throwable -> L21
        return r82;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzk;
    L32:
        return new zzaln(null);
    L34:
        return new zzalo();
    L36:
        return zzafv.zzM(zzk, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003င\u0002\u0004ဂ\u0003\u0005ဌ\u0004\u0006ဌ\u0005\u0007ဌ\u0006", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
    L38:
        return (byte) 1;
    }

    public final int zzg() {
        int r2 = this.zzh;
        int r0 = 2;
        if (r2 == 0) goto L9;
        if (r2 == 1) goto L8;
        if (r2 == 2) goto L7;
        r0 = 0;
        goto L9
    L7:
        r0 = 4;
        goto L9
    L8:
        r0 = 3;
    L9:
        if (r0 != 0) goto L11;
        return 1;
    L11:
        return r0;
    }

    public final int zzh() {
        int r2 = this.zzi;
        int r0 = 2;
        if (r2 == 0) goto L9;
        if (r2 == 1) goto L8;
        if (r2 == 2) goto L7;
        r0 = 0;
        goto L9
    L7:
        r0 = 4;
        goto L9
    L8:
        r0 = 3;
    L9:
        if (r0 != 0) goto L11;
        return 1;
    L11:
        return r0;
    }

    public final int zzi() {
        int r2 = this.zzj;
        int r0 = 2;
        if (r2 == 0) goto L9;
        if (r2 == 1) goto L8;
        if (r2 == 2) goto L7;
        r0 = 0;
        goto L9
    L7:
        r0 = 4;
        goto L9
    L8:
        r0 = 3;
    L9:
        if (r0 != 0) goto L11;
        return 1;
    L11:
        return r0;
    }
}
