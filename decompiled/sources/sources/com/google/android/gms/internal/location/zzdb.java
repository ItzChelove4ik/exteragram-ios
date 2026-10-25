package com.google.android.gms.internal.location;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public final class zzdb extends GoogleApi implements SettingsClient {
    public zzdb(Context r4) {
        super(r4, zzcg.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.location.SettingsClient
    public final Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest r3) {
        return doRead(TaskApiCall.builder().run(new zzcz(r3)).setMethodKey(2426).build());
    }
}
