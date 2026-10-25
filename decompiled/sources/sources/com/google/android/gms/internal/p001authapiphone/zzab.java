package com.google.android.gms.internal.p001authapiphone;

import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public final class zzab extends SmsRetrieverClient {
    public zzab(Context r1) {
        super(r1);
    }

    @Override // com.google.android.gms.auth.api.phone.SmsRetrieverClient
    public final Task<Void> startSmsRetriever() {
        return doWrite(TaskApiCall.builder().run(new zzx(this)).setFeatures(new Feature[]{zzac.zzc}).setMethodKey(1567).build());
    }
}
