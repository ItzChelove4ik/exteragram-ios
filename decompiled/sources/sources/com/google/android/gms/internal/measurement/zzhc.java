package com.google.android.gms.internal.measurement;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzhc extends zzadu implements zzafd {
    private static final zzhc zzl = null;
    private static volatile zzafj zzm;
    private int zzb;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private String zzk;

    static {
        zzhc r0 = new zzhc();
        zzl = r0;
        zzadu.zzcs(zzhc.class, r0);
    }

    private zzhc() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzk = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzhc zza() {
        return zzl;
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
        zzafj r8 = zzm;
        if (r8 == null) goto L17;
        return r8;
    L17:
        monitor-enter(zzhc.class);
        zzafj r82 = zzm;     // Catch: Throwable -> L21
        if (r82 != null) goto L23;
        r82 = new zzadq(zzl);     // Catch: Throwable -> L21
        zzm = r82;     // Catch: Throwable -> L21
    L23:
        monitor-exit(zzhc.class);     // Catch: Throwable -> L21
        return r82;
    L21:
        th = move-exception;
        throw th;
    L28:
        throw null;
    L30:
        return zzl;
    L32:
        return new zzhb(null);
    L34:
        return new zzhc();
    L36:
        return zzadu.zzct(zzl, "\u0004\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
    L38:
        return (byte) 1;
    }
}
