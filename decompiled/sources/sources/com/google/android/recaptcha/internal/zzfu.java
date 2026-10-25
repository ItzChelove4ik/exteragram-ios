package com.google.android.recaptcha.internal;

import java.util.Arrays;

/* loaded from: classes5.dex */
public interface zzfu {
    Object zza(Object... r1);

    default Object zzb(Object[] r3) {
        int r0 = r3.length;
        if (r0 != 1) goto L10;
        if ((r3[0] instanceof zzfv) == true) goto L8;
        r0 = 1;
        goto L10
    L8:
        return Boolean.TRUE;
    L10:
        return zza(Arrays.copyOf(r3, r0));
    }
}
