package com.google.firebase.crashlytics;

import android.content.Context;
import android.content.pm.PackageManager;
import c.f$$ExternalSyntheticBUOutline2;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponentDeferredProxy;
import com.google.firebase.crashlytics.internal.DevelopmentPlatformProvider;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.RemoteConfigDeferredProxy;
import com.google.firebase.crashlytics.internal.common.AppData;
import com.google.firebase.crashlytics.internal.common.BuildIdInfo;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsAppQualitySessionsSubscriber;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.settings.SettingsController;
import com.google.firebase.inject.Deferred;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.interop.FirebaseRemoteConfigInterop;
import com.google.firebase.sessions.api.FirebaseSessionsDependencies;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class FirebaseCrashlytics {
    final CrashlyticsCore core;

    public static /* synthetic */ void $r8$lambda$QD_dTaW8Z49RrTUw_cFPwY_G4sA(Exception r2) {
        Logger.getLogger().e("Error fetching settings.", r2);
    }

    private FirebaseCrashlytics(CrashlyticsCore r1) {
        this.core = r1;
    }

    public static FirebaseCrashlytics getInstance() {
        FirebaseCrashlytics r0 = (FirebaseCrashlytics) FirebaseApp.getInstance().get(FirebaseCrashlytics.class);
        if (r0 == null) goto L5;
        return r0;
    L5:
        f$$ExternalSyntheticBUOutline2.m("FirebaseCrashlytics component is not present.");
        return null;
    }

    public static FirebaseCrashlytics init(FirebaseApp r15, FirebaseInstallationsApi r16, Deferred<CrashlyticsNativeComponent> r17, Deferred<AnalyticsConnector> r18, Deferred<FirebaseRemoteConfigInterop> r19, ExecutorService r20, ExecutorService r21, ExecutorService r22) {
        Context r0 = r15.getApplicationContext();
        String r1 = r0.getPackageName();
        Logger.getLogger().i("Initializing Firebase Crashlytics " + CrashlyticsCore.getVersion() + " for " + r1);
        CrashlyticsWorkers r14 = new CrashlyticsWorkers(r20, r21);
        FileStore r11 = new FileStore(r0);
        DataCollectionArbiter r8 = new DataCollectionArbiter(r15);
        IdManager r2 = new IdManager(r0, r1, r16, r8);
        CrashlyticsNativeComponentDeferredProxy r7 = new CrashlyticsNativeComponentDeferredProxy(r17);
        AnalyticsDeferredProxy r12 = new AnalyticsDeferredProxy(r18);
        CrashlyticsAppQualitySessionsSubscriber r122 = new CrashlyticsAppQualitySessionsSubscriber(r8, r11);
        FirebaseSessionsDependencies.register(r122);
        CrashlyticsCore r4 = new CrashlyticsCore(r15, r2, r7, r8, r12.getDeferredBreadcrumbSource(), r12.getAnalyticsEventLogger(), r11, r122, new RemoteConfigDeferredProxy(r19), r14);
        String r13 = r15.getOptions().getApplicationId();
        String r152 = CommonUtils.getMappingFileId(r0);
        List<BuildIdInfo> r3 = CommonUtils.getBuildIdInfo(r0);
        Logger.getLogger().d("Mapping file ID is: " + r152);
        Iterator<BuildIdInfo> r42 = r3.iterator();
    L4:
        if (r42.hasNext() == false) goto L16;
        BuildIdInfo r5 = r42.next();
        Logger.getLogger().d(String.format("Build id for %s on %s: %s", new Object[]{r5.getLibraryName(), r5.getArch(), r5.getBuildId()}));
        goto L4
    L16:
        AppData r10 = AppData.create(r0, r2, r13, r152, r3, new DevelopmentPlatformProvider(r0));     // Catch: PackageManager.NameNotFoundException -> L13
        Logger.getLogger().v("Installer package name is: " + r10.installerPackageName);
        SettingsController r153 = SettingsController.create(r0, r13, r2, new HttpRequestFactory(), r10.versionCode, r10.versionName, r11, r8);
        r153.loadSettingsData(r14).addOnFailureListener(r22, new FirebaseCrashlytics$$ExternalSyntheticLambda0());
        if (r4.onPreExecute(r10, r153) == false) goto L12;
        r4.doBackgroundInitializationAsync(r153);
    L12:
        return new FirebaseCrashlytics(r4);
    L13:
        e = move-exception;
        Logger.getLogger().e("Error retrieving app package info.", e);
        return null;
    }

    public void recordException(Throwable r2) {
        if (r2 != null) goto L5;
        Logger.getLogger().w("A null value was passed to recordException. Ignoring.");
        return;
    L5:
        this.core.logException(r2, Collections.EMPTY_MAP);
    }

    public void setCrashlyticsCollectionEnabled(boolean r1) {
        this.core.setCrashlyticsCollectionEnabled(Boolean.valueOf(r1));
    }

    public void setCustomKey(String r1, String r2) {
        this.core.setCustomKey(r1, r2);
    }
}
