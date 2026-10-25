package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzvz extends zzyd implements zzzj {
    private static final zzvz zzg = null;
    private int zzb;
    private int zzd;
    private int zze;
    private long zzf;

    static {
        zzvz r0 = new zzvz();
        zzg = r0;
        zzyd.zzG(zzvz.class, r0);
    }

    private zzvz() {
    }

    public static /* synthetic */ zzvz zza() {
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
        return new zzvy(null);
    L18:
        return new zzvz();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001\u0003ဂ\u0002", new Object[]{"zzb", "zzd", zzpo.zza(), "zze", "zzf"});
    L22:
        return (byte) 1;
    }
}
