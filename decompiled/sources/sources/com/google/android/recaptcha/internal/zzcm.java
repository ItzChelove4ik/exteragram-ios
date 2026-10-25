package com.google.android.recaptcha.internal;

import kotlinx.coroutines.TimeoutCancellationException;

/* loaded from: classes5.dex */
public abstract class zzcm {
    public static final zzhg zza(Exception r8, zzhg r9) {
        if ((r8 instanceof TimeoutCancellationException) == false) goto L7;
        return new zzhg(zzhf.zzb, zzhe.zzb, r8.getMessage(), null, 8, null);
    L7:
        if ((r8 instanceof zzhg) == true) goto L9;
        return r9;
    L9:
        return (zzhg) r8;
    }
}
