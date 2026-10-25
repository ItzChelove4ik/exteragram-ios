package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzqx extends zzyd implements zzzj {
    private static final zzqx zzi = null;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private boolean zzg;
    private long zzh;

    static {
        zzqx r0 = new zzqx();
        zzi = r0;
        zzyd.zzG(zzqx.class, r0);
    }

    private zzqx() {
    }

    public static zzqw zza() {
        return (zzqw) zzi.zzB();
    }

    public static /* synthetic */ zzqx zzg() {
        return zzi;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L22;
        if (r82 == 2) goto L20;
        if (r82 == 3) goto L18;
        if (r82 == 4) goto L16;
        if (r82 == 5) goto L13;
        throw null;
    L13:
        return zzi;
    L16:
        return new zzqw(null);
    L18:
        return new zzqx();
    L20:
        return zzyd.zzH(zzi, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003င\u0002\u0004ဇ\u0003\u0006ဂ\u0004", new Object[]{"zzb", "zzd", zzos.zza(), "zze", "zzf", "zzg", "zzh"});
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

    public final /* synthetic */ void zze(boolean r2) {
        this.zzb |= 8;
        this.zzg = r2;
    }

    public final /* synthetic */ void zzf(long r2) {
        this.zzb |= 16;
        this.zzh = r2;
    }

    public final /* synthetic */ void zzh(int r1) {
        this.zzd = r1 - 1;
        this.zzb |= 1;
    }
}
