package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzvx extends zzyd implements zzzj {
    private static final zzvx zzh = null;
    private int zzb;
    private String zzd;
    private long zze;
    private long zzf;
    private zzvz zzg;

    static {
        zzvx r0 = new zzvx();
        zzh = r0;
        zzyd.zzG(zzvx.class, r0);
    }

    private zzvx() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzvx zza() {
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
        return new zzvw(null);
    L18:
        return new zzvx();
    L20:
        return zzyd.zzH(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဉ\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg"});
    L22:
        return (byte) 1;
    }
}
