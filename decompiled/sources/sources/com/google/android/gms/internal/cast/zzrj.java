package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzrj extends zzyd implements zzzj {
    private static final zzrj zzf = null;
    private int zzb;
    private int zzd;
    private int zze;

    static {
        zzrj r0 = new zzrj();
        zzf = r0;
        zzyd.zzG(zzrj.class, r0);
    }

    private zzrj() {
    }

    public static /* synthetic */ zzrj zza() {
        return zzf;
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
        return zzf;
    L16:
        return new zzri(null);
    L18:
        return new zzrj();
    L20:
        return zzyd.zzH(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zzb", "zzd", zzma.zza(), "zze", zzly.zza()});
    L22:
        return (byte) 1;
    }
}
