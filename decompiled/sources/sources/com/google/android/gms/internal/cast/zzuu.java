package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzuu extends zzyd implements zzzj {
    private static final zzuu zzh = null;
    private int zzb;
    private long zzd;
    private boolean zze;
    private long zzf;
    private boolean zzg;

    static {
        zzuu r0 = new zzuu();
        zzh = r0;
        zzyd.zzG(zzuu.class, r0);
    }

    private zzuu() {
    }

    public static /* synthetic */ zzuu zza() {
        return zzh;
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
        return zzh;
    L16:
        return new zzut(null);
    L18:
        return new zzuu();
    L20:
        return zzyd.zzH(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဇ\u0001\u0003ဂ\u0002\u0004ဇ\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg"});
    L22:
        return (byte) 1;
    }
}
