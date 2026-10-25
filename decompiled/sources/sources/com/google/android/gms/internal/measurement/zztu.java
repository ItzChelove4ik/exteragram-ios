package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final class zztu {
    private Executor zza;
    private zzru zzb;
    private final HashMap zzc;
    private final zzvc zzd;

    public zztu() {
        this.zzd = zzvc.zza;
        this.zzc = new HashMap();
    }

    public final zztu zza(Executor r1) {
        this.zza = r1;
        return this;
    }

    public final zztu zzb(zzru r1) {
        this.zzb = r1;
        return this;
    }

    public final zztu zzc(zzuw r5) {
        Preconditions.checkNotNull(zzti.zza);
        HashMap r0 = this.zzc;
        Preconditions.checkArgument(!r0.containsKey("singleproc"), "There is already a factory registered for the ID %s", "singleproc");
        r0.put("singleproc", r5);
        return this;
    }

    public final zztt zzd() {
        zzvc r3 = this.zzd;
        HashMap r4 = this.zzc;
        return new zztt(this.zza, this.zzb, r3, r4, null);
    }
}
