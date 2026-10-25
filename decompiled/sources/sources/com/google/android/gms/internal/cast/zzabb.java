package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzabb extends zzyd implements zzzj {
    private static final zzabb zzg = null;
    private zzyl zzb;
    private zzyl zzd;
    private zzyl zze;
    private zzyl zzf;

    static {
        zzabb r0 = new zzabb();
        zzg = r0;
        zzyd.zzG(zzabb.class, r0);
    }

    private zzabb() {
        this.zzb = zzyd.zzM();
        this.zzd = zzyd.zzM();
        this.zze = zzyd.zzM();
        this.zzf = zzyd.zzM();
    }

    public static /* synthetic */ zzabb zza() {
        return zzg;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r9, Object r10, Object r11) {
        int r92 = r9 - 1;
        if (r92 == 0) goto L22;
        if (r92 == 2) goto L20;
        if (r92 == 3) goto L18;
        if (r92 == 4) goto L16;
        if (r92 == 5) goto L13;
        throw null;
    L13:
        return zzg;
    L16:
        return new zzaba(null);
    L18:
        return new zzabb();
    L20:
        return zzyd.zzH(zzg, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u001b\u0002\u001b\u0003\u001b\u0004\u001b", new Object[]{"zzb", zzaaz.class, "zzd", zzaav.class, "zze", zzaaz.class, "zzf", zzaav.class});
    L22:
        return (byte) 1;
    }
}
