package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzum extends zzyd implements zzzj {
    private static final zzum zzg = null;
    private int zzb;
    private long zzd;
    private long zze;
    private zzyl zzf;

    static {
        zzum r0 = new zzum();
        zzg = r0;
        zzyd.zzG(zzum.class, r0);
    }

    private zzum() {
        this.zzf = zzyd.zzM();
    }

    public static /* synthetic */ zzum zza() {
        return zzg;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r2, Object r3, Object r4) {
        int r22 = r2 - 1;
        if (r22 == 0) goto L22;
        if (r22 == 2) goto L20;
        if (r22 == 3) goto L18;
        if (r22 == 4) goto L16;
        if (r22 == 5) goto L13;
        throw null;
    L13:
        return zzg;
    L16:
        return new zzul(null);
    L18:
        return new zzum();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003\u001b", new Object[]{"zzb", "zzd", "zze", "zzf", zzuo.class});
    L22:
        return (byte) 1;
    }
}
