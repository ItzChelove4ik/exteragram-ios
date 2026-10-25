package com.google.android.gms.internal.play_billing;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzjf extends zzfi implements zzgm {
    private static final zzjf zzb = null;
    private int zzd;
    private zzfn zze;
    private String zzf;
    private boolean zzg;

    static {
        zzjf r0 = new zzjf();
        zzb = r0;
        zzfi.zzw(zzjf.class, r0);
    }

    private zzjf() {
        this.zze = zzfi.zzr();
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ void zzA(zzjf r1, boolean r2) {
        r1.zzd |= 2;
        r1.zzg = r2;
    }

    public static /* bridge */ /* synthetic */ zzjf zzc() {
        return zzb;
    }

    public static zzjf zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r2, Object r3, Object r4) {
        int r22 = r2 - 1;
        if (r22 == 0) goto L22;
        if (r22 == 2) goto L20;
        if (r22 == 3) goto L18;
        if (r22 == 4) goto L16;
        if (r22 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zzja(null);
    L18:
        return new zzjf();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဇ\u0001", new Object[]{"zzd", "zze", zzjd.class, "zzf", "zzg"});
    L22:
        return (byte) 1;
    }
}
