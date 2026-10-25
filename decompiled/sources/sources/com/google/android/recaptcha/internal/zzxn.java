package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzxn extends zzafv implements zzagy {
    private static final zzxn zzf = null;
    private static volatile zzahe zzg;
    private int zza;
    private int zzd;
    private int zze;

    static {
        zzxn r0 = new zzxn();
        zzf = r0;
        zzafv.zzL(zzxn.class, r0);
    }

    private zzxn() {
    }

    public static zzxm zzd() {
        return (zzxm) zzf.zzF();
    }

    public static zzxn zze() {
        return zzf;
    }

    public static /* synthetic */ zzxn zzj() {
        return zzf;
    }

    public final zzwp zza() {
        zzwp r0 = zzwp.zzb(this.zza);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzwp.zzg;
    }

    public final zzwp zzb() {
        zzwp r0 = zzwp.zzb(this.zzd);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzwp.zzg;
    }

    public final int zzc() {
        return this.zze;
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
        monitor-enter(zzxn.class);
        zzahe r02 = zzg;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzf);     // Catch: Throwable -> L21
        zzg = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzxn.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzf;
    L32:
        return new zzxm(null);
    L34:
        return new zzxn();
    L36:
        return zzafv.zzM(zzf, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\u0004", new Object[]{"zza", "zzd", "zze"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzg(zzwp r1) {
        this.zza = r1.zza();
    }

    public final /* synthetic */ void zzh(zzwp r1) {
        this.zzd = r1.zza();
    }

    public final /* synthetic */ void zzi(int r1) {
        this.zze = r1;
    }
}
