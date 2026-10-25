package com.google.android.gms.internal.play_billing;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public final class zzjl extends zzfi implements zzgm {
    private static final zzjl zzb = null;
    private int zzd;
    private zzfn zze;
    private int zzf;
    private String zzg;

    static {
        zzjl r0 = new zzjl();
        zzb = r0;
        zzfi.zzw(zzjl.class, r0);
    }

    private zzjl() {
        this.zze = zzfi.zzr();
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* bridge */ /* synthetic */ zzjl zzc() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r1, Object r2, Object r3) {
        int r12 = r1 - 1;
        if (r12 == 0) goto L22;
        if (r12 == 2) goto L20;
        if (r12 == 3) goto L18;
        if (r12 == 4) goto L16;
        if (r12 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zzjj(null);
    L18:
        return new zzjl();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001a\u0002င\u0000\u0003ဈ\u0001", new Object[]{"zzd", "zze", "zzf", "zzg"});
    L22:
        return (byte) 1;
    }
}
