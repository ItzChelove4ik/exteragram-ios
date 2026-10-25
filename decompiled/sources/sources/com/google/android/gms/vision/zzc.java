package com.google.android.gms.vision;

import android.util.SparseIntArray;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* loaded from: classes5.dex */
public final class zzc {
    private static final Object zza = null;
    private static int zzb;
    private final SparseIntArray zzc;
    private final SparseIntArray zzd;

    static {
        zza = new Object();
    }

    public zzc() {
        this.zzc = new SparseIntArray();
        this.zzd = new SparseIntArray();
    }

    public final int zza(int r4) {
        Object r0 = zza;
        monitor-enter(r0);
        int r1 = this.zzc.get(r4, -1);     // Catch: Throwable -> L8
        if (r1 == (-1)) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r1;
    L10:
        int r12 = zzb;     // Catch: Throwable -> L8
        zzb = r12 + 1;     // Catch: Throwable -> L8
        this.zzc.append(r4, r12);     // Catch: Throwable -> L8
        this.zzd.append(r12, r4);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r12;
    L8:
        th = move-exception;
        throw th;
    }
}
