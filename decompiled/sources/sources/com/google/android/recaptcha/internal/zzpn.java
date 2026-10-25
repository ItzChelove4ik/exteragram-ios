package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
abstract class zzpn {
    public static int zza(int r4) {
        return (int) (Integer.rotateLeft((int) (r4 * (-862048943)), 15) * 461845907);
    }

    public static int zzb(Object r0) {
        if (r0 != null) goto L4;
        int r02 = 0;
    L6:
        return zza(r02);
    L4:
        r02 = r0.hashCode();
        goto L6
    }
}
