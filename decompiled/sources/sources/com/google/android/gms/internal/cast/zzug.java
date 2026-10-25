package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzug extends zzyd implements zzzj {
    private static final zzug zzl = null;
    private int zzb;
    private zzabb zzd;
    private zzyl zze;
    private zzyl zzf;
    private zzyl zzg;
    private String zzh;
    private long zzi;
    private int zzj;
    private String zzk;

    static {
        zzug r0 = new zzug();
        zzl = r0;
        zzyd.zzG(zzug.class, r0);
    }

    private zzug() {
        this.zze = zzyd.zzM();
        this.zzf = zzyd.zzM();
        this.zzg = zzyd.zzM();
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzk = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzug zza() {
        return zzl;
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
        return zzl;
    L16:
        return new zzuf(null);
    L18:
        return new zzug();
    L20:
        return zzyd.zzH(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0003\u0000\u0001ဉ\u0000\u0002\u001a\u0003\u001b\u0004\u001b\u0005ဈ\u0001\u0006ဂ\u0002\u0007᠌\u0003\bဈ\u0004", new Object[]{"zzb", "zzd", "zze", "zzf", zzqe.class, "zzg", zztx.class, "zzh", "zzi", "zzj", zzql.zza, "zzk"});
    L22:
        return (byte) 1;
    }
}
