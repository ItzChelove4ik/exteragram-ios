package com.google.android.recaptcha.internal;

import java.nio.ByteOrder;
import java.util.Objects;

/* loaded from: classes5.dex */
abstract class zzrn {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzrk zzb = null;

    static {
        if (Objects.equals(System.getProperty("os.arch"), "amd64") == false) goto L9;
        zzrk r0 = zzrj.zza;
    L10:
        zzb = r0;
        return;
    L5:
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN) == false) goto L7;
        r0 = zzrm.zza;     // Catch: Throwable -> L12
        goto L10
    L7:
        r0 = zzrm.zzb;     // Catch: Throwable -> L12
    L9:
        r0 = zzrj.zza;
        goto L10
    }

    public static long zza(byte[] r1, int r2) {
        return zzb.zza(r1, r2);
    }
}
