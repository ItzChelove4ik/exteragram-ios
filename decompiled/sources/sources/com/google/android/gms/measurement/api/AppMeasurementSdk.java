package com.google.android.gms.measurement.api;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import com.google.android.gms.internal.measurement.zzez;
import com.google.android.gms.measurement.internal.zzjq;
import java.util.Map;

/* loaded from: classes.dex */
public class AppMeasurementSdk {
    private final zzez zza;

    public interface OnEventListener extends zzjq {
    }

    public AppMeasurementSdk(zzez r1) {
        this.zza = r1;
    }

    @Keep
    public static AppMeasurementSdk getInstance(Context r1) {
        return zzez.zza(r1, null).zzb();
    }

    @Keep
    public void beginAdUnitExposure(String r1) {
        this.zza.zzu(r1);
    }

    @Keep
    public void endAdUnitExposure(String r1) {
        this.zza.zzv(r1);
    }

    @Keep
    public long generateEventId() {
        return this.zza.zzz();
    }

    @Keep
    public String getAppInstanceId() {
        return this.zza.zzy();
    }

    @Keep
    public String getGmpAppId() {
        return this.zza.zzx();
    }

    public Map<String, Object> getUserProperties(String r1, String r2, boolean r3) {
        return this.zza.zzC(r1, r2, r3);
    }

    @Keep
    public void logEvent(String r1, String r2, Bundle r3) {
        this.zza.zzi(r1, r2, r3);
    }

    public void registerOnMeasurementEventListener(OnEventListener r1) {
        this.zza.zzf(r1);
    }

    public void setUserProperty(String r2, String r3, Object r4) {
        this.zza.zzk(r2, r3, r4, true);
    }
}
