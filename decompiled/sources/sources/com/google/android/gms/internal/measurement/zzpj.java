package com.google.android.gms.internal.measurement;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;

/* loaded from: classes4.dex */
public final class zzpj {
    private final Function zza;
    private final ImmutableSet zzb;
    private boolean zzc;

    public zzpj(Function r2) {
        this.zzb = ImmutableSet.of();
        this.zzc = false;
        this.zza = r2;
    }

    public final zzpj zza() {
        this.zzc = true;
        return this;
    }

    public final zzph zzb() {
        boolean r5 = this.zzc;
        return new zzpi(new zzon(this.zza, false, false, r5, false, this.zzb), null);
    }
}
