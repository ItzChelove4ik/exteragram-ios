package com.google.android.gms.internal.measurement;

import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzje extends zzadu implements zzafd {
    private static final zzje zzk = null;
    private static volatile zzafj zzl;
    private int zzb;
    private int zze;
    private zzaef zzf;
    private String zzg;
    private String zzh;
    private boolean zzi;
    private double zzj;

    static {
        zzje r0 = new zzje();
        zzk = r0;
        zzadu.zzcs(zzje.class, r0);
    }

    private zzje() {
        this.zzf = zzadu.zzcy();
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzje zzj() {
        return zzk;
    }

    public final List zza() {
        return this.zzf;
    }

    public final String zzb() {
        return this.zzg;
    }

    public final boolean zzc() {
        if ((this.zzb & 4) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final String zzd() {
        return this.zzh;
    }

    public final boolean zze() {
        if ((this.zzb & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean zzf() {
        return this.zzi;
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
        zzafj r9 = zzl;
        if (r9 == null) goto L17;
        return r9;
    L17:
        monitor-enter(zzje.class);
        zzafj r92 = zzl;     // Catch: Throwable -> L21
        if (r92 != null) goto L23;
        r92 = new zzadq(zzk);     // Catch: Throwable -> L21
        zzl = r92;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzje.class);     // Catch: Throwable -> L21
        return r92;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzk;
    L32:
        return new zzjb(null);
    L34:
        return new zzje();
    L36:
        return zzadu.zzct(zzk, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006က\u0004", new Object[]{"zzb", "zze", zzjc.zza, "zzf", zzje.class, "zzg", "zzh", "zzi", "zzj"});
    L38:
        return (byte) 1;
    }

    public final boolean zzh() {
        if ((this.zzb & 16) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final double zzi() {
        return this.zzj;
    }

    public final int zzk() {
        int r0 = zzjd.zza(this.zze);
        if (r0 != 0) goto L6;
        return 1;
    L6:
        return r0;
    }
}
