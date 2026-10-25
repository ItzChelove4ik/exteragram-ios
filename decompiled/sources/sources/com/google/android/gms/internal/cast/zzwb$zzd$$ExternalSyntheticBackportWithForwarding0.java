package com.google.android.gms.internal.cast;

import sun.misc.Unsafe;

/* loaded from: classes4.dex */
public abstract /* synthetic */ class zzwb$zzd$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ boolean m(Unsafe r1, Object r2, long r3, Object r5, Object r6) {
    L3:
        if (r1.compareAndSwapObject(r2, r3, r5, r6) == true) goto L4;
        if (r1.getObject(r2, r3) == r5) goto L3;
        return false;
    L4:
        return true;
    }
}
