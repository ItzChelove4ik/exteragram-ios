package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.wearable.MessageApi;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzfp implements PendingResultUtil.ResultConverter {
    public static final /* synthetic */ zzfp zza = null;

    static {
        zza = new zzfp();
    }

    private /* synthetic */ zzfp() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final Object convert(Result r1) {
        return Integer.valueOf(((MessageApi.SendMessageResult) r1).getRequestId());
    }
}
