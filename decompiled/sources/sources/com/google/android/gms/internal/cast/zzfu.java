package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public final class zzfu extends GoogleApi implements zzgb {
    public zzfu(Context r3, zzfz r4) {
        super(r3, zzga.zza, r4, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.internal.cast.zzgb
    public final Task zza() {
        return doRead(TaskApiCall.builder().run(new zzft(this)).setMethodKey(4501).build());
    }
}
