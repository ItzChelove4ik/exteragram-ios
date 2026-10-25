package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzrh extends zzyd implements zzzj {
    private static final zzrh zzm = null;
    private int zzb;
    private String zzd;
    private long zze;
    private long zzf;
    private zzyl zzg;
    private int zzh;
    private boolean zzi;
    private String zzj;
    private long zzk;
    private long zzl;

    static {
        zzrh r0 = new zzrh();
        zzm = r0;
        zzyd.zzG(zzrh.class, r0);
    }

    private zzrh() {
        this.zzd = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = zzyd.zzM();
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzrh zza() {
        return zzm;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r12, Object r13, Object r14) {
        int r122 = r12 - 1;
        if (r122 == 0) goto L22;
        if (r122 == 2) goto L20;
        if (r122 == 3) goto L18;
        if (r122 == 4) goto L16;
        if (r122 == 5) goto L13;
        throw null;
    L13:
        return zzm;
    L16:
        return new zzrg(null);
    L18:
        return new zzrh();
    L20:
        return zzyd.zzH(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004\u001b\u0005င\u0003\u0006ဇ\u0004\u0007ဈ\u0005\bဂ\u0006\tဂ\u0007", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", zzrf.class, "zzh", "zzi", "zzj", "zzk", "zzl"});
    L22:
        return (byte) 1;
    }
}
