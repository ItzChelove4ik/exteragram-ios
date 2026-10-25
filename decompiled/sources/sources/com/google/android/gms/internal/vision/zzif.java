package com.google.android.gms.internal.vision;

import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class zzif {
    int zzb;
    private int zzd;
    private boolean zze;

    private zzif() {
        this.zzb = 100;
        this.zzd = Integer.MAX_VALUE;
        this.zze = false;
    }

    public /* synthetic */ zzif(zzie r1) {
        this();
    }

    public static long zza(long r4) {
        long r0 = r4 >>> 1;
        return (-(r4 & 1)) ^ r0;
    }

    public static zzif zza(byte[] r6, int r7, int r8, boolean r9) {
        zzih r0 = new zzih(r6, 0, r8, false, null);
        r0.zzc(r8);     // Catch: zzjk -> L5
        return r0;
    L5:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static int zze(int r1) {
        int r0 = r1 >>> 1;
        return (-(r1 & 1)) ^ r0;
    }

    public abstract int zzc(int r1);

    public abstract int zzu();
}
