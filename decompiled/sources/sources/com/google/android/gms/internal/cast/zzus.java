package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzus extends zzyd implements zzzj {
    private static final zzus zzm = null;
    private int zzb;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private boolean zzj;
    private int zzk;
    private boolean zzl;

    static {
        zzus r0 = new zzus();
        zzm = r0;
        zzyd.zzG(zzus.class, r0);
    }

    private zzus() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzur zza() {
        return (zzur) zzm.zzB();
    }

    public static /* synthetic */ zzus zzi() {
        return zzm;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r12, Object r13, Object r14) {
        int r122 = r12 - 1;
        if (r122 == 0) goto L22;
        if (r122 == 2) goto L20;
        if (r122 == 3) goto L18;
        if (r122 == 4) goto L16;
        if (r122 == 5) goto L13;
        throw null;
    L13:
        return zzm;
    L16:
        return new zzur(null);
    L18:
        return new zzus();
    L20:
        return zzyd.zzH(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဇ\u0006\b᠌\u0007\tဇ\b", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzlo.zza(), "zzl"});
    L22:
        return (byte) 1;
    }

    public final /* synthetic */ void zzc(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zzd = r2;
    }

    public final /* synthetic */ void zzd(String r2) {
        r2.getClass();
        this.zzb |= 2;
        this.zze = r2;
    }

    public final /* synthetic */ void zze(String r2) {
        r2.getClass();
        this.zzb |= 4;
        this.zzf = r2;
    }

    public final /* synthetic */ void zzf(String r2) {
        r2.getClass();
        this.zzb |= 8;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzg(String r2) {
        r2.getClass();
        this.zzb |= 16;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzh(String r2) {
        r2.getClass();
        this.zzb |= 32;
        this.zzi = r2;
    }

    public final /* synthetic */ void zzj(int r1) {
        this.zzk = r1 - 1;
        this.zzb |= 128;
    }
}
