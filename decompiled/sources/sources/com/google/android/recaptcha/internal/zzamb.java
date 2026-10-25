package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzamb extends zzafv implements zzagy {
    private static final zzamb zze = null;
    private static volatile zzahe zzf;
    private zzage zza;
    private int zzd;

    static {
        zzamb r0 = new zzamb();
        zze = r0;
        zzafv.zzL(zzamb.class, r0);
    }

    private zzamb() {
        this.zza = zzafv.zzR();
    }

    public static zzamb zza(zzaer r1) {
        return (zzamb) zzafv.zzT(zze, r1);
    }

    public static zzaly zzb() {
        return (zzaly) zze.zzF();
    }

    public static /* synthetic */ zzamb zzg() {
        return zze;
    }

    private final void zzh() {
        zzage r0 = this.zza;
        if (r0.zza() == true) goto L6;
        this.zza = zzafv.zzS(r0);
        return;
    }

    public final /* synthetic */ void zzc(zzama r1) {
        Objects.requireNonNull(r1);
        zzh();
        this.zza.add(r1);
    }

    public final /* synthetic */ void zzd(Iterable r1) {
        zzh();
        zzaec.zzy(r1, this.zza);
    }

    public final /* synthetic */ void zze(int r1) {
        this.zzd = r1;
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
        monitor-enter(zzamb.class);
        zzahe r02 = zzf;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zze);     // Catch: Throwable -> L21
        zzf = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzamb.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zze;
    L32:
        return new zzaly(null);
    L34:
        return new zzamb();
    L36:
        return zzafv.zzM(zze, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u000b", new Object[]{"zza", zzama.class, "zzd"});
    L38:
        return (byte) 1;
    }
}
