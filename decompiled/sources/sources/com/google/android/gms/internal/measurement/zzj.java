package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final class zzj {
    final Map zza;

    public zzj() {
        this.zza = new HashMap();
    }

    public final void zza(String r1, Callable r2) {
        this.zza.put(r1, r2);
    }
}
