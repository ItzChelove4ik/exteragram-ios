package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;

/* loaded from: classes.dex */
public final class zzg {
    private final AnalyticsConnector.AnalyticsConnectorListener zza;
    private final AppMeasurementSdk zzb;
    private final zzf zzc;

    public zzg(AppMeasurementSdk r1, AnalyticsConnector.AnalyticsConnectorListener r2) {
        this.zza = r2;
        this.zzb = r1;
        zzf r22 = new zzf(this);
        this.zzc = r22;
        r1.registerOnMeasurementEventListener(r22);
    }

    public final /* synthetic */ AnalyticsConnector.AnalyticsConnectorListener zzd() {
        return this.zza;
    }
}
