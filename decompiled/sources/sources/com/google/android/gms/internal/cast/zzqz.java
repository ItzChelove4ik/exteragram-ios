package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzqz extends zzyd implements zzzj {
    private static final zzqz zzm = null;
    private int zzb;
    private zzrp zzd;
    private long zze;
    private int zzf;
    private zzyl zzg;
    private zzyl zzh;
    private zzyl zzi;
    private zzyl zzj;
    private zzyl zzk;
    private int zzl;

    static {
        zzqz r0 = new zzqz();
        zzm = r0;
        zzyd.zzG(zzqz.class, r0);
    }

    private zzqz() {
        this.zzg = zzyd.zzM();
        this.zzh = zzyd.zzM();
        this.zzi = zzyd.zzM();
        this.zzj = zzyd.zzM();
        this.zzk = zzyd.zzM();
    }

    public static zzqy zza() {
        return (zzqy) zzm.zzB();
    }

    public static /* synthetic */ zzqz zzk() {
        return zzm;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r18, Object r19, Object r20) {
        int r0 = r18 - 1;
        if (r0 == 0) goto L22;
        if (r0 == 2) goto L20;
        if (r0 == 3) goto L18;
        if (r0 == 4) goto L16;
        if (r0 == 5) goto L13;
        throw null;
    L13:
        return zzm;
    L16:
        return new zzqy(null);
    L18:
        return new zzqz();
    L20:
        return zzyd.zzH(zzm, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0005\u0000\u0001ဉ\u0000\u0002စ\u0001\u0003᠌\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007\u001b\b\u001b\tင\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", zzoy.zza(), "zzg", zzqx.class, "zzh", zzqt.class, "zzi", zzrd.class, "zzj", zzrb.class, "zzk", zzqv.class, "zzl"});
    L22:
        return (byte) 1;
    }

    public final /* synthetic */ void zzc(zzrp r1) {
        r1.getClass();
        this.zzd = r1;
        this.zzb |= 1;
    }

    public final /* synthetic */ void zzd(long r2) {
        this.zzb |= 2;
        this.zze = r2;
    }

    public final /* synthetic */ void zze(Iterable r3) {
        zzyl r0 = this.zzg;
        if (r0.zza() == true) goto L5;
        this.zzg = zzyd.zzN(r0);
    L5:
        zzwz.zzu(r3, this.zzg);
    }

    public final /* synthetic */ void zzf(Iterable r3) {
        zzyl r0 = this.zzh;
        if (r0.zza() == true) goto L5;
        this.zzh = zzyd.zzN(r0);
    L5:
        zzwz.zzu(r3, this.zzh);
    }

    public final /* synthetic */ void zzg(Iterable r3) {
        zzyl r0 = this.zzi;
        if (r0.zza() == true) goto L5;
        this.zzi = zzyd.zzN(r0);
    L5:
        zzwz.zzu(r3, this.zzi);
    }

    public final /* synthetic */ void zzh(Iterable r3) {
        zzyl r0 = this.zzj;
        if (r0.zza() == true) goto L5;
        this.zzj = zzyd.zzN(r0);
    L5:
        zzwz.zzu(r3, this.zzj);
    }

    public final /* synthetic */ void zzi(Iterable r3) {
        zzyl r0 = this.zzk;
        if (r0.zza() == true) goto L5;
        this.zzk = zzyd.zzN(r0);
    L5:
        zzwz.zzu(r3, this.zzk);
    }

    public final /* synthetic */ void zzj(int r2) {
        this.zzb |= 8;
        this.zzl = r2;
    }
}
