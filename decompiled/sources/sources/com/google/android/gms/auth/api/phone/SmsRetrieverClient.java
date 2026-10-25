package com.google.android.gms.auth.api.phone;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public abstract class SmsRetrieverClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api.ClientKey zza = null;
    private static final Api.AbstractClientBuilder zzb = null;
    private static final Api zzc = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zza = r0;
        zza r1 = new zza();
        zzb = r1;
        zzc = new Api("SmsRetriever.API", r1, r0);
    }

    public SmsRetrieverClient(Context r4) {
        super(r4, zzc, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public abstract Task<Void> startSmsRetriever();
}
