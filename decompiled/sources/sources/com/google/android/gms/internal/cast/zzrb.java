package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzrb extends zzyd implements zzzj {
    private static final zzrb zzh = null;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzrb r0 = new zzrb();
        zzh = r0;
        zzyd.zzG(zzrb.class, r0);
    }

    private zzrb() {
    }

    public static zzra zza() {
        return (zzra) zzh.zzB();
    }

    public static /* synthetic */ zzrb zzf() {
        return zzh;
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
        return zzh;
    L16:
        return new zzra(null);
    L18:
        return new zzrb();
    L20:
        return zzyd.zzH(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zzb", "zzd", zzou.zza(), "zze", "zzf", "zzg"});
    L22:
        return (byte) 1;
    }

    public final /* synthetic */ void zzc(int r2) {
        this.zzb |= 2;
        this.zze = r2;
    }

    public final /* synthetic */ void zzd(int r2) {
        this.zzb |= 4;
        this.zzf = r2;
    }

    public final /* synthetic */ void zze(int r2) {
        this.zzb |= 8;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzg(int r1) {
        this.zzd = r1 - 1;
        this.zzb |= 1;
    }
}
