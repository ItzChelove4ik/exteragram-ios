package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzrx extends zzyd implements zzzj {
    private static final zzrx zzl = null;
    private int zzb;
    private String zzd;
    private boolean zze;
    private boolean zzf;
    private zzvv zzg;
    private boolean zzh;
    private zzyj zzi;
    private long zzj;
    private long zzk;

    static {
        zzrx r0 = new zzrx();
        zzl = r0;
        zzyd.zzG(zzrx.class, r0);
    }

    private zzrx() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzi = zzyd.zzJ();
    }

    public static /* synthetic */ zzrx zza() {
        return zzl;
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
        return zzl;
    L16:
        return new zzrw(null);
    L18:
        return new zzrx();
    L20:
        return zzyd.zzH(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဉ\u0003\u0004ဇ\u0004\u0005ࠬ\u0006ဇ\u0002\u0007ဂ\u0005\bဂ\u0006", new Object[]{"zzb", "zzd", "zze", "zzg", "zzh", "zzi", zzpi.zza(), "zzf", "zzj", "zzk"});
    L22:
        return (byte) 1;
    }
}
