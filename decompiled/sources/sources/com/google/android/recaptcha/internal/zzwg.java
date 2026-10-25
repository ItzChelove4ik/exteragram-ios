package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzwg extends zzafv implements zzagy {
    private static final zzwg zzg = null;
    private static volatile zzahe zzh;
    private int zza;
    private int zzd;
    private zzwi zze;
    private zzaer zzf;

    static {
        zzwg r0 = new zzwg();
        zzg = r0;
        zzafv.zzL(zzwg.class, r0);
    }

    private zzwg() {
        this.zzf = zzaer.zza;
    }

    public static zzwg zzd(zzaer r1, zzafh r2) {
        return (zzwg) zzafv.zzU(zzg, r1, r2);
    }

    public static zzwf zze() {
        return (zzwf) zzg.zzF();
    }

    public static zzahe zzg() {
        return (zzahe) zzg.zzf(7, null, null);
    }

    public static /* synthetic */ zzwg zzj() {
        return zzg;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzwi zzb() {
        zzwi r0 = this.zze;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzwi.zzh();
    }

    public final zzaer zzc() {
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
        monitor-enter(zzwg.class);
        zzahe r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzwg.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzwf(null);
    L34:
        return new zzwg();
    L36:
        return zzafv.zzM(zzg, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zza", "zzd", "zze", "zzf"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzh(zzwi r1) {
        Objects.requireNonNull(r1);
        this.zze = r1;
        this.zza |= 1;
    }

    public final /* synthetic */ void zzi(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzf = r1;
    }
}
