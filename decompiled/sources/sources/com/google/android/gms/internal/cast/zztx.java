package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zztx extends zzyd implements zzzj {
    private static final zztx zzr = null;
    private int zzb;
    private zzua zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private String zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private long zzl;
    private zztu zzm;
    private long zzn;
    private zzuc zzo;
    private zzue zzp;
    private int zzq;

    static {
        zztx r0 = new zztx();
        zzr = r0;
        zzyd.zzG(zztx.class, r0);
    }

    private zztx() {
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zztx zza() {
        return zzr;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r18, Object r19, Object r20) {
        int r0 = r18 - 1;
        if (r0 == 0) goto L22;
        if (r0 == 2) goto L20;
        if (r0 == 3) goto L18;
        if (r0 == 4) goto L16;
        if (r0 == 5) goto L13;
        throw null;
    L13:
        return zzr;
    L16:
        return new zztw(null);
    L18:
        return new zztx();
    L20:
        return zzyd.zzH(zzr, "\u0001\u000e\u0000\u0001\u0001\u000e\u000e\u0000\u0000\u0000\u0001ဉ\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005ဈ\u0004\u0006င\u0005\u0007င\u0006\bင\u0007\tဂ\b\nဉ\t\u000bဂ\n\fဉ\u000b\rဉ\f\u000e᠌\r", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", zztv.zza});
    L22:
        return (byte) 1;
    }
}
