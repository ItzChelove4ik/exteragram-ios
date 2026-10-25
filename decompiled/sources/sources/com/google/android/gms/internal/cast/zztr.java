package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zztr extends zzyd implements zzzj {
    private static final zztr zzg = null;
    private int zzb;
    private zzyl zzd;
    private zzyl zze;
    private zzuq zzf;

    static {
        zztr r0 = new zztr();
        zzg = r0;
        zzyd.zzG(zztr.class, r0);
    }

    private zztr() {
        this.zzd = zzyd.zzM();
        this.zze = zzyd.zzM();
    }

    public static /* synthetic */ zztr zza() {
        return zzg;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r7, Object r8, Object r9) {
        int r72 = r7 - 1;
        if (r72 == 0) goto L22;
        if (r72 == 2) goto L20;
        if (r72 == 3) goto L18;
        if (r72 == 4) goto L16;
        if (r72 == 5) goto L13;
        throw null;
    L13:
        return zzg;
    L16:
        return new zztq(null);
    L18:
        return new zztr();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003ဉ\u0000", new Object[]{"zzb", "zzd", zzvg.class, "zze", zzrn.class, "zzf"});
    L22:
        return (byte) 1;
    }
}
