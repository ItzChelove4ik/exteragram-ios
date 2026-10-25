package com.google.android.recaptcha.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zztu {
    final Map zza;
    final Map zzb;

    public /* synthetic */ zztu(byte[] r1) {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    public final zztu zza(Enum r2, Object r3) {
        this.zza.put(r2, r3);
        this.zzb.put(r3, r2);
        return this;
    }

    public final zztv zzb() {
        return new zztv(Collections.unmodifiableMap(this.zza), Collections.unmodifiableMap(this.zzb), null);
    }
}
