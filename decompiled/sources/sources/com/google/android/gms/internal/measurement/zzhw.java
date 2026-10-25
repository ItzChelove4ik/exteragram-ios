package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzhw extends zzadu implements zzafd {
    private static final zzhw zzk = null;
    private static volatile zzafj zzl;
    private int zzb;
    private String zze;
    private String zzf;
    private long zzg;
    private float zzh;
    private double zzi;
    private zzaef zzj;

    static {
        zzhw r0 = new zzhw();
        zzk = r0;
        zzadu.zzcs(zzhw.class, r0);
    }

    private zzhw() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = zzadu.zzcy();
    }

    public static zzhv zzn() {
        return (zzhv) zzk.zzcn();
    }

    public static /* synthetic */ zzhw zzy() {
        return zzk;
    }

    private final void zzz() {
        zzaef r0 = this.zzj;
        if (r0.zza() == true) goto L6;
        this.zzj = zzadu.zzcz(r0);
        return;
    }

    public final boolean zza() {
        if ((this.zzb & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public final String zzb() {
        return this.zze;
    }

    public final boolean zzc() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final boolean zze() {
        if ((this.zzb & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final long zzf() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r9, Object r10, Object r11) {
        int r92 = r9 - 1;
        if (r92 == 0) goto L38;
        if (r92 == 2) goto L36;
        if (r92 == 3) goto L34;
        if (r92 == 4) goto L32;
        if (r92 == 5) goto L30;
        if (r92 != 6) goto L28;
        zzafj r8 = zzl;
        if (r8 == null) goto L17;
        return r8;
    L17:
        monitor-enter(zzhw.class);
        zzafj r82 = zzl;     // Catch: Throwable -> L21
        if (r82 != null) goto L23;
        r82 = new zzadq(zzk);     // Catch: Throwable -> L21
        zzl = r82;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzhw.class);     // Catch: Throwable -> L21
        return r82;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzk;
    L32:
        return new zzhv(null);
    L34:
        return new zzhw();
    L36:
        return zzadu.zzct(zzk, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzhw.class});
    L38:
        return (byte) 1;
    }

    public final boolean zzh() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final float zzi() {
        return this.zzh;
    }

    public final boolean zzj() {
        if ((this.zzb & 16) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final double zzk() {
        return this.zzi;
    }

    public final List zzl() {
        return this.zzj;
    }

    public final int zzm() {
        return this.zzj.size();
    }

    public final /* synthetic */ void zzo(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzp(String r2) {
        r2.getClass();
        this.zzb |= 2;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzq() {
        this.zzb &= -3;
        this.zzf = zzk.zzf;
    }

    public final /* synthetic */ void zzr(long r2) {
        this.zzb |= 4;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzs() {
        this.zzb &= -5;
        this.zzg = 0;
    }

    public final /* synthetic */ void zzt(double r2) {
        this.zzb |= 16;
        this.zzi = r2;
    }

    public final /* synthetic */ void zzu() {
        this.zzb &= -17;
        this.zzi = 0.0d;
    }

    public final /* synthetic */ void zzv(zzhw r1) {
        r1.getClass();
        zzz();
        this.zzj.add(r1);
    }

    public final /* synthetic */ void zzw(Iterable r1) {
        zzz();
        zzacb.zzcg(r1, this.zzj);
    }

    public final /* synthetic */ void zzx() {
        this.zzj = zzadu.zzcy();
    }
}
