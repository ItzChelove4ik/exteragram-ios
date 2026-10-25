package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zztd extends zzyd implements zzzj {
    private static final zztd zzh = null;
    private int zzb;
    private String zzd;
    private float zze;
    private zzyi zzf;
    private int zzg;

    static {
        zztd r0 = new zztd();
        zzh = r0;
        zzyd.zzG(zztd.class, r0);
    }

    private zztd() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = zzyd.zzL();
    }

    public static /* synthetic */ zztd zza() {
        return zzh;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r2, Object r3, Object r4) {
        int r22 = r2 - 1;
        if (r22 == 0) goto L22;
        if (r22 == 2) goto L20;
        if (r22 == 3) goto L18;
        if (r22 == 4) goto L16;
        if (r22 == 5) goto L13;
        throw null;
    L13:
        return zzh;
    L16:
        return new zztc(null);
    L18:
        return new zztd();
    L20:
        return zzyd.zzH(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဈ\u0000\u0002ခ\u0001\u0003$\u0004င\u0002", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg"});
    L22:
        return (byte) 1;
    }
}
