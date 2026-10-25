package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzab {
    private static final Logger zza = null;
    private final zzap zzb;

    static {
        zza = new Logger("DiscoveryManager");
    }

    public zzab(zzap r1) {
        this.zzb = r1;
    }

    public final IObjectWrapper zza() {
        return this.zzb.zze();
    L4:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"getWrappedThis", zzap.class.getSimpleName()});
        return null;
    }
}
