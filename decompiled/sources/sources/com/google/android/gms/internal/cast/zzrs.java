package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzrs extends zzyd implements zzzj {
    private static final zzrs zzh = null;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzrs r0 = new zzrs();
        zzh = r0;
        zzyd.zzG(zzrs.class, r0);
    }

    private zzrs() {
    }

    public static /* synthetic */ zzrs zza() {
        return zzh;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r2, Object r3, Object r4) {
        int r22 = r2 - 1;
        if (r22 == 0) goto L22;
        if (r22 == 2) goto L20;
        if (r22 == 3) goto L18;
        if (r22 == 4) goto L16;
        if (r22 == 5) goto L13;
        throw null;
    L13:
        return zzh;
    L16:
        return new zzrr(null);
    L18:
        return new zzrs();
    L20:
        return zzyd.zzH(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg"});
    L22:
        return (byte) 1;
    }
}
