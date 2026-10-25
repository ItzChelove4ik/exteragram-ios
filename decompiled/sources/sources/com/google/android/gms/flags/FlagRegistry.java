package com.google.android.gms.flags;

import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes4.dex */
public class FlagRegistry {
    private final Collection<Flag> zza;
    private final Collection<Object> zzb;
    private final Collection<Object> zzc;

    public FlagRegistry() {
        this.zza = new ArrayList();
        this.zzb = new ArrayList();
        this.zzc = new ArrayList();
    }

    public final void zza(Flag r1) {
        this.zza.add(r1);
    }
}
