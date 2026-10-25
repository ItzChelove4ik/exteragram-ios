package com.google.android.gms.internal.cast;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzqa extends zzyd implements zzzj {
    private static final zzqa zzk = null;
    private int zzb;
    private int zzd;
    private boolean zze;
    private int zzf;
    private boolean zzg;
    private zzyl zzh;
    private zzyl zzi;
    private String zzj;

    static {
        zzqa r0 = new zzqa();
        zzk = r0;
        zzyd.zzG(zzqa.class, r0);
    }

    private zzqa() {
        this.zzh = zzyd.zzM();
        this.zzi = zzyd.zzM();
        this.zzj = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ zzqa zza() {
        return zzk;
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
        return zzk;
    L16:
        return new zzpz(null);
    L18:
        return new zzqa();
    L20:
        return zzyd.zzH(zzk, "\u0001\u0007\u0000\u0001\u0001\t\u0007\u0000\u0002\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003᠌\u0002\u0004ဇ\u0003\u0007\u001b\b\u001b\tဈ\u0004", new Object[]{"zzb", "zzd", zzle.zza(), "zze", "zzf", zzmi.zza(), "zzg", "zzh", zztl.class, "zzi", zztl.class, "zzj"});
    L22:
        return (byte) 1;
    }
}
