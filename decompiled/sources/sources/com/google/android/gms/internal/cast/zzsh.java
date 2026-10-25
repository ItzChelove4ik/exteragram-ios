package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzsh extends zzyd implements zzzj {
    private static final zzsh zzl = null;
    private int zzb;
    private zzsf zzd;
    private int zze;
    private String zzf;
    private String zzg;
    private zzvp zzh;
    private zzvn zzi;
    private int zzj;
    private zzvl zzk;

    static {
        zzsh r0 = new zzsh();
        zzl = r0;
        zzyd.zzG(zzsh.class, r0);
    }

    private zzsh() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzsh zza() {
        return zzl;
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
        return zzl;
    L16:
        return new zzsg(null);
    L18:
        return new zzsh();
    L20:
        return zzyd.zzH(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဉ\u0004\u0006ဉ\u0005\u0007᠌\u0006\bဉ\u0007", new Object[]{"zzb", "zzd", "zze", zzvj.zza, "zzf", "zzg", "zzh", "zzi", "zzj", zzsc.zza, "zzk"});
    L22:
        return (byte) 1;
    }
}
