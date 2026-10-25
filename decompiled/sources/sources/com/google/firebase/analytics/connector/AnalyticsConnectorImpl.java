package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzez;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.analytics.connector.internal.zzc;
import com.google.firebase.analytics.connector.internal.zze;
import com.google.firebase.analytics.connector.internal.zzg;
import com.google.firebase.events.Event;
import com.google.firebase.events.Subscriber;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class AnalyticsConnectorImpl implements AnalyticsConnector {
    private static volatile AnalyticsConnector zzc;
    final AppMeasurementSdk zza;
    final Map zzb;

    /* renamed from: com.google.firebase.analytics.connector.AnalyticsConnectorImpl$1, reason: invalid class name */
    public class AnonymousClass1 implements AnalyticsConnector.AnalyticsConnectorHandle {
        final /* synthetic */ String zza;
        final /* synthetic */ AnalyticsConnectorImpl zzb;

        public AnonymousClass1(AnalyticsConnectorImpl r1, String r2) {
            this.zza = r2;
            Objects.requireNonNull(r1);
            this.zzb = r1;
        }
    }

    public AnalyticsConnectorImpl(AppMeasurementSdk r1) {
        Preconditions.checkNotNull(r1);
        this.zza = r1;
        this.zzb = new ConcurrentHashMap();
    }

    public static AnalyticsConnector getInstance(FirebaseApp r5, Context r6, Subscriber r7) {
        Preconditions.checkNotNull(r5);
        Preconditions.checkNotNull(r6);
        Preconditions.checkNotNull(r7);
        Preconditions.checkNotNull(r6.getApplicationContext());
        if (zzc != null) goto L19;
        monitor-enter(AnalyticsConnectorImpl.class);
    L11:
        th = move-exception;
        throw th;
    L7:
        if (zzc != null) goto L14;
        Bundle r1 = new Bundle(1);     // Catch: Throwable -> L11
        if (r5.isDefaultApp() == false) goto L13;
        r7.subscribe(DataCollectionDefaultChange.class, zzb.zza, zza.zza);     // Catch: Throwable -> L11
        r1.putBoolean("dataCollectionDefaultEnabled", r5.isDataCollectionDefaultEnabled());     // Catch: Throwable -> L11
    L13:
        zzc = new AnalyticsConnectorImpl(zzez.zza(r6, r1).zzb());     // Catch: Throwable -> L11
    L14:
        monitor-exit(AnalyticsConnectorImpl.class);     // Catch: Throwable -> L11
    L19:
        return zzc;
    }

    public static /* synthetic */ void zza(Event r0) {
        throw null;
    }

    private final boolean zzc(String r2) {
        if (r2.isEmpty() == true) goto L10;
        Map r1 = this.zzb;
        if (r1.containsKey(r2) == true) goto L7;
        return false;
    L7:
        if (r1.get(r2) == null) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public Map<String, Object> getUserProperties(boolean r2) {
        return this.zza.getUserProperties(null, null, r2);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public void logEvent(String r4, String r5, Bundle r6) {
        if (r6 != null) goto L5;
        r6 = new Bundle();
    L5:
        if (zzc.zza(r4) == true) goto L8;
        return;
    L8:
        if (zzc.zzb(r5, r6) == true) goto L10;
        return;
    L10:
        if (zzc.zze(r4, r5, r6) == true) goto L12;
        return;
    L12:
        if ("clx".equals(r4) == true) goto L14;
    L16:
        this.zza.logEvent(r4, r5, r6);
        return;
    L14:
        if ("_ae".equals(r5) == false) goto L16;
        r6.putLong("_r", 1);
        goto L16
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener(String r4, AnalyticsConnector.AnalyticsConnectorListener r5) {
        Preconditions.checkNotNull(r5);
        if (zzc.zza(r4) == true) goto L6;
    L17:
        return null;
    L6:
        if (zzc(r4) == true) goto L17;
        AppMeasurementSdk r0 = this.zza;
        if ("fiam".equals(r4) == false) goto L11;
        Object r2 = new zze(r0, r5);
    L14:
        if (r2 == null) goto L17;
        this.zzb.put(r4, r2);
        return new AnonymousClass1(this, r4);
    L11:
        if ("clx".equals(r4) == false) goto L13;
        r2 = new zzg(r0, r5);
        goto L14
    L13:
        r2 = null;
        goto L14
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public void setUserProperty(String r2, String r3, Object r4) {
        if (zzc.zza(r2) == true) goto L6;
        return;
    L6:
        if (zzc.zzd(r2, r3) == false) goto L9;
        this.zza.setUserProperty(r2, r3, r4);
        return;
    }
}
