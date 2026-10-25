package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzvn extends zzyd implements zzzj {
    private static final zzvn zzf = null;
    private int zzb;
    private int zzd;
    private zztd zze;

    static {
        zzvn r0 = new zzvn();
        zzf = r0;
        zzyd.zzG(zzvn.class, r0);
    }

    private zzvn() {
    }

    public static /* synthetic */ zzvn zza() {
        return zzf;
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
        return zzf;
    L16:
        return new zzvm(null);
    L18:
        return new zzvn();
    L20:
        return zzyd.zzH(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဉ\u0001", new Object[]{"zzb", "zzd", zzsb.zza, "zze"});
    L22:
        return (byte) 1;
    }
}
