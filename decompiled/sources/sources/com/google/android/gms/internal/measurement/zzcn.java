package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public final class zzcn extends zzbl implements zzcp {
    public zzcn(IBinder r2) {
        super(r2, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void beginAdUnitExposure(String r2, long r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeLong(r3);
        zzc(23, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void clearConditionalUserProperty(String r2, String r3, Bundle r4) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        zzbn.zzc(r0, r4);
        zzc(9, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void endAdUnitExposure(String r2, long r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeLong(r3);
        zzc(24, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void generateEventId(zzcs r2) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzc(22, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void getCachedAppInstanceId(zzcs r2) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzc(19, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void getConditionalUserProperties(String r2, String r3, zzcs r4) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        zzbn.zzd(r0, r4);
        zzc(10, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void getCurrentScreenClass(zzcs r2) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzc(17, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void getCurrentScreenName(zzcs r2) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzc(16, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void getGmpAppId(zzcs r2) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzc(21, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void getMaxUserProperties(String r2, zzcs r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        zzbn.zzd(r0, r3);
        zzc(6, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void getUserProperties(String r2, String r3, boolean r4, zzcs r5) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        int r22 = zzbn.$r8$clinit;
        r0.writeInt(r4 ? 1 : 0);
        zzbn.zzd(r0, r5);
        zzc(5, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void initialize(IObjectWrapper r2, zzdb r3, long r4) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzbn.zzc(r0, r3);
        r0.writeLong(r4);
        zzc(1, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void initializeWithElapsedTime(IObjectWrapper r2, zzdb r3, long r4, long r6) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzbn.zzc(r0, r3);
        r0.writeLong(r4);
        r0.writeLong(r6);
        zzc(60, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void logEventWithElapsedTime(String r2, String r3, Bundle r4, boolean r5, boolean r6, long r7, long r9) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        zzbn.zzc(r0, r4);
        r0.writeInt(r5 ? 1 : 0);
        r0.writeInt(r6 ? 1 : 0);
        r0.writeLong(r7);
        r0.writeLong(r9);
        zzc(59, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void logHealthData(int r2, String r3, IObjectWrapper r4, IObjectWrapper r5, IObjectWrapper r6) {
        Parcel r22 = zza();
        r22.writeInt(5);
        r22.writeString(r3);
        zzbn.zzd(r22, r4);
        zzbn.zzd(r22, r5);
        zzbn.zzd(r22, r6);
        zzc(33, r22);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void onActivityCreatedByScionActivityInfo(zzdd r2, Bundle r3, long r4) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        zzbn.zzc(r0, r3);
        r0.writeLong(r4);
        zzc(53, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void onActivityDestroyedByScionActivityInfo(zzdd r2, long r3) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        r0.writeLong(r3);
        zzc(54, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void onActivityPausedByScionActivityInfo(zzdd r2, long r3) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        r0.writeLong(r3);
        zzc(55, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void onActivityResumedByScionActivityInfo(zzdd r2, long r3) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        r0.writeLong(r3);
        zzc(56, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void onActivitySaveInstanceStateByScionActivityInfo(zzdd r2, zzcs r3, long r4) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        zzbn.zzd(r0, r3);
        r0.writeLong(r4);
        zzc(57, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void onActivityStartedByScionActivityInfo(zzdd r2, long r3) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        r0.writeLong(r3);
        zzc(51, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void onActivityStoppedByScionActivityInfo(zzdd r2, long r3) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        r0.writeLong(r3);
        zzc(52, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void registerOnMeasurementEventListener(zzcy r2) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzc(35, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void resetAnalyticsData(long r2) {
        Parcel r0 = zza();
        r0.writeLong(r2);
        zzc(12, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void resetAnalyticsDataWithElapsedTime(long r2, long r4) {
        Parcel r0 = zza();
        r0.writeLong(r2);
        r0.writeLong(r4);
        zzc(61, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void retrieveAndUploadBatches(zzcv r2) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzc(58, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void setConditionalUserProperty(Bundle r2, long r3) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        r0.writeLong(r3);
        zzc(8, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void setCurrentScreenByScionActivityInfo(zzdd r2, String r3, String r4, long r5) {
        Parcel r0 = zza();
        zzbn.zzc(r0, r2);
        r0.writeString(r3);
        r0.writeString(r4);
        r0.writeLong(r5);
        zzc(50, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void setDataCollectionEnabled(boolean r3) {
        Parcel r0 = zza();
        int r1 = zzbn.$r8$clinit;
        r0.writeInt(r3 ? 1 : 0);
        zzc(39, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void setMeasurementEnabled(boolean r3, long r4) {
        Parcel r0 = zza();
        int r1 = zzbn.$r8$clinit;
        r0.writeInt(r3 ? 1 : 0);
        r0.writeLong(r4);
        zzc(11, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public final void setUserProperty(String r2, String r3, IObjectWrapper r4, boolean r5, long r6) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        zzbn.zzd(r0, r4);
        r0.writeInt(r5 ? 1 : 0);
        r0.writeLong(r6);
        zzc(4, r0);
    }
}
