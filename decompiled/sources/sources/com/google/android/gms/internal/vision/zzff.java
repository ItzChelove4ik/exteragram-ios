package com.google.android.gms.internal.vision;

import c.f$$ExternalSyntheticBUOutline2;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
final class zzff extends WeakReference<Throwable> {
    private final int zza;

    public zzff(Throwable r1, ReferenceQueue<Throwable> r2) {
        super(r1, r2);
        if (r1 == null) goto L6;
        this.zza = System.identityHashCode(r1);
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("The referent cannot be null");
        throw null;
    }

    public final boolean equals(Object r5) {
        if (r5 != null) goto L5;
    L15:
        return false;
    L5:
        if (r5.getClass() != zzff.class) goto L15;
        if (this != r5) goto L10;
        return true;
    L10:
        zzff r52 = (zzff) r5;
        if (this.zza != r52.zza) goto L15;
        if (get() != r52.get()) goto L15;
        return true;
    }

    public final int hashCode() {
        return this.zza;
    }
}
