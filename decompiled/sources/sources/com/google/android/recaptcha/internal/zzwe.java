package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzwe extends zzafv implements zzagy {
    private static final zzwe zzf = null;
    private static volatile zzahe zzg;
    private int zza;
    private int zzd;
    private int zze;

    static {
        zzwe r0 = new zzwe();
        zzf = r0;
        zzafv.zzL(zzwe.class, r0);
    }

    private zzwe() {
    }

    public static zzwd zzb() {
        return (zzwd) zzf.zzF();
    }

    public static zzwe zzc() {
        return zzf;
    }

    public static /* synthetic */ zzwe zze() {
        return zzf;
    }

    public final zzwp zza() {
        zzwp r0 = zzwp.zzb(this.zza);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzwp.zzg;
    }

    public final /* synthetic */ void zzd(zzwp r1) {
        this.zza = r1.zza();
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
        zzahe r0 = zzg;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzwe.class);
        zzahe r02 = zzg;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzf);     // Catch: Throwable -> L21
        zzg = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzwe.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzf;
    L32:
        return new zzwd(null);
    L34:
        return new zzwe();
    L36:
        return zzafv.zzM(zzf, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f", new Object[]{"zza", "zzd", "zze"});
    L38:
        return (byte) 1;
    }

    public final int zzg() {
        int r3 = this.zzd;
        int r0 = 2;
        if (r3 != 0) goto L5;
    L15:
        if (r0 != 0) goto L18;
        return 1;
    L18:
        return r0;
    L5:
        if (r3 != 2) goto L7;
        r0 = 4;
        goto L15
    L7:
        if (r3 == 3) goto L13;
        if (r3 == 4) goto L12;
        if (r3 == 5) goto L11;
        r0 = 0;
        goto L15
    L11:
        r0 = 7;
        goto L15
    L12:
        r0 = 6;
        goto L15
    L13:
        r0 = 5;
        goto L15
    }

    public final int zzh() {
        int r2 = this.zze;
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

    public final /* synthetic */ void zzi(int r1) {
        this.zzd = zzwo.zza(r1);
    }

    public final /* synthetic */ void zzj(int r1) {
        this.zze = zzwj.zza(r1);
    }
}
