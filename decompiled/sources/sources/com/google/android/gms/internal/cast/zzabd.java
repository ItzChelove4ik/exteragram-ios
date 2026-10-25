package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzabd extends zzyd implements zzzj {
    private static final zzabd zzd = null;
    private zzyl zzb;

    static {
        zzabd r0 = new zzabd();
        zzd = r0;
        zzyd.zzG(zzabd.class, r0);
    }

    private zzabd() {
        this.zzb = zzyd.zzM();
    }

    public static /* synthetic */ zzabd zza() {
        return zzd;
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
        return zzd;
    L16:
        return new zzabc(null);
    L18:
        return new zzabd();
    L20:
        return zzyd.zzH(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzb", zzabf.class});
    L22:
        return (byte) 1;
    }
}
