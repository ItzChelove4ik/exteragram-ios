package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
public final class zzst extends zzyd implements zzzj {
    private static final zzst zzh = null;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private byte zzg;

    static {
        zzst r0 = new zzst();
        zzh = r0;
        zzyd.zzG(zzst.class, r0);
    }

    private zzst() {
        this.zzg = 2;
    }

    public static /* synthetic */ zzst zza() {
        return zzh;
    }

    @Override // com.google.android.gms.internal.cast.zzyd
    public final Object zzb(int r7, Object r8, Object r9) {
        int r72 = r7 - 1;
        if (r72 == 0) goto L26;
        if (r72 == 2) goto L24;
        if (r72 == 3) goto L22;
        if (r72 == 4) goto L20;
        if (r72 == 5) goto L18;
        if (r8 != null) goto L14;
        byte r73 = 0;
    L15:
        this.zzg = r73;
        return null;
    L14:
        r73 = 1;
        goto L15
    L18:
        return zzh;
    L20:
        return new zzss(null);
    L22:
        return new zzst();
    L24:
        return zzyd.zzH(zzh, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001ᴌ\u0000\u0002င\u0001\u0003᠌\u0002", new Object[]{"zzb", "zzd", zzmy.zza(), "zze", "zzf", zzps.zza()});
    L26:
        return Byte.valueOf(this.zzg);
    }
}
