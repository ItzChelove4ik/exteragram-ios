package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzvi extends zzyd implements zzzj {
    private static final zzvi zzh = null;
    private int zzb;
    private int zzd;
    private zzyl zze;
    private zzyl zzf;
    private int zzg;

    static {
        zzvi r0 = new zzvi();
        zzh = r0;
        zzyd.zzG(zzvi.class, r0);
    }

    private zzvi() {
        this.zze = zzyd.zzM();
        this.zzf = zzyd.zzM();
    }

    public static /* synthetic */ zzvi zza() {
        return zzh;
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
        return zzh;
    L16:
        return new zzvh(null);
    L18:
        return new zzvi();
    L20:
        return zzyd.zzH(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001᠌\u0000\u0002\u001b\u0003\u001b\u0004င\u0001", new Object[]{"zzb", "zzd", zzpc.zza(), "zze", zztl.class, "zzf", zztl.class, "zzg"});
    L22:
        return (byte) 1;
    }
}
