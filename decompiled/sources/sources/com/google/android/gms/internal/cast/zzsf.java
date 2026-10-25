package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzsf extends zzyd implements zzzj {
    private static final zzsf zzi = null;
    private int zzb;
    private int zzd;
    private Object zze;
    private int zzf;
    private String zzg;
    private zzyl zzh;

    static {
        zzsf r0 = new zzsf();
        zzi = r0;
        zzyd.zzG(zzsf.class, r0);
    }

    private zzsf() {
        this.zzd = 0;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = zzyd.zzM();
    }

    public static /* synthetic */ zzsf zza() {
        return zzi;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r11, Object r12, Object r13) {
        int r112 = r11 - 1;
        if (r112 == 0) goto L22;
        if (r112 == 2) goto L20;
        if (r112 == 3) goto L18;
        if (r112 == 4) goto L16;
        if (r112 == 5) goto L13;
        throw null;
    L13:
        return zzi;
    L16:
        return new zzse(null);
    L18:
        return new zzsf();
    L20:
        return zzyd.zzH(zzi, "\u0001\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001᠌\u0000\u0002ဈ\u0001\u0003\u001b\u0004<\u0000\u0005<\u0000", new Object[]{"zze", "zzd", "zzb", "zzf", zzsd.zza, "zzg", "zzh", zztd.class, zzvp.class, zzvn.class});
    L22:
        return (byte) 1;
    }
}
