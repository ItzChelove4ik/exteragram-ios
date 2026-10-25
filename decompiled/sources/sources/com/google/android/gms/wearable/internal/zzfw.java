package com.google.android.gms.wearable.internal;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.MessageClient;

/* loaded from: classes5.dex */
public final class zzfw extends MessageClient {

    @VisibleForTesting
    final zzfl zza;

    public zzfw(Context r1, GoogleApi.Settings r2) {
        super(r1, r2);
        this.zza = new zzfl();
    }

    @Override // com.google.android.gms.wearable.MessageClient
    public final Task<Integer> sendMessage(String r7, String r8, byte[] r9) {
        zzfl r1 = this.zza;
        GoogleApiClient r2 = asGoogleApiClient();
        return PendingResultUtil.toTask(r2.enqueue(new zzfg(r1, r2, r7, r8, r9)), zzfp.zza);
    }
}
