package com.google.android.gms.internal.mlkit_language_id_common;

import java.util.logging.Logger;

/* loaded from: classes4.dex */
abstract class zzj {
    private static final Logger zza = null;
    private static final zzi zzb = null;

    static {
        zza = Logger.getLogger(zzj.class.getName());
        zzb = new zzi(null);
    }

    public static boolean zza(String r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.isEmpty() == true) goto L10;
        return false;
    L10:
        return true;
    }
}
