package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zziu extends zzadu implements zzafd {
    private static final zziu zzk = null;
    private static volatile zzafj zzl;
    private int zzb;
    private long zze;
    private String zzf;
    private String zzg;
    private long zzh;
    private float zzi;
    private double zzj;

    static {
        zziu r0 = new zziu();
        zzk = r0;
        zzadu.zzcs(zziu.class, r0);
    }

    private zziu() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzit zzm() {
        return (zzit) zzk.zzcn();
    }

    public static /* synthetic */ zziu zzv() {
        return zzk;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final long zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final boolean zzd() {
        if ((this.zzb & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zze() {
        return this.zzg;
    }

    public final boolean zzf() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L38;
        if (r82 == 2) goto L36;
        if (r82 == 3) goto L34;
        if (r82 == 4) goto L32;
        if (r82 == 5) goto L30;
        if (r82 != 6) goto L28;
        zzafj r7 = zzl;
        if (r7 == null) goto L17;
        return r7;
    L17:
        monitor-enter(zziu.class);
        zzafj r72 = zzl;     // Catch: Throwable -> L21
        if (r72 != null) goto L23;
        r72 = new zzadq(zzk);     // Catch: Throwable -> L21
        zzl = r72;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zziu.class);     // Catch: Throwable -> L21
        return r72;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzk;
    L32:
        return new zzit(null);
    L34:
        return new zziu();
    L36:
        return zzadu.zzct(zzk, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
    L38:
        return (byte) 1;
    }

    public final long zzh() {
        return this.zzh;
    }

    public final boolean zzi() {
        if ((this.zzb & 16) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final float zzj() {
        return this.zzi;
    }

    public final boolean zzk() {
        if ((this.zzb & 32) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final double zzl() {
        return this.zzj;
    }

    public final /* synthetic */ void zzn(long r2) {
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzo(String r2) {
        r2.getClass();
        this.zzb |= 2;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzp(String r2) {
        r2.getClass();
        this.zzb |= 4;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzq() {
        this.zzb &= -5;
        this.zzg = zzk.zzg;
    }

    public final /* synthetic */ void zzr(long r2) {
        this.zzb |= 8;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzs() {
        this.zzb &= -9;
        this.zzh = 0;
    }

    public final /* synthetic */ void zzt(double r2) {
        this.zzb |= 32;
        this.zzj = r2;
    }

    public final /* synthetic */ void zzu() {
        this.zzb &= -33;
        this.zzj = 0.0d;
    }
}
