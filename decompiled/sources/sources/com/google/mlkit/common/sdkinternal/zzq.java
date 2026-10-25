package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.OptionalModuleApi;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzq implements OptionalModuleApi {
    public final /* synthetic */ Feature[] zza;

    public /* synthetic */ zzq(Feature[] r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    public final Feature[] getOptionalFeatures() {
        Feature[] r0 = OptionalModuleUtils.EMPTY_FEATURES;
        return this.zza;
    }
}
