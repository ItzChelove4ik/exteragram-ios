package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzvr extends zzyd implements zzzj {
    private static final zzvr zzl = null;
    private int zzb;
    private int zzd;
    private int zze;
    private zzyl zzf;
    private zzyl zzg;
    private int zzh;
    private zzyj zzi;
    private boolean zzj;
    private boolean zzk;

    static {
        zzvr r0 = new zzvr();
        zzl = r0;
        zzyd.zzG(zzvr.class, r0);
    }

    private zzvr() {
        this.zzf = zzyd.zzM();
        this.zzg = zzyd.zzM();
        this.zzi = zzyd.zzJ();
    }

    public static /* synthetic */ zzvr zza() {
        return zzl;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r17, Object r18, Object r19) {
        int r0 = r17 - 1;
        if (r0 == 0) goto L22;
        if (r0 == 2) goto L20;
        if (r0 == 3) goto L18;
        if (r0 == 4) goto L16;
        if (r0 == 5) goto L13;
        throw null;
    L13:
        return zzl;
    L16:
        return new zzvq(null);
    L18:
        return new zzvr();
    L20:
        return zzyd.zzH(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0003\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003\u001b\u0004\u001b\u0005᠌\u0002\u0006ࠬ\u0007ဇ\u0003\bဇ\u0004", new Object[]{"zzb", "zzd", zzpe.zza(), "zze", zzpg.zza(), "zzf", zzrp.class, "zzg", zzrp.class, "zzh", zzmi.zza(), "zzi", zzpe.zza(), "zzj", "zzk"});
    L22:
        return (byte) 1;
    }
}
