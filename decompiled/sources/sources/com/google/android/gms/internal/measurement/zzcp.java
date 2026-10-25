package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* loaded from: classes.dex */
public interface zzcp extends IInterface {
    void beginAdUnitExposure(String r1, long r2);

    void clearConditionalUserProperty(String r1, String r2, Bundle r3);

    void clearMeasurementEnabled(long r1);

    void endAdUnitExposure(String r1, long r2);

    void generateEventId(zzcs r1);

    void getAppInstanceId(zzcs r1);

    void getCachedAppInstanceId(zzcs r1);

    void getConditionalUserProperties(String r1, String r2, zzcs r3);

    void getCurrentScreenClass(zzcs r1);

    void getCurrentScreenName(zzcs r1);

    void getGmpAppId(zzcs r1);

    void getMaxUserProperties(String r1, zzcs r2);

    void getSessionId(zzcs r1);

    void getTestFlag(zzcs r1, int r2);

    void getUserProperties(String r1, String r2, boolean r3, zzcs r4);

    void initForTests(Map r1);

    void initialize(IObjectWrapper r1, zzdb r2, long r3);

    void initializeWithElapsedTime(IObjectWrapper r1, zzdb r2, long r3, long r5);

    void isDataCollectionEnabled(zzcs r1);

    void logEvent(String r1, String r2, Bundle r3, boolean r4, boolean r5, long r6);

    void logEventAndBundle(String r1, String r2, Bundle r3, zzcs r4, long r5);

    void logEventWithElapsedTime(String r1, String r2, Bundle r3, boolean r4, boolean r5, long r6, long r8);

    void logHealthData(int r1, String r2, IObjectWrapper r3, IObjectWrapper r4, IObjectWrapper r5);

    void onActivityCreated(IObjectWrapper r1, Bundle r2, long r3);

    void onActivityCreatedByScionActivityInfo(zzdd r1, Bundle r2, long r3);

    void onActivityDestroyed(IObjectWrapper r1, long r2);

    void onActivityDestroyedByScionActivityInfo(zzdd r1, long r2);

    void onActivityPaused(IObjectWrapper r1, long r2);

    void onActivityPausedByScionActivityInfo(zzdd r1, long r2);

    void onActivityResumed(IObjectWrapper r1, long r2);

    void onActivityResumedByScionActivityInfo(zzdd r1, long r2);

    void onActivitySaveInstanceState(IObjectWrapper r1, zzcs r2, long r3);

    void onActivitySaveInstanceStateByScionActivityInfo(zzdd r1, zzcs r2, long r3);

    void onActivityStarted(IObjectWrapper r1, long r2);

    void onActivityStartedByScionActivityInfo(zzdd r1, long r2);

    void onActivityStopped(IObjectWrapper r1, long r2);

    void onActivityStoppedByScionActivityInfo(zzdd r1, long r2);

    void performAction(Bundle r1, zzcs r2, long r3);

    void registerOnMeasurementEventListener(zzcy r1);

    void resetAnalyticsData(long r1);

    void resetAnalyticsDataWithElapsedTime(long r1, long r3);

    void retrieveAndUploadBatches(zzcv r1);

    void setConditionalUserProperty(Bundle r1, long r2);

    void setConsent(Bundle r1, long r2);

    void setConsentThirdParty(Bundle r1, long r2);

    void setCurrentScreen(IObjectWrapper r1, String r2, String r3, long r4);

    void setCurrentScreenByScionActivityInfo(zzdd r1, String r2, String r3, long r4);

    void setDataCollectionEnabled(boolean r1);

    void setDefaultEventParameters(Bundle r1);

    void setEventInterceptor(zzcy r1);

    void setInstanceIdProvider(zzda r1);

    void setMeasurementEnabled(boolean r1, long r2);

    void setMinimumSessionDuration(long r1);

    void setSessionTimeoutDuration(long r1);

    void setSgtmDebugInfo(Intent r1);

    void setUserId(String r1, long r2);

    void setUserProperty(String r1, String r2, IObjectWrapper r3, boolean r4, long r5);

    void unregisterOnMeasurementEventListener(zzcy r1);
}
