package com.google.android.gms.fido.fido2;

import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.internal.fido.zzo;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public class Fido2ApiClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api.ClientKey zza = null;
    private static final Api zzb = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zza = r0;
        zzb = new Api("Fido.FIDO2_API", new zzo(), r0);
    }

    @Deprecated
    public Fido2ApiClient(Context r4) {
        super(r4, zzb, Api.ApiOptions.NO_OPTIONS, new ApiExceptionMapper());
    }

    public Task<PendingIntent> getRegisterPendingIntent(PublicKeyCredentialCreationOptions r3) {
        return doRead(TaskApiCall.builder().run(new zza(this, r3)).setMethodKey(5407).build());
    }
}
