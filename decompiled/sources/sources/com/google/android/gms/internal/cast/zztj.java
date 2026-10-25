package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zztj extends zzyd implements zzzj {
    private static final zztj zzg = null;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zztj r0 = new zztj();
        zzg = r0;
        zzyd.zzG(zztj.class, r0);
    }

    private zztj() {
    }

    public static /* synthetic */ zztj zza() {
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
        return new zzti(null);
    L18:
        return new zztj();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zzb", "zzd", "zze", "zzf"});
    L22:
        return (byte) 1;
    }
}
