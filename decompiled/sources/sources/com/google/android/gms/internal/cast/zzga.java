package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Api;

/* loaded from: classes4.dex */
public abstract class zzga {

    @Deprecated
    public static final Api zza = null;
    private static final Api.ClientKey zzb = null;
    private static final Api.AbstractClientBuilder zzc = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zzb = r0;
        zzfy r1 = new zzfy();
        zzc = r1;
        zza = new Api("UsageReporting.API", r1, r0);
    }
}
