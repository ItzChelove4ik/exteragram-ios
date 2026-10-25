package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zztf extends zzyd implements zzzj {
    private static final zztf zzg = null;
    private int zzb;
    private long zzd;
    private zzyk zze;
    private zzyk zzf;

    static {
        zztf r0 = new zztf();
        zzg = r0;
        zzyd.zzG(zztf.class, r0);
    }

    private zztf() {
        this.zze = zzyd.zzK();
        this.zzf = zzyd.zzK();
    }

    public static /* synthetic */ zztf zza() {
        return zzg;
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
        return zzg;
    L16:
        return new zzte(null);
    L18:
        return new zztf();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001စ\u0000\u0002\u0017\u0003\u0017", new Object[]{"zzb", "zzd", "zze", "zzf"});
    L22:
        return (byte) 1;
    }
}
