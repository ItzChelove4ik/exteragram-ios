package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzrd extends zzyd implements zzzj {
    private static final zzrd zzf = null;
    private int zzb;
    private int zzd;
    private int zze;

    static {
        zzrd r0 = new zzrd();
        zzf = r0;
        zzyd.zzG(zzrd.class, r0);
    }

    private zzrd() {
    }

    public static zzrc zza() {
        return (zzrc) zzf.zzB();
    }

    public static /* synthetic */ zzrd zzd() {
        return zzf;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L22;
        if (r12 == 2) goto L20;
        if (r12 == 3) goto L18;
        if (r12 == 4) goto L16;
        if (r12 == 5) goto L13;
        throw null;
    L13:
        return zzf;
    L16:
        return new zzrc(null);
    L18:
        return new zzrd();
    L20:
        return zzyd.zzH(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"zzb", "zzd", zzpa.zza(), "zze"});
    L22:
        return (byte) 1;
    }

    public final /* synthetic */ void zzc(int r2) {
        this.zzb |= 2;
        this.zze = r2;
    }

    public final /* synthetic */ void zze(int r1) {
        this.zzd = r1 - 1;
        this.zzb |= 1;
    }
}
