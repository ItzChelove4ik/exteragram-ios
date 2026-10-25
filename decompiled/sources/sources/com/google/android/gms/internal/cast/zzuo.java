package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzuo extends zzyd implements zzzj {
    private static final zzuo zzh = null;
    private int zzb;
    private String zzd;
    private zzyl zze;
    private zzyl zzf;
    private boolean zzg;

    static {
        zzuo r0 = new zzuo();
        zzh = r0;
        zzyd.zzG(zzuo.class, r0);
    }

    private zzuo() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = zzyd.zzM();
        this.zzf = zzyd.zzM();
    }

    public static /* synthetic */ zzuo zza() {
        return zzh;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L22;
        if (r82 == 2) goto L20;
        if (r82 == 3) goto L18;
        if (r82 == 4) goto L16;
        if (r82 == 5) goto L13;
        throw null;
    L13:
        return zzh;
    L16:
        return new zzun(null);
    L18:
        return new zzuo();
    L20:
        return zzyd.zzH(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဈ\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001", new Object[]{"zzb", "zzd", "zze", zzsl.class, "zzf", zzrp.class, "zzg"});
    L22:
        return (byte) 1;
    }
}
