package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public final class zze {
    final Set zza;
    private final AnalyticsConnector.AnalyticsConnectorListener zzb;
    private final AppMeasurementSdk zzc;
    private final zzd zzd;

    public zze(AppMeasurementSdk r1, AnalyticsConnector.AnalyticsConnectorListener r2) {
        this.zzb = r2;
        this.zzc = r1;
        zzd r22 = new zzd(this);
        this.zzd = r22;
        r1.registerOnMeasurementEventListener(r22);
        this.zza = new HashSet();
    }

    public final /* synthetic */ AnalyticsConnector.AnalyticsConnectorListener zzd() {
        return this.zzb;
    }
}
