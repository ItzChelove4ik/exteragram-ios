package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzxr extends zzafv implements zzagy {
    private static final zzxr zzh = null;
    private static volatile zzahe zzi;
    private int zza;
    private int zzd;
    private zzxn zze;
    private zzaer zzf;
    private zzaer zzg;

    static {
        zzxr r0 = new zzxr();
        zzh = r0;
        zzafv.zzL(zzxr.class, r0);
    }

    private zzxr() {
        zzaer r0 = zzaer.zza;
        this.zzf = r0;
        this.zzg = r0;
    }

    public static zzxr zze(zzaer r1, zzafh r2) {
        return (zzxr) zzafv.zzU(zzh, r1, r2);
    }

    public static zzxq zzg() {
        return (zzxq) zzh.zzF();
    }

    public static zzxr zzh() {
        return zzh;
    }

    public static zzahe zzi() {
        return (zzahe) zzh.zzf(7, null, null);
    }

    public static /* synthetic */ zzxr zzn() {
        return zzh;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzxn zzb() {
        zzxn r0 = this.zze;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzxn.zze();
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
        monitor-enter(zzxr.class);
        zzahe r12 = zzi;     // Catch: Throwable -> L21
        if (r12 != null) goto L23;
        r12 = new zzafr(zzh);     // Catch: Throwable -> L21
        zzi = r12;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzxr.class);     // Catch: Throwable -> L21
        return r12;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzh;
    L32:
        return new zzxq(null);
    L34:
        return new zzxr();
    L36:
        return zzafv.zzM(zzh, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n\u0004\n", new Object[]{"zza", "zzd", "zze", "zzf", "zzg"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzj(int r1) {
        this.zzd = 0;
    }

    public final /* synthetic */ void zzk(zzxn r1) {
        Objects.requireNonNull(r1);
        this.zze = r1;
        this.zza |= 1;
    }

    public final /* synthetic */ void zzl(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzf = r1;
    }

    public final /* synthetic */ void zzm(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzg = r1;
    }
}
