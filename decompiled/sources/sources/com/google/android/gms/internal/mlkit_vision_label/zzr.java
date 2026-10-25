package com.google.android.gms.internal.mlkit_vision_label;

import java.util.logging.Logger;

/* loaded from: classes4.dex */
abstract class zzr {
    private static final Logger zza = null;
    private static final zzq zzb = null;

    static {
        zza = Logger.getLogger(zzr.class.getName());
        zzb = new zzq(null);
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
