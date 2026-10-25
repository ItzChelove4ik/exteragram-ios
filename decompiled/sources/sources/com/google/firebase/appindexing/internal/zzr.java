package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.internal.icing.zze;
import com.google.firebase.FirebaseExceptionMapper;

/* loaded from: classes5.dex */
final class zzr extends GoogleApi<Api.ApiOptions.NoOptions> {
    public zzr(Context r4) {
        super(r4, zze.zzb, Api.ApiOptions.NO_OPTIONS, new FirebaseExceptionMapper());
    }
}
