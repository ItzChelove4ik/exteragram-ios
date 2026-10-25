package com.google.android.gms.wearable;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Wearable;

/* loaded from: classes5.dex */
public abstract class MessageClient extends GoogleApi<Wearable.WearableOptions> {
    public MessageClient(Context r3, GoogleApi.Settings r4) {
        super(r3, Wearable.API, Wearable.WearableOptions.zza, r4);
    }

    public abstract Task<Integer> sendMessage(String r1, String r2, byte[] r3);
}
