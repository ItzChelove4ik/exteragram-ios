package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzqc extends zzyd implements zzzj {
    private static final zzqc zzf = null;
    private int zzb;
    private String zzd;
    private String zze;

    static {
        zzqc r0 = new zzqc();
        zzf = r0;
        zzyd.zzG(zzqc.class, r0);
    }

    private zzqc() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static zzqb zza() {
        return (zzqb) zzf.zzB();
    }

    public static /* synthetic */ zzqc zze() {
        return zzf;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L22;
        if (r12 == 2) goto L20;
        if (r12 == 3) goto L18;
        if (r12 == 4) goto L16;
        if (r12 == 5) goto L13;
        throw null;
    L13:
        return zzf;
    L16:
        return new zzqb(null);
    L18:
        return new zzqc();
    L20:
        return zzyd.zzH(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzb", "zzd", "zze"});
    L22:
        return (byte) 1;
    }

    public final /* synthetic */ void zzc(String r2) {
        r2.getClass();
        this.zzb |= 1;
        this.zzd = r2;
    }

    public final /* synthetic */ void zzd(String r2) {
        r2.getClass();
        this.zzb |= 2;
        this.zze = r2;
    }
}
