package com.google.android.gms.measurement.internal;

import java.util.Comparator;

/* loaded from: classes5.dex */
final /* synthetic */ class zzld implements Comparator {
    static final /* synthetic */ zzld zza = null;

    static {
        zza = new zzld();
    }

    private /* synthetic */ zzld() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object r3, Object r4) {
        return Long.compare(((Long) r3).longValue(), ((Long) r4).longValue());
    }
}
