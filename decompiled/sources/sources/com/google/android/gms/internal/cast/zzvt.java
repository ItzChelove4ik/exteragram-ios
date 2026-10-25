package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzvt extends zzyd implements zzzj {
    private static final zzvt zzg = null;
    private int zzb;
    private int zzd;
    private long zze;
    private long zzf;

    static {
        zzvt r0 = new zzvt();
        zzg = r0;
        zzyd.zzG(zzvt.class, r0);
    }

    private zzvt() {
    }

    public static /* synthetic */ zzvt zza() {
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
        return new zzvs(null);
    L18:
        return new zzvt();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003ဂ\u0002", new Object[]{"zzb", "zzd", zzpk.zza(), "zze", "zzf"});
    L22:
        return (byte) 1;
    }
}
