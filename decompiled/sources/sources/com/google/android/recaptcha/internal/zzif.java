package com.google.android.recaptcha.internal;

import android.content.Context;
import android.os.Build;

/* loaded from: classes5.dex */
public final class zzif {
    private static final String zzc = "18.9.2";
    private static final int zzd = 0;
    private final Context zza;
    private final zzon zzb;
    private final zzhr zze;

    static {
        zzd = Build.VERSION.SDK_INT;
    }

    public zzif(Context r1, zzgp r2, zzhr r3, zzon r4, boolean r5) {
        this.zza = r1;
        this.zze = r3;
        this.zzb = r4;
    }

    public static final /* synthetic */ String zza() {
        return zzc;
    }

    public static final /* synthetic */ int zzb() {
        return zzd;
    }

    public static final /* synthetic */ zzon zzc(zzif r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ Context zzd(zzif r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzhr zze(zzif r0) {
        return r0.zze;
    }
}
