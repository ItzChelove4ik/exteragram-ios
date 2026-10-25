package com.google.android.gms.common.internal.service;

import android.content.Context;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.internal.base.zad;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public final class zat extends GoogleApi implements TelemetryLoggingClient {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Api.ClientKey zac = null;
    private static final Api.AbstractClientBuilder zad = null;
    private static final Api zae = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zac = r0;
        zar r1 = new zar();
        zad = r1;
        zae = new Api("ClientTelemetry.API", r1, r0);
    }

    public zat(Context r3, TelemetryLoggingOptions r4) {
        super(r3, zae, r4, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.common.internal.TelemetryLoggingClient
    public final Task<Void> log(TelemetryData r3) {
        TaskApiCall.Builder r0 = TaskApiCall.builder();
        r0.setFeatures(new Feature[]{zad.zaa});
        r0.setAutoResolveMissingFeatures(false);
        r0.run(new zas(r3));
        return doBestEffortWrite(r0.build());
    }
}
