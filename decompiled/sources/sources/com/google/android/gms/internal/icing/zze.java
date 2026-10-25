package com.google.android.gms.internal.icing;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
/* loaded from: classes4.dex */
public abstract class zze {
    public static final Api.ClientKey<zzae> zza = null;
    public static final Api<Api.ApiOptions.NoOptions> zzb = null;

    @Deprecated
    public static final zzz zzc = null;
    private static final Api.AbstractClientBuilder<zzae, Api.ApiOptions.NoOptions> zzd = null;

    static {
        Api.ClientKey<zzae> r0 = new Api.ClientKey();
        zza = r0;
        zzd r1 = new zzd();
        zzd = r1;
        zzb = new Api("AppDataSearch.LIGHTWEIGHT_API", r1, r0);
        zzc = new zzal();
    }
}
