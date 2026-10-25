package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzvp extends zzyd implements zzzj {
    private static final zzvp zzg = null;
    private int zzb;
    private String zzd;
    private int zze;
    private zztd zzf;

    static {
        zzvp r0 = new zzvp();
        zzg = r0;
        zzyd.zzG(zzvp.class, r0);
    }

    private zzvp() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzvp zza() {
        return zzg;
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
        return zzg;
    L16:
        return new zzvo(null);
    L18:
        return new zzvp();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002᠌\u0001\u0003ဉ\u0002", new Object[]{"zzb", "zzd", "zze", zzsa.zza, "zzf"});
    L22:
        return (byte) 1;
    }
}
