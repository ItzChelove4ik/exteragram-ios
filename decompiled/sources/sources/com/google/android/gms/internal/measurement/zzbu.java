package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public final class zzbu {
    private static volatile zzbu zza;
    private final List zzb;

    private zzbu() {
        this.zzb = new CopyOnWriteArrayList();
    }

    public static zzbu zza() {
        if (zza != null) goto L16;
        monitor-enter(zzbu.class);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (zza != null) goto L11;
        zza = new zzbu();     // Catch: Throwable -> L9
    L11:
        monitor-exit(zzbu.class);     // Catch: Throwable -> L9
    L16:
        return zza;
    }

    public final void zzb(zzbt r2) {
        this.zzb.add(0, r2);
    }
}
