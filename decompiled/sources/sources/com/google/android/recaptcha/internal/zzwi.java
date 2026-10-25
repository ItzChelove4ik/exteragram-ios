package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzwi extends zzafv implements zzagy {
    private static final zzwi zzh = null;
    private static volatile zzahe zzi;
    private int zza;
    private int zzd;
    private zzwe zze;
    private zzaer zzf;
    private zzaer zzg;

    static {
        zzwi r0 = new zzwi();
        zzh = r0;
        zzafv.zzL(zzwi.class, r0);
    }

    private zzwi() {
        zzaer r0 = zzaer.zza;
        this.zzf = r0;
        this.zzg = r0;
    }

    public static zzwi zze(zzaer r1, zzafh r2) {
        return (zzwi) zzafv.zzU(zzh, r1, r2);
    }

    public static zzwh zzg() {
        return (zzwh) zzh.zzF();
    }

    public static zzwi zzh() {
        return zzh;
    }

    public static zzahe zzi() {
        return (zzahe) zzh.zzf(7, null, null);
    }

    public static /* synthetic */ zzwi zzm() {
        return zzh;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzwe zzb() {
        zzwe r0 = this.zze;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzwe.zzc();
    }

    public final zzaer zzc() {
        return this.zzf;
    }

    public final zzaer zzd() {
        return this.zzg;
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
        monitor-enter(zzwi.class);
        zzahe r12 = zzi;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzafr(zzh);     // Catch: Throwable -> L21
        zzi = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzwi.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzwh(null);
    L34:
        return new zzwi();
    L36:
        return zzafv.zzM(zzh, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n\u0004\n", new Object[]{"zza", "zzd", "zze", "zzf", "zzg"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzj(zzwe r1) {
        Objects.requireNonNull(r1);
        this.zze = r1;
        this.zza |= 1;
    }

    public final /* synthetic */ void zzk(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzf = r1;
    }

    public final /* synthetic */ void zzl(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzg = r1;
    }
}
