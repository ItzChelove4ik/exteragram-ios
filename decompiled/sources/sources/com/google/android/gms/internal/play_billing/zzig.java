package com.google.android.gms.internal.play_billing;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzig extends zzfi implements zzgm {
    private static final zzig zzb = null;
    private int zzd;
    private int zze;
    private String zzf;
    private int zzg;
    private String zzh;
    private int zzi;

    static {
        zzig r0 = new zzig();
        zzb = r0;
        zzfi.zzw(zzig.class, r0);
    }

    private zzig() {
        this.zzf = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ void zzA(zzig r1, String r2) {
        r1.zzd |= 8;
        r1.zzh = r2;
    }

    public static /* synthetic */ void zzB(zzig r1, String r2) {
        r2.getClass();
        r1.zzd |= 2;
        r1.zzf = r2;
    }

    public static /* synthetic */ void zzC(zzig r1, int r2) {
        r1.zzd |= 16;
        r1.zzi = r2;
    }

    public static /* synthetic */ void zzD(zzig r0, zzie r1) {
        r0.zzg = r1.zza();
        r0.zzd |= 4;
    }

    public static /* synthetic */ void zzE(zzig r1, int r2) {
        r1.zzd |= 1;
        r1.zze = r2;
    }

    public static zzic zzc() {
        return (zzic) zzb.zzl();
    }

    public static /* bridge */ /* synthetic */ zzig zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfi
    public final Object zzb(int r8, Object r9, Object r10) {
        int r82 = r8 - 1;
        if (r82 == 0) goto L22;
        if (r82 == 2) goto L20;
        if (r82 == 3) goto L18;
        if (r82 == 4) goto L16;
        if (r82 == 5) goto L13;
        throw null;
    L13:
        return zzb;
    L16:
        return new zzic(null);
    L18:
        return new zzig();
    L20:
        return zzfi.zzt(zzb, "\u0004\u0005\u0000\u0001\u0001\u0007\u0005\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0004᠌\u0002\u0005ဈ\u0003\u0007င\u0004", new Object[]{"zzd", "zze", "zzf", "zzg", zzid.zza, "zzh", "zzi"});
    L22:
        return (byte) 1;
    }
}
