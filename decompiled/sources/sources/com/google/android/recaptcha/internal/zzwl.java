package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzwl extends zzafv implements zzagy {
    private static final zzwl zzg = null;
    private static volatile zzahe zzh;
    private int zza;
    private int zzd;
    private zzaer zze;
    private zzwn zzf;

    static {
        zzwl r0 = new zzwl();
        zzg = r0;
        zzafv.zzL(zzwl.class, r0);
    }

    private zzwl() {
        this.zze = zzaer.zza;
    }

    public static zzwl zzd(zzaer r1, zzafh r2) {
        return (zzwl) zzafv.zzU(zzg, r1, r2);
    }

    public static zzwk zze() {
        return (zzwk) zzg.zzF();
    }

    public static zzahe zzg() {
        return (zzahe) zzg.zzf(7, null, null);
    }

    public static /* synthetic */ zzwl zzj() {
        return zzg;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzaer zzb() {
        return this.zze;
    }

    public final zzwn zzc() {
        zzwn r0 = this.zzf;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzwn.zze();
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
        monitor-enter(zzwl.class);
        zzahe r02 = zzh;     // Catch: Throwable -> L21
        if (r02 != null) goto L23;
        r02 = new zzafr(zzg);     // Catch: Throwable -> L21
        zzh = r02;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzwl.class);     // Catch: Throwable -> L21
        return r02;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzg;
    L32:
        return new zzwk(null);
    L34:
        return new zzwl();
    L36:
        return zzafv.zzM(zzg, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003ဉ\u0000", new Object[]{"zza", "zzd", "zze", "zzf"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzh(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    public final /* synthetic */ void zzi(zzwn r1) {
        Objects.requireNonNull(r1);
        this.zzf = r1;
        this.zza |= 1;
    }
}
