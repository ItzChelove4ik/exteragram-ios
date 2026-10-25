package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.chaquo.python.internal.Common;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

@DynamiteApi
/* loaded from: classes5.dex */
public class AppMeasurementDynamiteService extends com.google.android.gms.internal.measurement.zzco {
    zzic zza;
    private final Map zzb;

    public AppMeasurementDynamiteService() {
        this.zza = null;
        this.zzb = new ArrayMap();
    }

    @EnsuresNonNull({"scion"})
    private final void zzb() {
        if (this.zza == null) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Attempting to perform action before initialize.");
    }

    private final void zzc(com.google.android.gms.internal.measurement.zzcs r1, String r2) {
        zzb();
        this.zza.zzk().zzao(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void beginAdUnitExposure(String r1, long r2) {
        zzb();
        this.zza.zzw().zza(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void clearConditionalUserProperty(String r1, String r2, Bundle r3) {
        zzb();
        this.zza.zzj().zzaa(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void clearMeasurementEnabled(long r1) {
        zzb();
        this.zza.zzj().zzn(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void endAdUnitExposure(String r1, long r2) {
        zzb();
        this.zza.zzw().zzb(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void generateEventId(com.google.android.gms.internal.measurement.zzcs r3) {
        zzb();
        long r0 = this.zza.zzk().zzd();
        zzb();
        this.zza.zzk().zzap(r3, r0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getAppInstanceId(com.google.android.gms.internal.measurement.zzcs r3) {
        zzb();
        this.zza.zzaX().zzj(new zzi(this, r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getCachedAppInstanceId(com.google.android.gms.internal.measurement.zzcs r2) {
        zzb();
        zzc(r2, this.zza.zzj().zzP());
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getConditionalUserProperties(String r3, String r4, com.google.android.gms.internal.measurement.zzcs r5) {
        zzb();
        this.zza.zzaX().zzj(new zzm(this, r5, r3, r4));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getCurrentScreenClass(com.google.android.gms.internal.measurement.zzcs r2) {
        zzb();
        zzc(r2, this.zza.zzj().zzad());
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getCurrentScreenName(com.google.android.gms.internal.measurement.zzcs r2) {
        zzb();
        zzc(r2, this.zza.zzj().zzac());
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getGmpAppId(com.google.android.gms.internal.measurement.zzcs r2) {
        zzb();
        zzc(r2, this.zza.zzj().zzae());
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getMaxUserProperties(String r2, com.google.android.gms.internal.measurement.zzcs r3) {
        zzb();
        this.zza.zzj().zzX(r2);
        zzb();
        this.zza.zzk().zzaq(r3, 25);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getSessionId(com.google.android.gms.internal.measurement.zzcs r3) {
        zzb();
        zzlj r2 = this.zza.zzj();
        r2.zzu.zzaX().zzj(new zzkm(r2, r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getTestFlag(com.google.android.gms.internal.measurement.zzcs r4, int r5) {
        zzb();
        if (r5 != 0) goto L5;
        this.zza.zzk().zzao(r4, this.zza.zzj().zzj());
        return;
    L5:
        if (r5 != 1) goto L7;
        this.zza.zzk().zzap(r4, this.zza.zzj().zzk().longValue());
        return;
    L7:
        if (r5 != 2) goto L9;
        zzpp r52 = this.zza.zzk();
        double r0 = this.zza.zzj().zzm().doubleValue();
        Bundle r3 = new Bundle();
        r3.putDouble("r", r0);
        r4.zzb(r3);     // Catch: RemoteException -> L20
        return;
    L20:
        e = move-exception;
        r52.zzu.zzaW().zze().zzb("Error returning double value to wrapper", e);
        return;
    L9:
        if (r5 != 3) goto L11;
        this.zza.zzk().zzaq(r4, this.zza.zzj().zzl().intValue());
        return;
    L11:
        if (r5 == 4) goto L13;
        return;
    L13:
        this.zza.zzk().zzas(r4, this.zza.zzj().zzi().booleanValue());
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void getUserProperties(String r8, String r9, boolean r10, com.google.android.gms.internal.measurement.zzcs r11) {
        zzb();
        this.zza.zzaX().zzj(new zzk(this, r11, r8, r9, r10));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void initForTests(Map r1) {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void initialize(IObjectWrapper r2, com.google.android.gms.internal.measurement.zzdb r3, long r4) {
        zzic r0 = this.zza;
        if (r0 != null) goto L6;
        this.zza = zzic.zzy((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(r2)), r3, Long.valueOf(r4), null);
        return;
    L6:
        r0.zzaW().zze().zza("Attempting to initialize multiple times");
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void initializeWithElapsedTime(IObjectWrapper r2, com.google.android.gms.internal.measurement.zzdb r3, long r4, long r6) {
        zzic r0 = this.zza;
        if (r0 != null) goto L6;
        this.zza = zzic.zzy((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(r2)), r3, Long.valueOf(r4), Long.valueOf(r6));
        return;
    L6:
        r0.zzaW().zze().zza("Attempting to initialize multiple times");
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void isDataCollectionEnabled(com.google.android.gms.internal.measurement.zzcs r3) {
        zzb();
        this.zza.zzaX().zzj(new zzn(this, r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void logEvent(String r11, String r12, Bundle r13, boolean r14, boolean r15, long r16) {
        zzb();
        this.zza.zzj().zzB(r11, r12, r13, r14, r15, r16, 0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void logEventAndBundle(String r10, String r11, Bundle r12, com.google.android.gms.internal.measurement.zzcs r13, long r14) {
        zzb();
        Preconditions.checkNotEmpty(r11);
        if (true == this.zza.zzc().zzp(null, zzfy.zzbf)) goto L6;
        String r0 = Common.ASSET_APP;
    L5:
        String r4 = r0;
        if (r12 == null) goto L9;
        Bundle r02 = new Bundle(r12);
    L10:
        r02.putString("_o", r4);
        zzbh r1 = new zzbh(r11, new zzbf(r12), r4, r14, 0);
        this.zza.zzaX().zzj(new zzj(this, r13, r1, r10));
        return;
    L9:
        r02 = new Bundle();
        goto L10
    L6:
        r0 = "auto";
        goto L5
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void logEventWithElapsedTime(String r11, String r12, Bundle r13, boolean r14, boolean r15, long r16, long r18) {
        zzb();
        this.zza.zzj().zzB(r11, r12, r13, r14, r15, r16, r18);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void logHealthData(int r10, String r11, IObjectWrapper r12, IObjectWrapper r13, IObjectWrapper r14) {
        zzb();
        Object r0 = null;
        if (r12 != null) goto L5;
        Object r6 = null;
    L6:
        if (r13 != null) goto L8;
        Object r7 = null;
    L9:
        if (r14 == null) goto L12;
        r0 = ObjectWrapper.unwrap(r14);
    L12:
        this.zza.zzaW().zzm(r10, true, false, r11, r6, r7, r0);
        return;
    L8:
        r7 = ObjectWrapper.unwrap(r13);
        goto L9
    L5:
        r6 = ObjectWrapper.unwrap(r12);
        goto L6
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityCreated(IObjectWrapper r1, Bundle r2, long r3) {
        zzb();
        onActivityCreatedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(r1))), r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityCreatedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd r1, Bundle r2, long r3) {
        zzb();
        zzky r32 = this.zza.zzj().zza;
        if (r32 == null) goto L6;
        this.zza.zzj().zzh();
        r32.zza(r1, r2);
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityDestroyed(IObjectWrapper r1, long r2) {
        zzb();
        onActivityDestroyedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(r1))), r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityDestroyedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd r1, long r2) {
        zzb();
        zzky r22 = this.zza.zzj().zza;
        if (r22 == null) goto L6;
        this.zza.zzj().zzh();
        r22.zzb(r1);
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityPaused(IObjectWrapper r1, long r2) {
        zzb();
        onActivityPausedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(r1))), r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityPausedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd r1, long r2) {
        zzb();
        zzky r22 = this.zza.zzj().zza;
        if (r22 == null) goto L6;
        this.zza.zzj().zzh();
        r22.zzc(r1);
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityResumed(IObjectWrapper r1, long r2) {
        zzb();
        onActivityResumedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(r1))), r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityResumedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd r1, long r2) {
        zzb();
        zzky r22 = this.zza.zzj().zza;
        if (r22 == null) goto L6;
        this.zza.zzj().zzh();
        r22.zzd(r1);
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivitySaveInstanceState(IObjectWrapper r1, com.google.android.gms.internal.measurement.zzcs r2, long r3) {
        zzb();
        onActivitySaveInstanceStateByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(r1))), r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivitySaveInstanceStateByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd r2, com.google.android.gms.internal.measurement.zzcs r3, long r4) {
        zzb();
        zzky r42 = this.zza.zzj().zza;
        Bundle r5 = new Bundle();
        if (r42 == null) goto L10;
        this.zza.zzj().zzh();
        r42.zze(r2, r5);
    L10:
        r3.zzb(r5);     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        this.zza.zzaW().zze().zzb("Error returning bundle value to wrapper", e);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityStarted(IObjectWrapper r1, long r2) {
        zzb();
        onActivityStartedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(r1))), r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityStartedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd r1, long r2) {
        zzb();
        if (this.zza.zzj().zza == null) goto L6;
        this.zza.zzj().zzh();
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityStopped(IObjectWrapper r1, long r2) {
        zzb();
        onActivityStoppedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(r1))), r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void onActivityStoppedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd r1, long r2) {
        zzb();
        if (this.zza.zzj().zza == null) goto L6;
        this.zza.zzj().zzh();
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void performAction(Bundle r1, com.google.android.gms.internal.measurement.zzcs r2, long r3) {
        zzb();
        r2.zzb(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void registerOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzcy r3) {
        zzb();
        Map r0 = this.zzb;
        monitor-enter(r0);
        zzjq r1 = (zzjq) r0.get(Integer.valueOf(r3.zzf()));     // Catch: Throwable -> L7
        if (r1 != null) goto L9;
        r1 = new zzq(this, r3);     // Catch: Throwable -> L7
        r0.put(Integer.valueOf(r3.zzf()), r1);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        this.zza.zzj().zzV(r1);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    @Deprecated
    public void resetAnalyticsData(long r3) {
        zzb();
        this.zza.zzj().zzS(r3, 0);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void resetAnalyticsDataWithElapsedTime(long r1, long r3) {
        zzb();
        this.zza.zzj().zzS(r1, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void retrieveAndUploadBatches(com.google.android.gms.internal.measurement.zzcv r3) {
        zzb();
        this.zza.zzj().zzt(new zzo(this, r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setConditionalUserProperty(Bundle r1, long r2) {
        zzb();
        zzic r0 = this.zza;
        if (r1 != null) goto L6;
        r0.zzaW().zzb().zza("Conditional user property must not be null");
        return;
    L6:
        r0.zzj().zzZ(r1, r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setConsent(Bundle r1, long r2) {
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setConsentThirdParty(Bundle r2, long r3) {
        zzb();
        this.zza.zzj().zzp(r2, -20, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setCurrentScreen(IObjectWrapper r7, String r8, String r9, long r10) {
        zzb();
        setCurrentScreenByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(r7))), r8, r9, r10);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setCurrentScreenByScionActivityInfo(com.google.android.gms.internal.measurement.zzdd r1, String r2, String r3, long r4) {
        zzb();
        this.zza.zzs().zzk(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setDataCollectionEnabled(boolean r3) {
        zzb();
        zzlj r2 = this.zza.zzj();
        r2.zzb();
        r2.zzu.zzaX().zzj(new zzjy(r2, r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setDefaultEventParameters(Bundle r3) {
        zzb();
        zzlj r2 = this.zza.zzj();
        if (r3 != null) goto L5;
        Bundle r32 = new Bundle();
    L6:
        r2.zzu.zzaX().zzj(new zzlf(r2, r32));
        return;
    L5:
        r32 = new Bundle(r3);
        goto L6
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setEventInterceptor(com.google.android.gms.internal.measurement.zzcy r3) {
        zzb();
        zzp r0 = new zzp(this, r3);
        boolean r32 = this.zza.zzaX().zze();
        zzic r1 = this.zza;
        if (r32 == false) goto L6;
        r1.zzj().zzU(r0);
        return;
    L6:
        r1.zzaX().zzj(new zzl(this, r0));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setInstanceIdProvider(com.google.android.gms.internal.measurement.zzda r1) {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setMeasurementEnabled(boolean r1, long r2) {
        zzb();
        this.zza.zzj().zzn(Boolean.valueOf(r1));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setMinimumSessionDuration(long r1) {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setSessionTimeoutDuration(long r3) {
        zzb();
        zzlj r2 = this.zza.zzj();
        r2.zzu.zzaX().zzj(new zzka(r2, r3));
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setSgtmDebugInfo(Intent r3) {
        zzb();
        zzlj r2 = this.zza.zzj();
        Uri r32 = r3.getData();
        if (r32 != null) goto L6;
        r2.zzu.zzaW().zzi().zza("Activity intent has no data. Preview Mode was not enabled.");
        return;
    L6:
        String r0 = r32.getQueryParameter("sgtm_debug_enable");
        if (r0 != null) goto L9;
    L15:
        zzic r22 = r2.zzu;
        r22.zzaW().zzi().zza("[sgtm] Preview Mode was not enabled.");
        r22.zzc().zzy(null);
        return;
    L9:
        if (r0.equals("1") == false) goto L15;
        String r33 = r32.getQueryParameter("sgtm_preview_key");
        if (TextUtils.isEmpty(r33) == true) goto L17;
        zzic r23 = r2.zzu;
        r23.zzaW().zzi().zzb("[sgtm] Preview Mode was enabled. Using the sgtmPreviewKey: ", r33);
        r23.zzc().zzy(r33);
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setUserId(String r8, long r9) {
        zzb();
        zzlj r0 = this.zza.zzj();
        if (r8 != null) goto L5;
    L8:
        r0.zzu.zzaX().zzj(new zzlg(r0, r8));
        r0.zzK(null, "_id", r8, true, r9);
        return;
    L5:
        if (TextUtils.isEmpty(r8) == false) goto L8;
        r0.zzu.zzaW().zze().zza("User ID must be non-empty or null");
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void setUserProperty(String r1, String r2, IObjectWrapper r3, boolean r4, long r5) {
        zzb();
        Object r32 = ObjectWrapper.unwrap(r3);
        this.zza.zzj().zzK(r1, r2, r32, r4, r5);
    }

    @Override // com.google.android.gms.internal.measurement.zzcp
    public void unregisterOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzcy r3) {
        zzb();
        Map r0 = this.zzb;
        monitor-enter(r0);
        zzjq r1 = (zzjq) r0.remove(Integer.valueOf(r3.zzf()));     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        if (r1 != null) goto L8;
        r1 = new zzq(this, r3);
    L8:
        this.zza.zzj().zzW(r1);
        return;
    L10:
        th = move-exception;
        throw th;
    }
}
