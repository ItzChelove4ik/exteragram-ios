package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzva extends zzyd implements zzzj {
    private static final zzva zzi = null;
    private int zzb;
    private int zzd;
    private long zze;
    private zzyl zzf;
    private zzyl zzg;
    private zzyl zzh;

    static {
        zzva r0 = new zzva();
        zzi = r0;
        zzyd.zzG(zzva.class, r0);
    }

    private zzva() {
        this.zzf = zzyd.zzM();
        this.zzg = zzyd.zzM();
        this.zzh = zzyd.zzM();
    }

    public static /* synthetic */ zzva zza() {
        return zzi;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r11, Object r12, Object r13) {
        int r112 = r11 - 1;
        if (r112 == 0) goto L22;
        if (r112 == 2) goto L20;
        if (r112 == 3) goto L18;
        if (r112 == 4) goto L16;
        if (r112 == 5) goto L13;
        throw null;
    L13:
        return zzi;
    L16:
        return new zzuz(null);
    L18:
        return new zzva();
    L20:
        return zzyd.zzH(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0003\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003\u001b\u0004\u001b\u0005\u001b", new Object[]{"zzb", "zzd", zzoi.zza(), "zze", "zzf", zzus.class, "zzg", zzqt.class, "zzh", zzuy.class});
    L22:
        return (byte) 1;
    }
}
