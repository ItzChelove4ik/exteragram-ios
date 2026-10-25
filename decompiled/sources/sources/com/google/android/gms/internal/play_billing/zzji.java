package com.google.android.gms.internal.play_billing;

/* loaded from: classes.dex */
public final class zzji extends zzfi implements zzgm {
    private static final zzji zzb = null;
    private int zzd;
    private int zze;
    private Object zzf;
    private zzis zzg;
    private zziv zzh;

    static {
        zzji r0 = new zzji();
        zzb = r0;
        zzfi.zzw(zzji.class, r0);
    }

    private zzji() {
        this.zze = 0;
    }

    public static /* synthetic */ void zzA(zzji r0, zzhx r1) {
        r0.zzf = r1;
        r0.zze = 2;
    }

    public static /* synthetic */ void zzB(zzji r0, zzib r1) {
        r0.zzf = r1;
        r0.zze = 3;
    }

    public static /* synthetic */ void zzC(zzji r0, zzij r1) {
        r1.getClass();
        r0.zzf = r1;
        r0.zze = 7;
    }

    public static /* synthetic */ void zzD(zzji r0, zzis r1) {
        r1.getClass();
        r0.zzg = r1;
        r0.zzd |= 1;
    }

    public static /* synthetic */ void zzE(zzji r0, zzjo r1) {
        r1.getClass();
        r0.zzf = r1;
        r0.zze = 8;
    }

    public static /* synthetic */ void zzF(zzji r0, zzjs r1) {
        r0.zzf = r1;
        r0.zze = 4;
    }

    public static zzjg zzc() {
        return (zzjg) zzb.zzl();
    }

    public static /* bridge */ /* synthetic */ zzji zzd() {
        return zzb;
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
        return new zzjg(null);
    L18:
        return new zzji();
    L20:
        return zzfi.zzt(zzb, "\u0004\b\u0001\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဉ\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006ဉ\u0001\u0007<\u0000\b<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", zzhx.class, zzib.class, zzjs.class, zzip.class, "zzh", zzij.class, zzjo.class});
    L22:
        return (byte) 1;
    }
}
