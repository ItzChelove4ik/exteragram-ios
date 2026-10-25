package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzth extends zzyd implements zzzj {
    private static final zzth zzk = null;
    private int zzb;
    private boolean zzd;
    private boolean zze;
    private boolean zzf;
    private boolean zzg;
    private int zzh;
    private int zzi;
    private boolean zzj;

    static {
        zzth r0 = new zzth();
        zzk = r0;
        zzyd.zzG(zzth.class, r0);
    }

    private zzth() {
    }

    public static /* synthetic */ zzth zza() {
        return zzk;
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
        return zzk;
    L16:
        return new zztg(null);
    L18:
        return new zzth();
    L20:
        return zzyd.zzH(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007ဇ\u0006", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh", zznm.zza(), "zzi", zznm.zza(), "zzj"});
    L22:
        return (byte) 1;
    }
}
