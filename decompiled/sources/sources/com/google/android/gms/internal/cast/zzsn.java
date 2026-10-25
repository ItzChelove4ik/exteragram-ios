package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzsn extends zzyd implements zzzj {
    private static final zzsn zzk = null;
    private int zzb;
    private zzyl zzd;
    private boolean zze;
    private boolean zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    private boolean zzj;

    static {
        zzsn r0 = new zzsn();
        zzk = r0;
        zzyd.zzG(zzsn.class, r0);
    }

    private zzsn() {
        this.zzd = zzyd.zzM();
    }

    public static /* synthetic */ zzsn zza() {
        return zzk;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r10, Object r11, Object r12) {
        int r102 = r10 - 1;
        if (r102 == 0) goto L22;
        if (r102 == 2) goto L20;
        if (r102 == 3) goto L18;
        if (r102 == 4) goto L16;
        if (r102 == 5) goto L13;
        throw null;
    L13:
        return zzk;
    L16:
        return new zzsm(null);
    L18:
        return new zzsn();
    L20:
        return zzyd.zzH(zzk, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001\u001b\u0002ဇ\u0000\u0003ဇ\u0001\u0004ဂ\u0002\u0005ဂ\u0003\u0006ဂ\u0004\u0007ဇ\u0005", new Object[]{"zzb", "zzd", zzrx.class, "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
    L22:
        return (byte) 1;
    }
}
