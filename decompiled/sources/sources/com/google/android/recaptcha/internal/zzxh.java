package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzxh extends zzafv implements zzagy {
    private static final zzxh zzd = null;
    private static volatile zzahe zze;
    private int zza;

    static {
        zzxh r0 = new zzxh();
        zzd = r0;
        zzafv.zzL(zzxh.class, r0);
    }

    private zzxh() {
    }

    public static zzxg zzb() {
        return (zzxg) zzd.zzF();
    }

    public static zzxh zzc() {
        return zzd;
    }

    public static /* synthetic */ zzxh zze() {
        return zzd;
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
        zzahe r0 = zze;
        if (r0 == null) goto L17;
        return r0;
    L17:
        monitor-enter(zzxh.class);
        zzahe r02 = zze;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzd);     // Catch: Throwable -> L21
        zze = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzxh.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzd;
    L32:
        return new zzxg(null);
    L34:
        return new zzxh();
    L36:
        return zzafv.zzM(zzd, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"zza"});
    L38:
        return (byte) 1;
    }
}
