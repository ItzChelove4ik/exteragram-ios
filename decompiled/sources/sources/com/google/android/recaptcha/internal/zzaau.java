package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;

/* loaded from: classes5.dex */
public abstract class zzaau {
    static {
        int r0 = zzxf.zza;
        zza();     // Catch: GeneralSecurityException -> L5
        return;
    L5:
        e = move-exception;
        throw new ExceptionInInitializerError(e);
    }

    public static void zza() {
        zzzp.zzb();
        zzzr.zzb();
        zzyf.zza(true);
        zzaad.zza(true);
        zzaap.zza(true);
        if (zztf.zza() == false) goto L5;
        return;
    L5:
        zzyn.zza(true);
    }
}
