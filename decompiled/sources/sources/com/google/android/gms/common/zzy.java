package com.google.android.gms.common;

import android.util.Log;

/* loaded from: classes4.dex */
public class zzy {
    private static final zzy zze = null;
    final boolean zza;
    final String zzb;
    final Throwable zzc;
    final int zzd;

    static {
        zze = new zzy(true, 3, 1, null, null, -1);
    }

    private zzy(boolean r1, int r2, int r3, String r4, Throwable r5, long r6) {
        this.zza = r1;
        this.zzd = r2;
        this.zzb = r4;
        this.zzc = r5;
    }

    public /* synthetic */ zzy(boolean r1, int r2, int r3, String r4, Throwable r5, long r6, byte[] r8) {
        this(false, 1, 5, null, null, -1);
    }

    @Deprecated
    public static zzy zzb() {
        return zze;
    }

    public static zzy zzc(String r8) {
        return new zzy(false, 1, 5, r8, null, -1);
    }

    public static zzy zzd(String r8, Throwable r9) {
        return new zzy(false, 1, 5, r8, r9, -1);
    }

    public static zzy zzf(int r8, long r9) {
        return new zzy(true, r8, 1, null, null, r9);
    }

    public static zzy zzg(int r8, int r9, String r10, Throwable r11) {
        return new zzy(false, r8, r9, r10, r11, -1);
    }

    public String zza() {
        return this.zzb;
    }

    public final void zze() {
        if (this.zza == false) goto L5;
        return;
    L5:
        if (Log.isLoggable("GoogleCertificatesRslt", 3) == false) goto L13;
        Throwable r0 = this.zzc;
        if (r0 == null) goto L10;
        Log.d("GoogleCertificatesRslt", zza(), r0);
        return;
    L10:
        Log.d("GoogleCertificatesRslt", zza());
        return;
    }
}
