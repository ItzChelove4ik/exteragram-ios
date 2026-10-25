package com.google.android.recaptcha.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzxp extends zzafv implements zzagy {
    private static final zzxp zzl = null;
    private static volatile zzahe zzm;
    private int zza;
    private int zzd;
    private zzxr zze;
    private zzaer zzf;
    private zzaer zzg;
    private zzaer zzh;
    private zzaer zzi;
    private zzaer zzj;
    private zzaer zzk;

    static {
        zzxp r0 = new zzxp();
        zzl = r0;
        zzafv.zzL(zzxp.class, r0);
    }

    private zzxp() {
        zzaer r0 = zzaer.zza;
        this.zzf = r0;
        this.zzg = r0;
        this.zzh = r0;
        this.zzi = r0;
        this.zzj = r0;
        this.zzk = r0;
    }

    public static zzxp zzj(zzaer r1, zzafh r2) {
        return (zzxp) zzafv.zzU(zzl, r1, r2);
    }

    public static zzxo zzk() {
        return (zzxo) zzl.zzF();
    }

    public static zzahe zzl() {
        return (zzahe) zzl.zzf(7, null, null);
    }

    public static /* synthetic */ zzxp zzu() {
        return zzl;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzxr zzb() {
        zzxr r0 = this.zze;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzxr.zzh();
    }

    public final zzaer zzc() {
        return this.zzf;
    }

    public final zzaer zzd() {
        return this.zzg;
    }

    public final zzaer zze() {
        return this.zzh;
    }

    @Override // com.google.android.recaptcha.internal.zzafv
    public final Object zzf(int r10, Object r11, Object r12) {
        int r102 = r10 - 1;
        if (r102 == 0) goto L38;
        if (r102 == 2) goto L36;
        if (r102 == 3) goto L34;
        if (r102 == 4) goto L32;
        if (r102 == 5) goto L30;
        if (r102 != 6) goto L28;
        zzahe r9 = zzm;
        if (r9 == null) goto L17;
        return r9;
    L17:
        monitor-enter(zzxp.class);
        zzahe r92 = zzm;     // Catch: Throwable -> L21
        if (r92 != null) goto L23;
        r92 = new zzafr(zzl);     // Catch: Throwable -> L21
        zzm = r92;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzxp.class);     // Catch: Throwable -> L21
        return r92;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzl;
    L32:
        return new zzxo(null);
    L34:
        return new zzxp();
    L36:
        return zzafv.zzM(zzl, "\u0000\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n\u0004\n\u0005\n\u0006\n\u0007\n\b\n", new Object[]{"zza", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
    L38:
        return (byte) 1;
    }

    public final zzaer zzg() {
        return this.zzi;
    }

    public final zzaer zzh() {
        return this.zzj;
    }

    public final zzaer zzi() {
        return this.zzk;
    }

    public final /* synthetic */ void zzm(int r1) {
        this.zzd = 0;
    }

    public final /* synthetic */ void zzn(zzxr r1) {
        Objects.requireNonNull(r1);
        this.zze = r1;
        this.zza |= 1;
    }

    public final /* synthetic */ void zzo(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzf = r1;
    }

    public final /* synthetic */ void zzp(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzg = r1;
    }

    public final /* synthetic */ void zzq(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzh = r1;
    }

    public final /* synthetic */ void zzr(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzi = r1;
    }

    public final /* synthetic */ void zzs(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzj = r1;
    }

    public final /* synthetic */ void zzt(zzaer r1) {
        Objects.requireNonNull(r1);
        this.zzk = r1;
    }
}
