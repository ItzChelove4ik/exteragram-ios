package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zzte {
    public static boolean zza(int r2) {
        if ((r2 - 1) == 0) goto L12;
        if (zztf.zza() == true) goto L7;
    L10:
        return true;
    L7:
        if (zztf.zzb().booleanValue() == true) goto L10;
        return false;
    L12:
        if (zztf.zza() == true) goto L14;
        return true;
    L14:
        return false;
    }
}
