package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzue extends zzyd implements zzzj {
    private static final zzue zzf = null;
    private int zzb;
    private int zzd;
    private int zze;

    static {
        zzue r0 = new zzue();
        zzf = r0;
        zzyd.zzG(zzue.class, r0);
    }

    private zzue() {
    }

    public static /* synthetic */ zzue zza() {
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
        return new zzud(null);
    L18:
        return new zzue();
    L20:
        return zzyd.zzH(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zzb", "zzd", "zze"});
    L22:
        return (byte) 1;
    }
}
