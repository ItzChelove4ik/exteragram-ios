package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;

/* loaded from: classes4.dex */
final class zzon {
    private final Function zza;
    private final boolean zzb;
    private final ImmutableSet zzc;
    private volatile String zzd;

    public zzon(Function r1, boolean r2, boolean r3, boolean r4, boolean r5, ImmutableSet r6) {
        this.zzd = null;
        this.zza = r1;
        this.zzb = r4;
        this.zzc = r6;
    }

    public final String zza(Context r2) {
        String r0 = this.zzd;
        if (r0 != null) goto L6;
        String r22 = (String) this.zza.apply(r2);
        this.zzd = r22;
        return r22;
    L6:
        return r0;
    }

    public final boolean zzb() {
        return this.zzb;
    }

    public final ImmutableSet zzc() {
        return this.zzc;
    }
}
