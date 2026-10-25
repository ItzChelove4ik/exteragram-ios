package com.google.android.gms.measurement.internal;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zzot {
    private final String zza;
    private final Map zzb;
    private final zzls zzc;
    private final com.google.android.gms.internal.measurement.zzis zzd;

    public zzot(String r1, Map r2, zzls r3, com.google.android.gms.internal.measurement.zzis r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final String zza() {
        return this.zza;
    }

    public final Map zzb() {
        Map r0 = this.zzb;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return Collections.EMPTY_MAP;
    }

    public final zzls zzc() {
        return this.zzc;
    }

    public final com.google.android.gms.internal.measurement.zzis zzd() {
        return this.zzd;
    }
}
