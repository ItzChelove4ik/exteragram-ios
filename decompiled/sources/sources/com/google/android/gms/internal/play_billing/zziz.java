package com.google.android.gms.internal.play_billing;

/* loaded from: classes.dex */
public final class zziz extends zzfi implements zzgm {
    private static final zziz zzb = null;
    private int zzd;
    private int zze;

    static {
        zziz r0 = new zziz();
        zzb = r0;
        zzfi.zzw(zziz.class, r0);
    }

    private zziz() {
    }

    public static /* bridge */ /* synthetic */ zziz zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L22;
        if (r12 == 2) goto L20;
        if (r12 == 3) goto L18;
        if (r12 == 4) goto L16;
        if (r12 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zziw(null);
    L18:
        return new zziz();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zzix.zza});
    L22:
        return (byte) 1;
    }
}
