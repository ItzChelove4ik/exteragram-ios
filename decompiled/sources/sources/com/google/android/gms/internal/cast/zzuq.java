package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzuq extends zzyd implements zzzj {
    private static final zzuq zze = null;
    private int zzb;
    private zzpy zzd;

    static {
        zzuq r0 = new zzuq();
        zze = r0;
        zzyd.zzG(zzuq.class, r0);
    }

    private zzuq() {
    }

    public static /* synthetic */ zzuq zza() {
        return zze;
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
        return zze;
    L16:
        return new zzup(null);
    L18:
        return new zzuq();
    L20:
        return zzyd.zzH(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zzb", "zzd"});
    L22:
        return (byte) 1;
    }
}
