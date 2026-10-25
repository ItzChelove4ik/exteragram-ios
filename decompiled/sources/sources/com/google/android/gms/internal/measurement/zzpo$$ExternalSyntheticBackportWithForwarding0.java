package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: classes.dex */
public abstract /* synthetic */ class zzpo$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ boolean m(AtomicReferenceArray r1, int r2, Object r3, Object r4) {
    L3:
        if (r1.compareAndSet(r2, r3, r4) == true) goto L4;
        if (r1.get(r2) == r3) goto L3;
        return false;
    L4:
        return true;
    }
}
