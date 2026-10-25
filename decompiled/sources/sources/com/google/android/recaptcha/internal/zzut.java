package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes5.dex */
public final class zzut {
    private static final zzut zzb = null;
    private final ConcurrentMap zza;

    static {
        zzb = new zzut();
    }

    public zzut() {
        this.zza = new ConcurrentHashMap();
    }

    public static zzut zza() {
        return zzb;
    }

    public final void zzb(zzus r1, Class r2) {
        zzus r0 = (zzus) this.zza.putIfAbsent(r2, r1);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.equals(r1) == true) goto L10;
        zzsj$$ExternalSyntheticBUOutline1.m("Different key creator for parameters class already inserted");
        return;
    }
}
