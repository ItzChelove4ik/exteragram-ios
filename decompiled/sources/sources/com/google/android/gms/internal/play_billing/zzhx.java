package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
public final class zzhx extends zzfi implements zzgm {
    private static final zzhx zzb = null;
    private int zzd;
    private int zze;
    private Object zzf;
    private int zzg;
    private zzig zzh;
    private int zzi;

    static {
        zzhx r0 = new zzhx();
        zzb = r0;
        zzfi.zzw(zzhx.class, r0);
    }

    private zzhx() {
        this.zze = 0;
    }

    public static zzhx zzA(byte[] r1, zzeu r2) {
        return (zzhx) zzfi.zzp(zzb, r1, r2);
    }

    public static /* synthetic */ void zzC(zzhx r0, zzil r1) {
        r0.zzi = r1.zza();
        r0.zzd |= 4;
    }

    public static /* synthetic */ void zzD(zzhx r0, zzig r1) {
        r1.getClass();
        r0.zzh = r1;
        r0.zzd |= 2;
    }

    public static /* synthetic */ void zzF(zzhx r0, zzjf r1) {
        r1.getClass();
        r0.zzf = r1;
        r0.zze = 7;
    }

    public static /* synthetic */ void zzG(zzhx r0, zzjv r1) {
        r1.getClass();
        r0.zzf = r1;
        r0.zze = 6;
    }

    public static /* synthetic */ void zzH(zzhx r0, int r1) {
        r0.zzg = r1 - 1;
        r0.zzd |= 1;
    }

    public static zzhv zzc() {
        return (zzhv) zzb.zzl();
    }

    public static /* bridge */ /* synthetic */ zzhx zzd() {
        return zzb;
    }

    public final zzjf zzB() {
        if (this.zze != 7) goto L7;
        return (zzjf) this.zzf;
    L7:
        return zzjf.zzd();
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r12, Object r13, Object r14) {
        int r122 = r12 - 1;
        if (r122 == 0) goto L22;
        if (r122 == 2) goto L20;
        if (r122 == 3) goto L18;
        if (r122 == 4) goto L16;
        if (r122 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zzhv(null);
    L18:
        return new zzhx();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0006\u0001\u0001\u0001\u0007\u0006\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001\u0004<\u0000\u0005᠌\u0002\u0006<\u0000\u0007<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzhy.zza, "zzh", zziz.class, "zzi", zzik.zza, zzjv.class, zzjf.class});
    L22:
        return (byte) 1;
    }
}
