package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzpu extends zzyd implements zzzj {
    private static final zzpu zzg = null;
    private int zzb;
    private String zzd;
    private String zze;
    private zzyj zzf;

    static {
        zzpu r0 = new zzpu();
        zzg = r0;
        zzyd.zzG(zzpu.class, r0);
    }

    private zzpu() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzf = zzyd.zzJ();
    }

    public static /* synthetic */ zzpu zza() {
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
        return new zzpt(null);
    L18:
        return new zzpu();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ࠞ", new Object[]{"zzb", "zzd", "zze", "zzf", zzpm.zzb()});
    L22:
        return (byte) 1;
    }
}
