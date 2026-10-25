package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzsv extends zzyd implements zzzj {
    private static final zzsv zzk = null;
    private int zzb;
    private String zzd;
    private String zze;
    private boolean zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    static {
        zzsv r0 = new zzsv();
        zzk = r0;
        zzyd.zzG(zzsv.class, r0);
    }

    private zzsv() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzsv zza() {
        return zzk;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r13, Object r14, Object r15) {
        int r132 = r13 - 1;
        if (r132 == 0) goto L22;
        if (r132 == 2) goto L20;
        if (r132 == 3) goto L18;
        if (r132 == 4) goto L16;
        if (r132 == 5) goto L13;
        throw null;
    L13:
        return zzk;
    L16:
        return new zzsu(null);
    L18:
        return new zzsv();
    L20:
        return zzyd.zzH(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007᠌\u0006", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", zzna.zza(), "zzh", zzne.zza(), "zzi", zznc.zza(), "zzj", zzng.zza()});
    L22:
        return (byte) 1;
    }
}
