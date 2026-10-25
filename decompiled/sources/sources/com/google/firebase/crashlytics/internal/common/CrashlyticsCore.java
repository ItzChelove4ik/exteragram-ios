package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.RemoteConfigDeferredProxy;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.metadata.LogFileManager;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import com.google.firebase.crashlytics.internal.stacktrace.MiddleOutFallbackStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.RemoveRepeatsStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class CrashlyticsCore {
    private final AnalyticsEventLogger analyticsEventLogger;
    private final FirebaseApp app;
    public final BreadcrumbSource breadcrumbSource;
    private final Context context;
    private CrashlyticsController controller;
    private CrashlyticsFileMarker crashMarker;
    private final CrashlyticsWorkers crashlyticsWorkers;
    private final DataCollectionArbiter dataCollectionArbiter;
    private boolean didCrashOnPreviousExecution;
    private final FileStore fileStore;
    private final IdManager idManager;
    private CrashlyticsFileMarker initializationMarker;
    private final CrashlyticsNativeComponent nativeComponent;
    private final OnDemandCounter onDemandCounter;
    private final RemoteConfigDeferredProxy remoteConfigDeferredProxy;
    private final CrashlyticsAppQualitySessionsSubscriber sessionsSubscriber;
    private final long startTime;

    /* renamed from: $r8$lambda$-Hs7RZ3eRbvOmovK-93oAJzb6ws, reason: not valid java name */
    public static /* synthetic */ Task m1937$r8$lambda$Hs7RZ3eRbvOmovK93oAJzb6ws(CrashlyticsCore r2, long r3, String r5) {
        return r2.crashlyticsWorkers.diskWrite.submit(new CrashlyticsCore$$ExternalSyntheticLambda7(r2, r3, r5));
    }

    public static /* synthetic */ Boolean $r8$lambda$FGHaPdjiIxZJNBM7z8RgIueD4_o(CrashlyticsCore r0) {
        return Boolean.valueOf(r0.controller.didCrashOnPreviousExecution());
    }

    public static /* synthetic */ void $r8$lambda$VYqBCTibDEOUCFbGI4yAyXq_BZY(CrashlyticsCore r0, long r1, String r3) {
        r0.controller.writeToLog(r1, r3);
    }

    /* renamed from: $r8$lambda$aTz2civUJgCYzzJJmnu_HE-ZeUI, reason: not valid java name */
    public static /* synthetic */ void m1938$r8$lambda$aTz2civUJgCYzzJJmnu_HEZeUI(CrashlyticsCore r0, SettingsProvider r1) {
        r0.doBackgroundInitialization(r1);
    }

    public static /* synthetic */ void $r8$lambda$cJw54l6pepzJFEwHob8HTSSCYd8(CrashlyticsCore r0, String r1, String r2) {
        r0.controller.setCustomKey(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$keH9wMoaKPTY5p135XVOk0KWtKQ(CrashlyticsCore r1, Throwable r2, Map r3) {
        r1.controller.writeNonFatalException(Thread.currentThread(), r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$knH5rP9MzxyK2lsYoMqMtYQH4kA(CrashlyticsCore r0, SettingsProvider r1) {
        r0.doBackgroundInitialization(r1);
    }

    public CrashlyticsCore(FirebaseApp r1, IdManager r2, CrashlyticsNativeComponent r3, DataCollectionArbiter r4, BreadcrumbSource r5, AnalyticsEventLogger r6, FileStore r7, CrashlyticsAppQualitySessionsSubscriber r8, RemoteConfigDeferredProxy r9, CrashlyticsWorkers r10) {
        this.app = r1;
        this.dataCollectionArbiter = r4;
        this.context = r1.getApplicationContext();
        this.idManager = r2;
        this.nativeComponent = r3;
        this.breadcrumbSource = r5;
        this.analyticsEventLogger = r6;
        this.fileStore = r7;
        this.sessionsSubscriber = r8;
        this.remoteConfigDeferredProxy = r9;
        this.crashlyticsWorkers = r10;
        this.startTime = System.currentTimeMillis();
        this.onDemandCounter = new OnDemandCounter();
    }

    private void checkForPreviousCrash() {
        Future r0 = this.crashlyticsWorkers.common.getExecutor().submit(new CrashlyticsCore$$ExternalSyntheticLambda2(this));
        Boolean r02 = (Boolean) r0.get(3, TimeUnit.SECONDS);     // Catch: Exception -> L6
        this.didCrashOnPreviousExecution = Boolean.TRUE.equals(r02);
        return;
    L6:
        this.didCrashOnPreviousExecution = false;
    }

    private void doBackgroundInitialization(SettingsProvider r4) {
        CrashlyticsWorkers.checkBackgroundThread();
        markInitializationStarted();
        this.breadcrumbSource.registerBreadcrumbHandler(new CrashlyticsCore$$ExternalSyntheticLambda4(this));     // Catch: Throwable -> L8 Exception -> L10
        this.controller.saveVersionControlInfo();     // Catch: Throwable -> L8 Exception -> L10
        if (r4.getSettingsSync().featureFlagData.collectReports == true) goto L6;
        Logger.getLogger().d("Collection of crash reports disabled in Crashlytics settings.");     // Catch: Throwable -> L8 Exception -> L10
        throw new RuntimeException("Collection of crash reports disabled in Crashlytics settings.");     // Catch: Throwable -> L8 Exception -> L10
    L6:
        if (this.controller.finalizeSessions(r4) == true) goto L12;
        Logger.getLogger().w("Previous sessions could not be finalized.");     // Catch: Throwable -> L8 Exception -> L10
    L12:
        this.controller.submitAllReports(r4.getSettingsAsync());     // Catch: Throwable -> L8 Exception -> L10
        markInitializationComplete();
        return;
    L8:
        th = move-exception;
        markInitializationComplete();
        throw th;
    L10:
        e = move-exception;
        Logger.getLogger().e("Crashlytics encountered a problem during asynchronous initialization.", e);     // Catch: Throwable -> L8
        markInitializationComplete();
    }

    private void finishInitSynchronously(SettingsProvider r3) {
        Future<?> r2 = this.crashlyticsWorkers.common.getExecutor().submit(new CrashlyticsCore$$ExternalSyntheticLambda3(this, r3));
        Logger.getLogger().d("Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        r2.get(3, TimeUnit.SECONDS);     // Catch: TimeoutException -> L5 ExecutionException -> L7 InterruptedException -> L9
        return;
    L9:
        e = move-exception;
        Logger.getLogger().e("Crashlytics was interrupted during initialization.", e);
        Thread.currentThread().interrupt();
        return;
    L7:
        e = move-exception;
        Logger.getLogger().e("Crashlytics encountered a problem during initialization.", e);
        return;
    L5:
        e = move-exception;
        Logger.getLogger().e("Crashlytics timed out during initialization.", e);
    }

    public static String getVersion() {
        return "20.1.0";
    }

    public static boolean isBuildIdValid(String r2, boolean r3) {
        if (r3 == true) goto L7;
        Logger.getLogger().v("Configured not to require a build ID.");
        return true;
    L7:
        if (TextUtils.isEmpty(r2) == true) goto L9;
        return true;
    L9:
        Log.e("FirebaseCrashlytics", ".");
        Log.e("FirebaseCrashlytics", ".     |  | ");
        Log.e("FirebaseCrashlytics", ".     |  |");
        Log.e("FirebaseCrashlytics", ".     |  |");
        Log.e("FirebaseCrashlytics", ".   \\ |  | /");
        Log.e("FirebaseCrashlytics", ".    \\    /");
        Log.e("FirebaseCrashlytics", ".     \\  /");
        Log.e("FirebaseCrashlytics", ".      \\/");
        Log.e("FirebaseCrashlytics", ".");
        Log.e("FirebaseCrashlytics", "The Crashlytics build ID is missing. This occurs when the Crashlytics Gradle plugin is missing from your app's build configuration. Please review the Firebase Crashlytics onboarding instructions at https://firebase.google.com/docs/crashlytics/get-started?platform=android#add-plugin");
        Log.e("FirebaseCrashlytics", ".");
        Log.e("FirebaseCrashlytics", ".      /\\");
        Log.e("FirebaseCrashlytics", ".     /  \\");
        Log.e("FirebaseCrashlytics", ".    /    \\");
        Log.e("FirebaseCrashlytics", ".   / |  | \\");
        Log.e("FirebaseCrashlytics", ".     |  |");
        Log.e("FirebaseCrashlytics", ".     |  |");
        Log.e("FirebaseCrashlytics", ".     |  |");
        Log.e("FirebaseCrashlytics", ".");
        return false;
    }

    public boolean didPreviousInitializationFail() {
        return this.initializationMarker.isPresent();
    }

    public Task<Void> doBackgroundInitializationAsync(SettingsProvider r3) {
        return this.crashlyticsWorkers.common.submit(new CrashlyticsCore$$ExternalSyntheticLambda0(this, r3));
    }

    public void log(String r5) {
        long r0 = System.currentTimeMillis() - this.startTime;
        this.crashlyticsWorkers.common.submitTask(new CrashlyticsCore$$ExternalSyntheticLambda6(this, r0, r5));
    }

    public void logException(Throwable r3, Map<String, String> r4) {
        this.crashlyticsWorkers.common.submit(new CrashlyticsCore$$ExternalSyntheticLambda5(this, r3, r4));
    }

    public void markInitializationComplete() {
        CrashlyticsWorkers.checkBackgroundThread();
    L7:
        e = move-exception;
        Logger.getLogger().e("Problem encountered deleting Crashlytics initialization marker.", e);
        return;
    L4:
        if (this.initializationMarker.remove() == true) goto L12;
        Logger.getLogger().w("Initialization marker file was not properly removed.");     // Catch: Exception -> L7
        return;
    }

    public void markInitializationStarted() {
        CrashlyticsWorkers.checkBackgroundThread();
        this.initializationMarker.create();
        Logger.getLogger().v("Initialization marker file was created.");
    }

    public boolean onPreExecute(AppData r30, SettingsProvider r31) {
        Context r0 = this.context;
        if (isBuildIdValid(r30.buildId, CommonUtils.getBooleanResourceValue(r0, "com.google.firebase.crashlytics.RequireBuildId", CommonUtils.getBooleanResourceValue(r0, "com.crashlytics.RequireBuildId", true))) == false) goto L19;
        String r02 = new CLSUUID().getSessionId();
        this.crashMarker = new CrashlyticsFileMarker("crash_marker", this.fileStore);     // Catch: Exception -> L8
        this.initializationMarker = new CrashlyticsFileMarker("initialization_marker", this.fileStore);     // Catch: Exception -> L8
        UserMetadata r7 = new UserMetadata(r02, this.fileStore, this.crashlyticsWorkers);     // Catch: Exception -> L8
        LogFileManager r6 = new LogFileManager(this.fileStore);     // Catch: Exception -> L8
        MiddleOutFallbackStrategy r8 = new MiddleOutFallbackStrategy(1024, new StackTraceTrimmingStrategy[]{new RemoveRepeatsStrategy(10)});     // Catch: Exception -> L8
        this.remoteConfigDeferredProxy.setupListener(r7);     // Catch: Exception -> L8
        this.controller = new CrashlyticsController(this.context, this.idManager, this.dataCollectionArbiter, this.fileStore, this.crashMarker, r30, r7, r6, SessionReportingCoordinator.create(this.context, this.idManager, this.fileStore, r30, r6, r7, r8, r31, this.onDemandCounter, this.sessionsSubscriber, this.crashlyticsWorkers), this.nativeComponent, this.analyticsEventLogger, this.sessionsSubscriber, this.crashlyticsWorkers);     // Catch: Exception -> L8
        boolean r2 = didPreviousInitializationFail();     // Catch: Exception -> L8
        checkForPreviousCrash();     // Catch: Exception -> L8
        this.controller.enableExceptionHandling(r02, Thread.getDefaultUncaughtExceptionHandler(), r31);     // Catch: Exception -> L8
        if (Build.VERSION.SDK_INT < 37) goto L10;
        this.controller.enableProfilingManagerListener(r02);     // Catch: Exception -> L8
    L10:
        if (r2 == true) goto L12;
    L15:
        Logger.getLogger().d("Successfully configured exception handler.");
        return true;
    L12:
        if (CommonUtils.canTryConnection(this.context) == false) goto L15;
        Logger.getLogger().d("Crashlytics did not finish previous background initialization. Initializing synchronously.");     // Catch: Exception -> L8
        finishInitSynchronously(r31);     // Catch: Exception -> L8
        return false;
    L8:
        e = move-exception;
        Logger.getLogger().e("Crashlytics was not started due to an exception during initialization", e);
        this.controller = null;
        return false;
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("The Crashlytics build ID is missing. This occurs when the Crashlytics Gradle plugin is missing from your app's build configuration. Please review the Firebase Crashlytics onboarding instructions at https://firebase.google.com/docs/crashlytics/get-started?platform=android#add-plugin");
        return false;
    }

    public void setCrashlyticsCollectionEnabled(Boolean r1) {
        this.dataCollectionArbiter.setCrashlyticsDataCollectionEnabled(r1);
    }

    public void setCustomKey(String r3, String r4) {
        this.crashlyticsWorkers.common.submit(new CrashlyticsCore$$ExternalSyntheticLambda1(this, r3, r4));
    }
}
