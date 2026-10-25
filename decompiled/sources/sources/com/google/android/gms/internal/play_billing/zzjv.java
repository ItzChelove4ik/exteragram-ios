package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
public final class zzjv extends zzfi implements zzgm {
    private static final zzjv zzb = null;
    private int zzd;
    private int zze;
    private boolean zzf;
    private long zzg;
    private boolean zzh;
    private int zzi;

    static {
        zzjv r0 = new zzjv();
        zzb = r0;
        zzfi.zzw(zzjv.class, r0);
    }

    private zzjv() {
    }

    public static /* synthetic */ void zzA(zzjv r1, boolean r2) {
        r1.zzd |= 8;
        r1.zzh = r2;
    }

    public static /* synthetic */ void zzB(zzjv r1, int r2) {
        r1.zzd |= 16;
        r1.zzi = r2;
    }

    public static /* synthetic */ void zzC(zzjv r1, long r2) {
        r1.zzd |= 4;
        r1.zzg = r2;
    }

    public static /* synthetic */ void zzD(zzjv r0, boolean r1) {
        r0.zzd |= 2;
        r0.zzf = true;
    }

    public static zzjt zzc() {
        return (zzjt) zzb.zzl();
    }

    public static /* bridge */ /* synthetic */ zzjv zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r7, Object r8, Object r9) {
        int r72 = r7 - 1;
        if (r72 == 0) goto L22;
        if (r72 == 2) goto L20;
        if (r72 == 3) goto L18;
        if (r72 == 4) goto L16;
        if (r72 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zzjt(null);
    L18:
        return new zzjv();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001င\u0000\u0002ဇ\u0001\u0003ဂ\u0002\u0004ဇ\u0003\u0005င\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
    L22:
        return (byte) 1;
    }
}
