package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzvv extends zzyd implements zzzj {
    private static final zzvv zzj = null;
    private int zzb;
    private int zzd;
    private int zze;
    private zzyj zzf;
    private int zzg;
    private zzyl zzh;
    private long zzi;

    static {
        zzvv r0 = new zzvv();
        zzj = r0;
        zzyd.zzG(zzvv.class, r0);
    }

    private zzvv() {
        this.zzf = zzyd.zzJ();
        this.zzh = zzyd.zzM();
    }

    public static /* synthetic */ zzvv zza() {
        return zzj;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r13, Object r14, Object r15) {
        int r132 = r13 - 1;
        if (r132 == 0) goto L22;
        if (r132 == 2) goto L20;
        if (r132 == 3) goto L18;
        if (r132 == 4) goto L16;
        if (r132 == 5) goto L13;
        throw null;
    L13:
        return zzj;
    L16:
        return new zzvu(null);
    L18:
        return new zzvv();
    L20:
        return zzyd.zzH(zzj, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0002\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003ࠞ\u0005᠌\u0002\u0006\u001b\u0007ဂ\u0003", new Object[]{"zzb", "zzd", zzpk.zza(), "zze", zzmi.zza(), "zzf", zzpi.zza(), "zzg", zzlw.zza(), "zzh", zzvt.class, "zzi"});
    L22:
        return (byte) 1;
    }
}
