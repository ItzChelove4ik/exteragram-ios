package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi;

/* loaded from: classes5.dex */
public final class zzfk implements MessageApi.SendMessageResult {
    private final Status zza;
    private final int zzb;

    public zzfk(Status r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.wearable.MessageApi.SendMessageResult
    public final int getRequestId() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
