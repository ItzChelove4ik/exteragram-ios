package com.google.android.gms.internal.mlkit_common;

import com.chaquo.python.internal.Common;

/* loaded from: classes4.dex */
public abstract class zzss {
    private static zzsr zza;

    public static synchronized zzsh zza(zzsb r3) {
        monitor-enter(zzss.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zza = new zzsr(null);     // Catch: Throwable -> L7
    L9:
        zzsh r32 = (zzsh) zza.get(r3);     // Catch: Throwable -> L7
        monitor-exit(zzss.class);
        return r32;
    }

    public static synchronized zzsh zzb(String r1) {
        monitor-enter(zzss.class);
        zzsh r0 = zza(zzsb.zzd(Common.ABI_COMMON).zzd());     // Catch: Throwable -> L7
        monitor-exit(zzss.class);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }
}
