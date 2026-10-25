package com.google.android.gms.internal.play_billing;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzdn extends zzfi implements zzgm {
    private static final zzdn zzb = null;
    private int zzd;
    private String zze;

    static {
        zzdn r0 = new zzdn();
        zzb = r0;
        zzfi.zzw(zzdn.class, r0);
    }

    private zzdn() {
        this.zze = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* bridge */ /* synthetic */ zzdn zza() {
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
        return new zzdm(null);
    L18:
        return new zzdn();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzd", "zze"});
    L22:
        return (byte) 1;
    }
}
