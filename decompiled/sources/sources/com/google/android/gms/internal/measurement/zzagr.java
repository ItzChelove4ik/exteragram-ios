package com.google.android.gms.internal.measurement;

import android.content.Context;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class zzagr {
    private static final zzph zza = null;
    private static final zzom zzb = null;
    private static volatile String zzc;
    private static final zzog zzd = null;

    static {
        zzpj r0 = new zzpj(zzagq.zza);
        r0.zza();
        zzph r02 = r0.zzb();
        zza = r02;
        zzog r1 = new zzog("com.google.android.gms.measurement", r02);
        zzd = r1;
        zzb = r1.zzd("__phenotype_server_token", _UrlKt.FRAGMENT_ENCODE_SET);
        zzc = null;
    }

    public static String zza() {
        return (String) zzb.get();
    }

    public static String zzb(Context r3) {
        String r0 = zzc;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(zzagr.class);
        String r02 = zzc;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = zzlg.zzb(r3, "com.google.android.gms.measurement", false);     // Catch: Throwable -> L9
        zzc = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(zzagr.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static zzog zzc() {
        return zzd;
    }
}
