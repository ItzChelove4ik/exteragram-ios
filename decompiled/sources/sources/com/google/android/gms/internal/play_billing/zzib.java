package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
public final class zzib extends zzfi implements zzgm {
    private static final zzib zzb = null;
    private int zzd;
    private int zze;
    private Object zzf;
    private int zzg;
    private int zzh;

    static {
        zzib r0 = new zzib();
        zzb = r0;
        zzfi.zzw(zzib.class, r0);
    }

    private zzib() {
        this.zze = 0;
    }

    public static /* synthetic */ void zzB(zzib r0, zzil r1) {
        r0.zzh = r1.zza();
        r0.zzd |= 2;
    }

    public static /* synthetic */ void zzD(zzib r0, zzjf r1) {
        r1.getClass();
        r0.zzf = r1;
        r0.zze = 4;
    }

    public static /* synthetic */ void zzE(zzib r0, zzjv r1) {
        r1.getClass();
        r0.zzf = r1;
        r0.zze = 3;
    }

    public static /* synthetic */ void zzF(zzib r0, int r1) {
        r0.zzg = r1 - 1;
        r0.zzd |= 1;
    }

    public static zzhz zzc() {
        return (zzhz) zzb.zzl();
    }

    public static /* bridge */ /* synthetic */ zzib zzd() {
        return zzb;
    }

    public final zzjf zzA() {
        if (this.zze != 4) goto L7;
        return (zzjf) this.zzf;
    L7:
        return zzjf.zzd();
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r11, Object r12, Object r13) {
        int r112 = r11 - 1;
        if (r112 == 0) goto L22;
        if (r112 == 2) goto L20;
        if (r112 == 3) goto L18;
        if (r112 == 4) goto L16;
        if (r112 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zzhz(null);
    L18:
        return new zzib();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0005\u0001\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005᠌\u0001", new Object[]{"zzf", "zze", "zzd", "zzg", zzhy.zza, zziz.class, zzjv.class, zzjf.class, "zzh", zzik.zza});
    L22:
        return (byte) 1;
    }
}
