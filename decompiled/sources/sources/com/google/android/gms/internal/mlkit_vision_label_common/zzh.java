package com.google.android.gms.internal.mlkit_vision_label_common;

import java.util.logging.Logger;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class zzh {
    private static final Logger zza = null;
    private static final zzg zzb = null;

    static {
        zza = Logger.getLogger(zzh.class.getName());
        zzb = new zzg(null);
    }

    public static String zza(String r0) {
        if (r0 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r0;
    }
}
