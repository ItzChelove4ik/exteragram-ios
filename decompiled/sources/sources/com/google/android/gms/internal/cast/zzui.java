package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzui extends zzyd implements zzzj {
    private static final zzui zzf = null;
    private int zzb;
    private int zzd;
    private int zze;

    static {
        zzui r0 = new zzui();
        zzf = r0;
        zzyd.zzG(zzui.class, r0);
    }

    private zzui() {
    }

    public static /* synthetic */ zzui zza() {
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
        return new zzuh(null);
    L18:
        return new zzui();
    L20:
        return zzyd.zzH(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zzb", "zzd", zznw.zza(), "zze", zznw.zza()});
    L22:
        return (byte) 1;
    }
}
