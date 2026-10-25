package com.google.android.gms.internal.cloudmessaging;

import android.content.Context;
import com.google.android.gms.cloudmessaging.CloudMessagingClient;
import com.google.android.gms.cloudmessaging.RegisterRequest;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public final class zzm extends GoogleApi implements CloudMessagingClient {
    private static final Api.ClientKey zza = null;
    private static final Api.AbstractClientBuilder zzb = null;
    private static final Api zzc = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zza = r0;
        zzh r1 = new zzh();
        zzb = r1;
        zzc = new Api("CloudMessaging.API", r1, r0);
    }

    public zzm(Context r4) {
        super(r4, zzc, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.cloudmessaging.CloudMessagingClient
    public final Task<String> register(RegisterRequest r3) {
        return doRead(TaskApiCall.builder().setFeatures(new Feature[]{com.google.android.gms.cloudmessaging.zzi.zza}).run(new zzl(this, r3)).setMethodKey(39001).build());
    }
}
