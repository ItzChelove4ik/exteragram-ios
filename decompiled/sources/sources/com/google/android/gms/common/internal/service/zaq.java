package com.google.android.gms.common.internal.service;

import android.content.Context;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.zad;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public final class zaq extends GoogleApi implements zad {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Api.ClientKey zac = null;
    private static final Api.AbstractClientBuilder zad = null;
    private static final Api zae = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zac = r0;
        zao r1 = new zao();
        zad = r1;
        zae = new Api("ClientNotification.API", r1, r0);
    }

    public zaq(Context r4) {
        super(r4, zae, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.common.internal.zad
    public final Task zaa(com.google.android.gms.common.internal.zab r3) {
        TaskApiCall.Builder r0 = TaskApiCall.builder();
        r0.setFeatures(new Feature[]{com.google.android.gms.internal.base.zad.zab});
        r0.setAutoResolveMissingFeatures(false);
        r0.run(new zap(r3));
        return doBestEffortWrite(r0.build());
    }
}
