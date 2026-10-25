package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zztn extends zzyd implements zzzj {
    private static final zztn zzk = null;
    private int zzb;
    private boolean zzd;
    private boolean zze;
    private int zzf;
    private String zzg;
    private int zzh;
    private int zzi;
    private String zzj;

    static {
        zztn r0 = new zztn();
        zzk = r0;
        zzyd.zzG(zztn.class, r0);
    }

    private zztn() {
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zztn zza() {
        return zzk;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r9, Object r10, Object r11) {
        int r92 = r9 - 1;
        if (r92 == 0) goto L22;
        if (r92 == 2) goto L20;
        if (r92 == 3) goto L18;
        if (r92 == 4) goto L16;
        if (r92 == 5) goto L13;
        throw null;
    L13:
        return zzk;
    L16:
        return new zztm(null);
    L18:
        return new zztn();
    L20:
        return zzyd.zzH(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003င\u0002\u0004ဈ\u0003\u0005င\u0004\u0006င\u0005\u0007ဈ\u0006", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
    L22:
        return (byte) 1;
    }
}
