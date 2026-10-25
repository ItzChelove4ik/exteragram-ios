package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzrl extends zzyd implements zzzj {
    private static final zzrl zzg = null;
    private int zzb;
    private int zzd;
    private Object zze;
    private long zzf;

    static {
        zzrl r0 = new zzrl();
        zzg = r0;
        zzyd.zzG(zzrl.class, r0);
    }

    private zzrl() {
        this.zzd = 0;
    }

    public static /* synthetic */ zzrl zza() {
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
        return new zzrk(null);
    L18:
        return new zzrl();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001စ\u0000\u0002:\u0000\u00035\u0000\u00048\u0000", new Object[]{"zze", "zzd", "zzb", "zzf"});
    L22:
        return (byte) 1;
    }
}
