package com.google.android.gms.internal.play_billing;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzjd extends zzfi implements zzgm {
    private static final zzjd zzb = null;
    private int zzd;
    private int zze;
    private String zzf;

    static {
        zzjd r0 = new zzjd();
        zzb = r0;
        zzfi.zzw(zzjd.class, r0);
    }

    private zzjd() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* bridge */ /* synthetic */ zzjd zzc() {
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
        return new zzjb(null);
    L18:
        return new zzjd();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", zzjc.zza, "zzf"});
    L22:
        return (byte) 1;
    }
}
