package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzrz extends zzyd implements zzzj {
    private static final zzrz zzk = null;
    private int zzb;
    private int zzd;
    private int zze;
    private zzyj zzf;
    private zzyj zzg;
    private zzyl zzh;
    private zzyl zzi;
    private int zzj;

    static {
        zzrz r0 = new zzrz();
        zzk = r0;
        zzyd.zzG(zzrz.class, r0);
    }

    private zzrz() {
        this.zzf = zzyd.zzJ();
        this.zzg = zzyd.zzJ();
        this.zzh = zzyd.zzM();
        this.zzi = zzyd.zzM();
    }

    public static /* synthetic */ zzrz zza() {
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
        return new zzry(null);
    L18:
        return new zzrz();
    L20:
        return zzyd.zzH(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0004\u0000\u0001င\u0000\u0002᠌\u0001\u0003\u0016\u0004\u0016\u0005\u001a\u0006\u001a\u0007᠌\u0002", new Object[]{"zzb", "zzd", "zze", zzmq.zza(), "zzf", "zzg", "zzh", "zzi", "zzj", zzmi.zza()});
    L22:
        return (byte) 1;
    }
}
