package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzf implements AppMeasurementSdk.OnEventListener {
    final /* synthetic */ zzg zza;

    public zzf(zzg r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzjq
    public final void onEvent(String r2, String r3, Bundle r4, long r5) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (zzc.zzc(r3) == false) goto L8;
        Bundle r22 = new Bundle();
        r22.putString("name", r3);
        r22.putLong("timestampInMillis", r5);
        r22.putBundle(PluginsConstants.PARAMS, r4);
        this.zza.zzd().onMessageTriggered(3, r22);
        return;
    }
}
