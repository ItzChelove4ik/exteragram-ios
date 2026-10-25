package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.ComplianceOptions;

/* loaded from: classes4.dex */
public abstract class zzff {
    public static ApiMetadata zza(Context r0) {
        zzfc.zza();
        return ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(r0).build());
    }
}
