package com.google.android.gms.internal.cloudmessaging;

import android.content.Context;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.ComplianceOptions;

/* loaded from: classes4.dex */
public abstract class zzq {
    public static ApiMetadata zza(Context r0) {
        zzn.zza();
        return ApiMetadata.fromComplianceOptions(ComplianceOptions.newBuilder(r0).build());
    }
}
