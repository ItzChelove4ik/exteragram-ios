package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzuk extends zzyd implements zzzj {
    private static final zzuk zzk = null;
    private int zzb;
    private zzrp zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private long zzi;
    private zzyl zzj;

    static {
        zzuk r0 = new zzuk();
        zzk = r0;
        zzyd.zzG(zzuk.class, r0);
    }

    private zzuk() {
        this.zzj = zzyd.zzM();
    }

    public static /* synthetic */ zzuk zza() {
        return zzk;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r14, Object r15, Object r16) {
        int r142 = r14 - 1;
        if (r142 == 0) goto L22;
        if (r142 == 2) goto L20;
        if (r142 == 3) goto L18;
        if (r142 == 4) goto L16;
        if (r142 == 5) goto L13;
        throw null;
    L13:
        return zzk;
    L16:
        return new zzuj(null);
    L18:
        return new zzuk();
    L20:
        return zzyd.zzH(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006ဂ\u0005\u0007\u001b", new Object[]{"zzb", "zzd", "zze", zzoa.zza(), "zzf", zzny.zza(), "zzg", zzmi.zza(), "zzh", zzlm.zza(), "zzi", "zzj", zzrp.class});
    L22:
        return (byte) 1;
    }
}
