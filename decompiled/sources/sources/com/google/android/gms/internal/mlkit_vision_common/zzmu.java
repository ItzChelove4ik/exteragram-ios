package com.google.android.gms.internal.mlkit_vision_common;

import android.os.SystemClock;

/* loaded from: classes4.dex */
public abstract class zzmu {
    public static void zza(zzmj r0, int r1, int r2, long r3, int r5, int r6, int r7, int r8) {
        r0.zzc(zzc(r1, r2, r3, r5, r6, r7, r8), zziv.zzbA);
    }

    private static zzmt zzc(int r11, int r12, long r13, int r15, int r16, int r17, int r18) {
        return new zzmt(r11, r12, r17, r15, r16, SystemClock.elapsedRealtime() - r13, r18);
    }
}
