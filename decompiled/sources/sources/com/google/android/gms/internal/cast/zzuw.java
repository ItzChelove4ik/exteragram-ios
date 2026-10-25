package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzuw extends zzyd implements zzzj {
    private static final zzuw zzo = null;
    private int zzb;
    private String zzd;
    private zzrp zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private long zzj;
    private long zzk;
    private int zzl;
    private int zzm;
    private zzyj zzn;

    static {
        zzuw r0 = new zzuw();
        zzo = r0;
        zzyd.zzG(zzuw.class, r0);
    }

    private zzuw() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzn = zzyd.zzJ();
    }

    public static /* synthetic */ zzuw zza() {
        return zzo;
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
        return zzo;
    L16:
        return new zzuv(null);
    L18:
        return new zzuw();
    L20:
        return zzyd.zzH(zzo, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0001\u0000\u0001ဉ\u0001\u0002ဇ\u0002\u0003ဇ\u0003\u0004ဇ\u0004\u0005ဂ\u0006\u0006ဂ\u0007\u0007င\b\bင\t\t'\nဈ\u0000\u000bဇ\u0005", new Object[]{"zzb", "zze", "zzf", "zzg", "zzh", "zzj", "zzk", "zzl", "zzm", "zzn", "zzd", "zzi"});
    L22:
        return (byte) 1;
    }
}
