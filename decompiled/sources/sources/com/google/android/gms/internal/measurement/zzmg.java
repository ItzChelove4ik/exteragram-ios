package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzmg extends zzadu implements zzafd {
    private static final zzmg zzl = null;
    private static volatile zzafj zzm;
    private int zzb;
    private String zze;
    private zzacr zzf;
    private String zzg;
    private zzaef zzh;
    private zzaef zzi;
    private boolean zzj;
    private long zzk;

    static {
        zzmg r0 = new zzmg();
        zzl = r0;
        zzadu.zzcs(zzmg.class, r0);
    }

    private zzmg() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = zzacr.zza;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = zzadu.zzcy();
        this.zzi = zzadu.zzcy();
    }

    public static zzmf zzh() {
        return (zzmf) zzl.zzcn();
    }

    public static /* synthetic */ zzmg zzp() {
        return zzl;
    }

    public final String zza() {
        return this.zze;
    }

    public final boolean zzb() {
        if ((this.zzb & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final zzacr zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final List zze() {
        return this.zzh;
    }

    public final long zzf() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.measurement.zzadu
    public final Object zzg(int r10, Object r11, Object r12) {
        int r102 = r10 - 1;
        if (r102 == 0) goto L38;
        if (r102 == 2) goto L36;
        if (r102 == 3) goto L34;
        if (r102 == 4) goto L32;
        if (r102 == 5) goto L30;
        if (r102 != 6) goto L28;
        zzafj r9 = zzm;
        if (r9 == null) goto L17;
        return r9;
    L17:
        monitor-enter(zzmg.class);
        zzafj r92 = zzm;     // Catch: Throwable -> L21
        if (r92 != null) goto L23;
        r92 = new zzadq(zzl);     // Catch: Throwable -> L21
        zzm = r92;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzmg.class);     // Catch: Throwable -> L21
        return r92;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzl;
    L32:
        return new zzmf(null);
    L34:
        return new zzmg();
    L36:
        return zzadu.zzct(zzl, "\u0004\u0007\u0000\u0001\u0001\t\u0007\u0000\u0002\u0000\u0001ဈ\u0002\u0002ဈ\u0000\u0003ည\u0001\u0004\u001b\u0005\u001a\bဇ\u0003\tဂ\u0004", new Object[]{"zzb", "zzg", "zze", "zzf", "zzh", zzmi.class, "zzi", "zzj", "zzk"});
    L38:
        return (byte) 1;
    }

    public final /* synthetic */ void zzi(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zze = r2;
    }

    public final /* synthetic */ void zzj(zzacr r2) {
        r2.getClass();
        this.zzb |= 2;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzk(String r2) {
        r2.getClass();
        this.zzb |= 4;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzl(zzmi r3) {
        r3.getClass();
        zzaef r0 = this.zzh;
        if (r0.zza() == true) goto L5;
        this.zzh = zzadu.zzcz(r0);
    L5:
        this.zzh.add(r3);
    }

    public final /* synthetic */ void zzm(String r3) {
        r3.getClass();
        zzaef r0 = this.zzi;
        if (r0.zza() == true) goto L5;
        this.zzi = zzadu.zzcz(r0);
    L5:
        this.zzi.add(r3);
    }

    public final /* synthetic */ void zzn(boolean r2) {
        this.zzb |= 8;
        this.zzj = r2;
    }

    public final /* synthetic */ void zzo(long r2) {
        this.zzb |= 16;
        this.zzk = r2;
    }
}
