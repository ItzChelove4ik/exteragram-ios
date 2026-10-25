package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.api.Api;

/* loaded from: classes4.dex */
public abstract class zzjx {

    @Deprecated
    public static final Api zza = null;
    private static final Api.ClientKey zzb = null;
    private static final Api.AbstractClientBuilder zzc = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zzb = r0;
        zzjw r1 = new zzjw();
        zzc = r1;
        zza = new Api("Phenotype.API", r1, r0);
    }

    public static zzkk zza(Context r1) {
        return new zzkk(r1);
    }
}
