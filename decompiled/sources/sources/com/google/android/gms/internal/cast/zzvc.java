package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzvc extends zzyd implements zzzj {
    private static final zzvc zzi = null;
    private int zzb;
    private int zzd;
    private boolean zze;
    private zzyl zzf;
    private zzyl zzg;
    private boolean zzh;

    static {
        zzvc r0 = new zzvc();
        zzi = r0;
        zzyd.zzG(zzvc.class, r0);
    }

    private zzvc() {
        this.zzf = zzyd.zzM();
        this.zzg = zzyd.zzM();
    }

    public static /* synthetic */ zzvc zza() {
        return zzi;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r10, Object r11, Object r12) {
        int r102 = r10 - 1;
        if (r102 == 0) goto L22;
        if (r102 == 2) goto L20;
        if (r102 == 3) goto L18;
        if (r102 == 4) goto L16;
        if (r102 == 5) goto L13;
        throw null;
    L13:
        return zzi;
    L16:
        return new zzvb(null);
    L18:
        return new zzvc();
    L20:
        return zzyd.zzH(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003\u001b\u0004\u001b\u0005ဇ\u0002", new Object[]{"zzb", "zzd", zzok.zza(), "zze", "zzf", zzuy.class, "zzg", zzva.class, "zzh"});
    L22:
        return (byte) 1;
    }
}
