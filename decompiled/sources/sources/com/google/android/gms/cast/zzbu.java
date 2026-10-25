package com.google.android.gms.cast;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzbu {
    private final Map zza;
    private final Map zzb;
    private final Map zzc;

    public zzbu() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
    }

    public final zzbu zza(String r2, String r3, int r4) {
        this.zza.put(r2, r3);
        this.zzb.put(r3, r2);
        Integer r32 = Integer.valueOf(r4);
        this.zzc.put(r2, r32);
        return this;
    }

    public final String zzb(String r1) {
        return (String) this.zza.get(r1);
    }

    public final String zzc(String r1) {
        return (String) this.zzb.get(r1);
    }

    public final int zzd(String r1) {
        Integer r0 = (Integer) this.zzc.get(r1);
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.intValue();
    }
}
