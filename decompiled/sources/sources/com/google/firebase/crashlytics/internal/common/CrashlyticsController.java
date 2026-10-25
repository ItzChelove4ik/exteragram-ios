package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ProfilingManager;
import android.os.ProfilingResult;
import android.os.StatFs;
import android.util.Base64;
import androidx.camera.core.streamsharing.StreamSharing$$ExternalSyntheticBackport0;
import com.chaquo.python.internal.Common;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.NativeSessionFileProvider;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsTasks;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorker;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.metadata.EventMetadata;
import com.google.firebase.crashlytics.internal.metadata.LogFileManager;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.settings.Settings;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import com.google.firebase.sessions.api.CrashEventReceiver;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
class CrashlyticsController {
    static final FilenameFilter APP_EXCEPTION_MARKER_FILTER = null;
    private static final Charset UTF_8 = null;
    private final AnalyticsEventLogger analyticsEventLogger;
    private final AppData appData;
    final AtomicBoolean checkForUnsentReportsCalled;
    private final Context context;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    private final CrashlyticsFileMarker crashMarker;
    private final CrashlyticsWorkers crashlyticsWorkers;
    private final DataCollectionArbiter dataCollectionArbiter;
    private final FileStore fileStore;
    private final IdManager idManager;
    private final LogFileManager logFileManager;
    private final CrashlyticsNativeComponent nativeComponent;
    final TaskCompletionSource<Boolean> reportActionProvided;
    private final SessionReportingCoordinator reportingCoordinator;
    private final CrashlyticsAppQualitySessionsSubscriber sessionsSubscriber;
    private SettingsProvider settingsProvider;
    final TaskCompletionSource<Boolean> unsentReportsAvailable;
    final TaskCompletionSource<Void> unsentReportsHandled;
    private final UserMetadata userMetadata;

    /* renamed from: com.google.firebase.crashlytics.internal.common.CrashlyticsController$1, reason: invalid class name */
    public class AnonymousClass1 implements CrashlyticsUncaughtExceptionHandler.CrashListener {
        final /* synthetic */ CrashlyticsController this$0;

        public AnonymousClass1(CrashlyticsController r1) {
            this.this$0 = r1;
        }

        @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler.CrashListener
        public void onUncaughtException(SettingsProvider r1, Thread r2, Throwable r3) {
            this.this$0.handleUncaughtException(r1, r2, r3);
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.common.CrashlyticsController$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 implements Callable<Task<Void>> {
        final /* synthetic */ CrashlyticsController this$0;
        final /* synthetic */ Throwable val$ex;
        final /* synthetic */ boolean val$isOnDemand;
        final /* synthetic */ SettingsProvider val$settingsProvider;
        final /* synthetic */ Thread val$thread;
        final /* synthetic */ long val$timestampMillis;

        /* renamed from: com.google.firebase.crashlytics.internal.common.CrashlyticsController$2$1, reason: invalid class name */
        public class AnonymousClass1 implements SuccessContinuation<Settings, Void> {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ String val$currentSessionId;

            public AnonymousClass1(AnonymousClass2 r1, String r2) {
                this.this$1 = r1;
                this.val$currentSessionId = r2;
            }

            /* renamed from: then, reason: avoid collision after fix types in other method */
            public Task<Void> then2(Settings r5) {
                String r0 = null;
                if (r5 != null) goto L6;
                Logger.getLogger().w("Received null app settings, cannot send reports at crash time.");
                return Tasks.forResult(null);
            L6:
                Task r52 = CrashlyticsController.access$800(this.this$1.this$0);
                SessionReportingCoordinator r1 = CrashlyticsController.access$300(this.this$1.this$0);
                CrashlyticsWorker r2 = CrashlyticsController.access$700(this.this$1.this$0).common;
                if (this.this$1.val$isOnDemand == false) goto L10;
                r0 = this.val$currentSessionId;
            L10:
                return Tasks.whenAll(new Task[]{r52, r1.sendReports(r2, r0)});
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public /* bridge */ /* synthetic */ Task<Void> then(Settings r1) {
                return then2(r1);
            }
        }

        public AnonymousClass2(CrashlyticsController r1, long r2, Throwable r4, Thread r5, SettingsProvider r6, boolean r7) {
            this.this$0 = r1;
            this.val$timestampMillis = r2;
            this.val$ex = r4;
            this.val$thread = r5;
            this.val$settingsProvider = r6;
            this.val$isOnDemand = r7;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Task<Void> call() {
            long r6 = CrashlyticsController.access$000(this.val$timestampMillis);
            String r5 = CrashlyticsController.access$100(this.this$0);
            if (r5 != null) goto L6;
            Logger.getLogger().e("Tried to write a fatal exception while no session was open.");
            return Tasks.forResult(null);
        L6:
            CrashlyticsController.access$200(this.this$0).create();
            CrashlyticsController.access$300(this.this$0).persistFatalEvent(this.val$ex, this.val$thread, r5, r6);
            CrashlyticsController.access$400(this.this$0, this.val$timestampMillis);
            this.this$0.doCloseSessions(this.val$settingsProvider);
            CrashlyticsController.access$500(this.this$0, new CLSUUID().getSessionId(), Boolean.valueOf(this.val$isOnDemand));
            if (CrashlyticsController.access$600(this.this$0).isAutomaticDataCollectionEnabled() == true) goto L11;
            return Tasks.forResult(null);
        L11:
            return this.val$settingsProvider.getSettingsAsync().onSuccessTask(CrashlyticsController.access$700(this.this$0).common, new AnonymousClass1(this, r5));
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Task<Void> call() {
            return call();
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.common.CrashlyticsController$3, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass3 implements SuccessContinuation<Void, Boolean> {
        final /* synthetic */ CrashlyticsController this$0;

        public AnonymousClass3(CrashlyticsController r1) {
            this.this$0 = r1;
        }

        @Override // com.google.android.gms.tasks.SuccessContinuation
        public /* bridge */ /* synthetic */ Task<Boolean> then(Void r1) {
            return then2(r1);
        }

        /* renamed from: then, reason: avoid collision after fix types in other method */
        public Task<Boolean> then2(Void r1) {
            return Tasks.forResult(Boolean.TRUE);
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.common.CrashlyticsController$4, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass4 implements SuccessContinuation<Boolean, Void> {
        final /* synthetic */ CrashlyticsController this$0;
        final /* synthetic */ Task val$settingsDataTask;

        /* renamed from: com.google.firebase.crashlytics.internal.common.CrashlyticsController$4$1, reason: invalid class name */
        public class AnonymousClass1 implements SuccessContinuation<Settings, Void> {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1) {
                this.this$1 = r1;
            }

            /* renamed from: then, reason: avoid collision after fix types in other method */
            public Task<Void> then2(Settings r3) {
                if (r3 != null) goto L6;
                Logger.getLogger().w("Received null app settings at app startup. Cannot send cached reports");
                return Tasks.forResult(null);
            L6:
                CrashlyticsController.access$800(this.this$1.this$0);
                CrashlyticsController.access$300(this.this$1.this$0).sendReports(CrashlyticsController.access$700(this.this$1.this$0).common);
                this.this$1.this$0.unsentReportsHandled.trySetResult(null);
                return Tasks.forResult(null);
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public /* bridge */ /* synthetic */ Task<Void> then(Settings r1) {
                return then2(r1);
            }
        }

        public AnonymousClass4(CrashlyticsController r1, Task r2) {
            this.this$0 = r1;
            this.val$settingsDataTask = r2;
        }

        /* renamed from: then, reason: avoid collision after fix types in other method */
        public Task<Void> then2(Boolean r3) {
            if (r3.booleanValue() == true) goto L6;
            Logger.getLogger().v("Deleting cached crash reports...");
            CrashlyticsController.access$900(this.this$0.listAppExceptionMarkerFiles());
            CrashlyticsController.access$300(this.this$0).removeAllReports();
            this.this$0.unsentReportsHandled.trySetResult(null);
            return Tasks.forResult(null);
        L6:
            Logger.getLogger().d("Sending cached crash reports...");
            boolean r32 = r3.booleanValue();
            CrashlyticsController.access$600(this.this$0).grantDataCollectionPermission(r32);
            return this.val$settingsDataTask.onSuccessTask(CrashlyticsController.access$700(this.this$0).common, new AnonymousClass1(this));
        }

        @Override // com.google.android.gms.tasks.SuccessContinuation
        public /* bridge */ /* synthetic */ Task<Void> then(Boolean r1) {
            return then2(r1);
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.common.CrashlyticsController$5, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass5 implements Callable<Void> {
        final /* synthetic */ CrashlyticsController this$0;
        final /* synthetic */ long val$timestamp;

        public AnonymousClass5(CrashlyticsController r1, long r2) {
            this.this$0 = r1;
            this.val$timestamp = r2;
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ Void call() {
            return call2();
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: call, reason: avoid collision after fix types in other method */
        public Void call2() {
            Bundle r0 = new Bundle();
            r0.putInt("fatal", 1);
            r0.putLong("timestamp", this.val$timestamp);
            CrashlyticsController.access$1000(this.this$0).logEvent("_ae", r0);
            return null;
        }
    }

    /* renamed from: $r8$lambda$058Q1W9iZVTsgR34t-Ud6A8T_3I, reason: not valid java name */
    public static /* synthetic */ boolean m1933$r8$lambda$058Q1W9iZVTsgR34tUd6A8T_3I(File r0, String r1) {
        return r1.startsWith(".ae");
    }

    public static /* synthetic */ void $r8$lambda$76pEjOiZkyRDRmGdgYrL_FjmRBA(CrashlyticsController r0, String r1, ProfilingResult r2) {
        r0.getClass();
        r0.writeTriggerTypeFile(r1, r2.getTriggerType());
    }

    /* renamed from: $r8$lambda$FsNFwb_q7pedE_qQeytXk-HvMrg, reason: not valid java name */
    public static /* synthetic */ void m1934$r8$lambda$FsNFwb_q7pedE_qQeytXkHvMrg(CrashlyticsController r1, String r2) {
        r1.doOpenSession(r2, Boolean.FALSE);
    }

    /* renamed from: $r8$lambda$Lye36H2aFDfhEQO5c-r549W0SzM, reason: not valid java name */
    public static /* synthetic */ boolean m1935$r8$lambda$Lye36H2aFDfhEQO5cr549W0SzM(Integer r0) {
        if (r0.intValue() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ boolean $r8$lambda$meuoSqUbQoTUGw_A1iOehQ7SiKk(File r1, String r2) {
        return StreamSharing$$ExternalSyntheticBackport0.m("trigger-type-anomaly", "trigger-type-oom").contains(r2);
    }

    /* renamed from: $r8$lambda$x6EyoEY6_xUqHU0usyLcw6xr-24, reason: not valid java name */
    public static /* synthetic */ Integer m1936$r8$lambda$x6EyoEY6_xUqHU0usyLcw6xr24(File r1) {
        String r12 = r1.getName();
        r12.getClass();
        if (r12.equals("trigger-type-anomaly") == true) goto L11;
        if (r12.equals("trigger-type-oom") == true) goto L9;
        return 0;
    L9:
        return 7;
    L11:
        return 8;
    }

    static {
        APP_EXCEPTION_MARKER_FILTER = new CrashlyticsController$$ExternalSyntheticLambda5();
        UTF_8 = Charset.forName("UTF-8");
    }

    public CrashlyticsController(Context r3, IdManager r4, DataCollectionArbiter r5, FileStore r6, CrashlyticsFileMarker r7, AppData r8, UserMetadata r9, LogFileManager r10, SessionReportingCoordinator r11, CrashlyticsNativeComponent r12, AnalyticsEventLogger r13, CrashlyticsAppQualitySessionsSubscriber r14, CrashlyticsWorkers r15) {
        this.settingsProvider = null;
        this.unsentReportsAvailable = new TaskCompletionSource();
        this.reportActionProvided = new TaskCompletionSource();
        this.unsentReportsHandled = new TaskCompletionSource();
        this.checkForUnsentReportsCalled = new AtomicBoolean(false);
        this.context = r3;
        this.idManager = r4;
        this.dataCollectionArbiter = r5;
        this.fileStore = r6;
        this.crashMarker = r7;
        this.appData = r8;
        this.userMetadata = r9;
        this.logFileManager = r10;
        this.nativeComponent = r12;
        this.analyticsEventLogger = r13;
        this.sessionsSubscriber = r14;
        this.reportingCoordinator = r11;
        this.crashlyticsWorkers = r15;
    }

    public static /* synthetic */ long access$000(long r0) {
        return getTimestampSeconds(r0);
    }

    public static /* synthetic */ String access$100(CrashlyticsController r0) {
        return r0.getCurrentSessionId();
    }

    public static /* synthetic */ AnalyticsEventLogger access$1000(CrashlyticsController r0) {
        return r0.analyticsEventLogger;
    }

    public static /* synthetic */ CrashlyticsFileMarker access$200(CrashlyticsController r0) {
        return r0.crashMarker;
    }

    public static /* synthetic */ SessionReportingCoordinator access$300(CrashlyticsController r0) {
        return r0.reportingCoordinator;
    }

    public static /* synthetic */ void access$400(CrashlyticsController r0, long r1) {
        r0.doWriteAppExceptionMarker(r1);
    }

    public static /* synthetic */ void access$500(CrashlyticsController r0, String r1, Boolean r2) {
        r0.doOpenSession(r1, r2);
    }

    public static /* synthetic */ DataCollectionArbiter access$600(CrashlyticsController r0) {
        return r0.dataCollectionArbiter;
    }

    public static /* synthetic */ CrashlyticsWorkers access$700(CrashlyticsController r0) {
        return r0.crashlyticsWorkers;
    }

    public static /* synthetic */ Task access$800(CrashlyticsController r0) {
        return r0.logAnalyticsAppExceptionEvents();
    }

    public static /* synthetic */ void access$900(List r0) {
        deleteFiles(r0);
    }

    private static StaticSessionData.AppData createAppData(IdManager r6, AppData r7) {
        return StaticSessionData.AppData.create(r6.getAppIdentifier(), r7.versionCode, r7.versionName, r6.getInstallIds().getCrashlyticsInstallId(), DeliveryMechanism.determineFrom(r7.installerPackageName).getId(), r7.developmentPlatformProvider);
    }

    private static StaticSessionData.DeviceData createDeviceData(Context r16) {
        StatFs r0 = new StatFs(Environment.getDataDirectory().getPath());
        return StaticSessionData.DeviceData.create(CommonUtils.getCpuArchitectureInt(), Build.MODEL, Runtime.getRuntime().availableProcessors(), CommonUtils.calculateTotalRamInBytes(r16), r0.getBlockCount() * r0.getBlockSize(), CommonUtils.isEmulator(), CommonUtils.getDeviceState(), Build.MANUFACTURER, Build.PRODUCT);
    }

    private static StaticSessionData.OsData createOsData() {
        return StaticSessionData.OsData.create(Build.VERSION.RELEASE, Build.VERSION.CODENAME, CommonUtils.isRooted());
    }

    private static void deleteFiles(List<File> r1) {
        Iterator<File> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        r12.next().delete();
        goto L4
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void doCloseSessions(boolean r4, SettingsProvider r5, boolean r6) {
        CrashlyticsWorkers.checkBackgroundThread();
        ArrayList r0 = new ArrayList(this.reportingCoordinator.listSortedOpenSessionIds());
        if (r0.size() > r4) goto L6;
        Logger.getLogger().v("No open sessions to be closed.");
        return;
    L6:
        String r1 = (String) r0.get(r4 ? 1 : 0);
        if (r6 == true) goto L9;
    L11:
        Logger.getLogger().v("ANR feature disabled.");
    L12:
        if (r6 == false) goto L17;
        if (this.nativeComponent.hasCrashDataForSession(r1) == false) goto L17;
        finalizePreviousNativeSession(r1);
    L17:
        if (Build.VERSION.SDK_INT < 37) goto L19;
        writeProfilingManagerInfo(r1);
    L19:
        if (r4 == 0) goto L21;
        String r42 = (String) r0.get(0);
    L22:
        this.reportingCoordinator.finalizeSessions(getCurrentTimestampSeconds(), r42);
        return;
    L21:
        this.sessionsSubscriber.setSessionId(null);
        r42 = null;
        goto L22
    L9:
        if (r5.getSettingsSync().featureFlagData.collectAnrs == false) goto L11;
        writeApplicationExitInfoEventIfRelevant(r1);
        goto L12
    }

    private void doOpenSession(String r8, Boolean r9) {
        long r3 = getCurrentTimestampSeconds();
        Logger.getLogger().d("Opening a new session with ID " + r8);
        this.nativeComponent.prepareNativeSession(r8, String.format(Locale.US, "Crashlytics Android SDK/%s", new Object[]{CrashlyticsCore.getVersion()}), r3, StaticSessionData.create(createAppData(this.idManager, this.appData), createOsData(), createDeviceData(this.context)));
        if (r9.booleanValue() == false) goto L6;
        if (r8 == null) goto L6;
        this.userMetadata.setNewSession(r8);
    L6:
        this.logFileManager.setCurrentSession(r8);
        this.sessionsSubscriber.setSessionId(r8);
        this.reportingCoordinator.onBeginSession(r8, r3);
    }

    private void doWriteAppExceptionMarker(long r3) {
    L8:
        e = move-exception;
        Logger.getLogger().w("Could not create app exception marker file.", e);
        return;
    L4:
        if (this.fileStore.getCommonFile(".ae" + r3).createNewFile() == false) goto L7;
        return;
    L7:
        throw new IOException("Create new file failed.");     // Catch: IOException -> L8
    }

    private void finalizePreviousNativeSession(String r8) {
        Logger.getLogger().v("Finalizing native report for session " + r8);
        NativeSessionFileProvider r0 = this.nativeComponent.getSessionFileProvider(r8);
        File r1 = r0.getMinidumpFile();
        CrashlyticsReport.ApplicationExitInfo r2 = r0.getApplicationExitInto();
        if (nativeCoreAbsent(r8, r1, r2) == false) goto L6;
        Logger.getLogger().w("No native core present");
        return;
    L6:
        long r3 = r1.lastModified();
        LogFileManager r12 = new LogFileManager(this.fileStore, r8);
        File r5 = this.fileStore.getNativeSessionDir(r8);
        if (r5.isDirectory() == true) goto L10;
        Logger.getLogger().w("Couldn't create directory to store native session files, aborting.");
        return;
    L10:
        doWriteAppExceptionMarker(r3);
        List<NativeSessionFile> r02 = getNativeSessionFiles(r0, r8, this.fileStore, r12.getBytesForLog());
        NativeSessionFileGzipper.processNativeSessions(r5, r02);
        Logger.getLogger().d("CrashlyticsController#finalizePreviousNativeSession");
        this.reportingCoordinator.finalizeSessionWithNativeEvent(r8, r02, r2);
        r12.clearLog();
    }

    private static boolean firebaseCrashExists() {
        Class.forName("com.google.firebase.crash.FirebaseCrash");     // Catch: ClassNotFoundException -> L5
        return true;
    L5:
        return false;
    }

    private String getCurrentSessionId() {
        SortedSet<String> r1 = this.reportingCoordinator.listSortedOpenSessionIds();
        if (r1.isEmpty() == false) goto L5;
        return null;
    L5:
        return r1.first();
    }

    private static long getCurrentTimestampSeconds() {
        return getTimestampSeconds(System.currentTimeMillis());
    }

    public static List<NativeSessionFile> getNativeSessionFiles(NativeSessionFileProvider r6, String r7, FileStore r8, byte[] r9) {
        File r0 = r8.getSessionFile(r7, "user-data");
        File r2 = r8.getSessionFile(r7, "keys");
        File r72 = r8.getSessionFile(r7, "rollouts-state");
        ArrayList r82 = new ArrayList();
        r82.add(new BytesBackedNativeSessionFile("logs_file", "logs", r9));
        r82.add(new FileBackedNativeSessionFile("crash_meta_file", "metadata", r6.getMetadataFile()));
        r82.add(new FileBackedNativeSessionFile("session_meta_file", "session", r6.getSessionFile()));
        r82.add(new FileBackedNativeSessionFile("app_meta_file", Common.ASSET_APP, r6.getAppFile()));
        r82.add(new FileBackedNativeSessionFile("device_meta_file", "device", r6.getDeviceFile()));
        r82.add(new FileBackedNativeSessionFile("os_meta_file", "os", r6.getOsFile()));
        r82.add(nativeCoreFile(r6));
        r82.add(new FileBackedNativeSessionFile("user_meta_file", "user", r0));
        r82.add(new FileBackedNativeSessionFile("keys_file", "keys", r2));
        r82.add(new FileBackedNativeSessionFile("rollouts_file", "rollouts", r72));
        return r82;
    }

    private InputStream getResourceAsStream(String r1) {
        ClassLoader r0 = getClass().getClassLoader();
        if (r0 != null) goto L7;
        Logger.getLogger().w("Couldn't get Class Loader");
        return null;
    L7:
        return r0.getResourceAsStream(r1);
    }

    private static long getTimestampSeconds(long r2) {
        return r2 / 1000;
    }

    private Task<Void> logAnalyticsAppExceptionEvent(long r3) {
        if (firebaseCrashExists() == false) goto L6;
        Logger.getLogger().w("Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
        return Tasks.forResult(null);
    L6:
        Logger.getLogger().d("Logging app exception event to Firebase Analytics");
        return Tasks.call(new ScheduledThreadPoolExecutor(1), new AnonymousClass5(this, r3));
    }

    private Task<Void> logAnalyticsAppExceptionEvents() {
        ArrayList r0 = new ArrayList();
        Iterator<File> r1 = listAppExceptionMarkerFiles().iterator();
    L4:
        if (r1.hasNext() == false) goto L11;
        File r2 = r1.next();
        r0.add(logAnalyticsAppExceptionEvent(Long.parseLong(r2.getName().substring(3))));     // Catch: NumberFormatException -> L8
    L9:
        r2.delete();
    L8:
        Logger.getLogger().w("Could not parse app exception timestamp from file " + r2.getName());
        goto L9
    L11:
        return Tasks.whenAll(r0);
    }

    private static boolean nativeCoreAbsent(String r3, File r4, CrashlyticsReport.ApplicationExitInfo r5) {
        if (r4 != null) goto L4;
    L5:
        Logger.getLogger().w("No minidump data found for session " + r3);
    L6:
        if (r5 != null) goto L8;
        Logger.getLogger().i("No Tombstones data found for session " + r3);
    L8:
        if (r4 != null) goto L10;
    L11:
        if (r5 != null) goto L16;
        return true;
    L16:
        return false;
    L10:
        if (r4.exists() == false) goto L11;
        return false;
    L4:
        if (r4.exists() == true) goto L6;
        goto L5
    }

    private static NativeSessionFile nativeCoreFile(NativeSessionFileProvider r4) {
        File r42 = r4.getMinidumpFile();
        if (r42 == null) goto L10;
        if (r42.exists() == false) goto L10;
        return new FileBackedNativeSessionFile("minidump_file", "minidump", r42);
    L10:
        return new BytesBackedNativeSessionFile("minidump_file", "minidump", new byte[]{0});
    }

    private static byte[] readResource(InputStream r4) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        byte[] r1 = new byte[1024];     // Catch: Throwable -> L7
    L4:
        int r2 = r4.read(r1);     // Catch: Throwable -> L7
        if (r2 == (-1)) goto L9;
        r0.write(r1, 0, r2);     // Catch: Throwable -> L7
        goto L4
    L9:
        byte[] r42 = r0.toByteArray();     // Catch: Throwable -> L7
        r0.close();
        return r42;
    L7:
        th = move-exception;
        r0.close();     // Catch: Throwable -> L14
    L16:
        throw th;
    L14:
        th = move-exception;
        th.addSuppressed(th);
        goto L16
    }

    @SuppressLint({"WrongConstant"})
    private void registerProfilingManagerListener(String r4, @Background Executor r5) {
        ProfilingManager r0 = CrashlyticsController$$ExternalSyntheticApiModelOutline2.m(this.context.getSystemService(CrashlyticsController$$ExternalSyntheticApiModelOutline1.m()));
        r0.addProfilingTriggers(StreamSharing$$ExternalSyntheticBackport0.m(CrashlyticsController$$ExternalSyntheticApiModelOutline0.m(7).build(), CrashlyticsController$$ExternalSyntheticApiModelOutline0.m(8).build()));
        r0.registerForAllProfilingResults(r5, new CrashlyticsController$$ExternalSyntheticLambda3(this, r4));
    }

    private Task<Boolean> waitForReportAction() {
        if (this.dataCollectionArbiter.isAutomaticDataCollectionEnabled() == false) goto L6;
        Logger.getLogger().d("Automatic data collection is enabled. Allowing upload.");
        this.unsentReportsAvailable.trySetResult(Boolean.FALSE);
        return Tasks.forResult(Boolean.TRUE);
    L6:
        Logger.getLogger().d("Automatic data collection is disabled.");
        Logger.getLogger().v("Notifying that unsent reports are available.");
        this.unsentReportsAvailable.trySetResult(Boolean.TRUE);
        Task<TContinuationResult> r0 = this.dataCollectionArbiter.waitForAutomaticDataCollectionEnabled().onSuccessTask(new AnonymousClass3(this));
        Logger.getLogger().d("Waiting for send/deleteUnsentReports to be called.");
        return CrashlyticsTasks.race(r0, this.reportActionProvided.getTask());
    }

    private void writeApplicationExitInfoEventIfRelevant(String r5) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 30) goto L10;
        List<ApplicationExitInfo> r02 = ((ActivityManager) this.context.getSystemService("activity")).getHistoricalProcessExitReasons(null, 0, 0);
        if (r02.size() == 0) goto L8;
        this.reportingCoordinator.persistRelevantAppExitInfoEvent(r5, r02, new LogFileManager(this.fileStore, r5), UserMetadata.loadFromExistingSession(r5, this.fileStore, this.crashlyticsWorkers));
        return;
    L8:
        Logger.getLogger().v("No ApplicationExitInfo available. Session: " + r5);
        return;
    L10:
        Logger.getLogger().v("ANR feature enabled, but device is API " + r0);
    }

    private void writeProfilingManagerInfo(String r5) {
        ActivityManager r0 = (ActivityManager) this.context.getSystemService("activity");
        List<Integer> r1 = (List) this.fileStore.getSessionFiles(r5, new CrashlyticsController$$ExternalSyntheticLambda6()).stream().map(new CrashlyticsController$$ExternalSyntheticLambda7()).filter(new CrashlyticsController$$ExternalSyntheticLambda8()).collect(Collectors.toList());
        List<ApplicationExitInfo> r02 = r0.getHistoricalProcessExitReasons(null, 0, 0);
        this.reportingCoordinator.persistProfilingManagerInfo(r5, r1, r02);
    }

    public boolean didCrashOnPreviousExecution() {
        CrashlyticsWorkers.checkBackgroundThread();
        if (this.crashMarker.isPresent() == true) goto L11;
        String r0 = getCurrentSessionId();
        if (r0 != null) goto L7;
        return false;
    L7:
        if (this.nativeComponent.hasCrashDataForSession(r0) == false) goto L13;
        return true;
    L13:
        return false;
    L11:
        Logger.getLogger().v("Found previous crash marker.");
        this.crashMarker.remove();
        return true;
    }

    public void doCloseSessions(SettingsProvider r2) {
        doCloseSessions(false, r2, false);
    }

    public void enableExceptionHandling(String r3, Thread.UncaughtExceptionHandler r4, SettingsProvider r5) {
        this.settingsProvider = r5;
        openSession(r3);
        CrashlyticsUncaughtExceptionHandler r0 = new CrashlyticsUncaughtExceptionHandler(new AnonymousClass1(this), r5, r4, this.nativeComponent);
        this.crashHandler = r0;
        Thread.setDefaultUncaughtExceptionHandler(r0);
    }

    public void enableProfilingManagerListener(String r2) {
        registerProfilingManagerListener(r2, this.crashlyticsWorkers.diskWrite.getExecutor());
    }

    public boolean finalizeSessions(SettingsProvider r4) {
        CrashlyticsWorkers.checkBackgroundThread();
        if (isHandlingException() == false) goto L6;
        Logger.getLogger().w("Skipping session finalization because a crash has already occurred.");
        return false;
    L6:
        Logger.getLogger().v("Finalizing previously open sessions.");
        doCloseSessions(true, r4, true);     // Catch: Exception -> L10
        Logger.getLogger().v("Closed all previously open sessions.");
        return true;
    L10:
        e = move-exception;
        Logger.getLogger().e("Unable to finalize previously open sessions.", e);
        return false;
    }

    public String getVersionControlInfo() {
        String r0 = CommonUtils.getVersionControlInfo(this.context);
        if (r0 == null) goto L6;
        Logger.getLogger().d("Read version control info from string resource");
        return Base64.encodeToString(r0.getBytes(UTF_8), 0);
    L6:
        InputStream r3 = getResourceAsStream("META-INF/version-control-info.textproto");
        if (r3 != null) goto L21;
        if (r3 == null) goto L19;
        r3.close();
    L19:
        Logger.getLogger().i("No version control information found");
        return null;
    L21:
        Logger.getLogger().d("Read version control info from file");     // Catch: Throwable -> L11
        String r02 = Base64.encodeToString(readResource(r3), 0);     // Catch: Throwable -> L11
        r3.close();
        return r02;
    L11:
        th = move-exception;
        r3.close();     // Catch: Throwable -> L14
    L16:
        throw th;
    L14:
        th = move-exception;
        th.addSuppressed(th);
        goto L16
    }

    public void handleUncaughtException(SettingsProvider r2, Thread r3, Throwable r4) {
        handleUncaughtException(r2, r3, r4, false);
    }

    public synchronized void handleUncaughtException(SettingsProvider r10, Thread r11, Throwable r12, boolean r13) {
        monitor-enter(this);
        Logger.getLogger().d("Handling uncaught exception \"" + r12 + "\" from thread " + r11.getName());     // Catch: Throwable -> L24
        if (r13 == false) goto L31;
    L10:
        long r2 = System.currentTimeMillis();     // Catch: Throwable -> L24
        CrashlyticsController r1 = this;
        Task r9 = this.crashlyticsWorkers.common.submitTask(new AnonymousClass2(r1, r2, r12, r11, r10, r13));     // Catch: Throwable -> L16
        if (r13 == false) goto L33;
    L22:
        monitor-exit(r1);
        return;
    L33:
        Utils.awaitEvenIfOnMainThread(r9);     // Catch: Throwable -> L16 Exception -> L18 TimeoutException -> L21
    L21:
        Logger.getLogger().e("Cannot send reports. Timed out while fetching settings.");     // Catch: Throwable -> L16
    L18:
        e = move-exception;
        Logger.getLogger().e("Error handling uncaught exception", e);     // Catch: Throwable -> L16
        goto L22
    L31:
        CrashEventReceiver.notifyCrashOccurred();     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        Throwable r102 = th;
        r1 = this;
    L26:
        monitor-exit(r1);     // Catch: Throwable -> L16
        throw r102;
    L24:
        th = th;
        r1 = this;
    L17:
        r102 = th;
    L16:
        th = th;
        goto L17
    }

    public boolean isHandlingException() {
        CrashlyticsUncaughtExceptionHandler r0 = this.crashHandler;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isHandlingException() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public List<File> listAppExceptionMarkerFiles() {
        return this.fileStore.getCommonFiles(APP_EXCEPTION_MARKER_FILTER);
    }

    public void openSession(String r3) {
        this.crashlyticsWorkers.common.submit(new CrashlyticsController$$ExternalSyntheticLambda4(this, r3));
    }

    public void saveVersionControlInfo() {
        String r0 = getVersionControlInfo();     // Catch: IOException -> L6
        if (r0 == null) goto L11;
        setInternalKey("com.crashlytics.version-control-info", r0);     // Catch: IOException -> L6
        Logger.getLogger().i("Saved version control info");     // Catch: IOException -> L6
        return;
    L11:
        return;
    L6:
        e = move-exception;
        Logger.getLogger().w("Unable to save version control info", e);
    }

    public void setCustomKey(String r2, String r3) {
        this.userMetadata.setCustomKey(r2, r3);     // Catch: IllegalArgumentException -> L4
        return;
    L4:
        e = move-exception;
        Context r1 = this.context;
        if (r1 != null) goto L8;
    L11:
        Logger.getLogger().e("Attempting to set custom attribute with null key, ignoring.");
        return;
    L8:
        if (CommonUtils.isAppDebuggable(r1) == false) goto L11;
        throw e;
    }

    public void setInternalKey(String r2, String r3) {
        this.userMetadata.setInternalKey(r2, r3);     // Catch: IllegalArgumentException -> L4
        return;
    L4:
        e = move-exception;
        Context r1 = this.context;
        if (r1 != null) goto L8;
    L11:
        Logger.getLogger().e("Attempting to set custom attribute with null key, ignoring.");
        return;
    L8:
        if (CommonUtils.isAppDebuggable(r1) == false) goto L11;
        throw e;
    }

    public void submitAllReports(Task<Settings> r4) {
        if (this.reportingCoordinator.hasReportsToSend() == true) goto L6;
        Logger.getLogger().v("No crash reports are available to be sent.");
        this.unsentReportsAvailable.trySetResult(Boolean.FALSE);
        return;
    L6:
        Logger.getLogger().v("Crash reports are available to be sent.");
        waitForReportAction().onSuccessTask(this.crashlyticsWorkers.common, new AnonymousClass4(this, r4));
    }

    public void writeNonFatalException(Thread r5, Throwable r6, Map<String, String> r7) {
        long r0 = System.currentTimeMillis();
        if (isHandlingException() == true) goto L10;
        long r02 = getTimestampSeconds(r0);
        String r2 = getCurrentSessionId();
        if (r2 != null) goto L8;
        Logger.getLogger().w("Tried to write a non-fatal exception while no session was open.");
        return;
    L8:
        EventMetadata r3 = new EventMetadata(r2, r02, r7);
        this.reportingCoordinator.persistNonFatalEvent(r6, r5, r3);
        return;
    }

    public void writeToLog(long r2, String r4) {
        if (isHandlingException() == true) goto L6;
        this.logFileManager.writeToLog(r2, r4);
        return;
    }

    public void writeTriggerTypeFile(String r3, int r4) {
        if (r4 != 8) goto L6;
        String r42 = "trigger-type-anomaly";
    L15:
    L13:
        Logger.getLogger().e("Unable to touch trigger file ".concat(r42));
        return;
    L10:
        if (this.fileStore.getSessionFile(r3, r42).createNewFile() == true) goto L17;
        Logger.getLogger().d("Trigger file " + r42 + " exists for session: " + r3);     // Catch: IOException -> L13
        return;
    L17:
        return;
    L6:
        if (r4 != 7) goto L8;
        r42 = "trigger-type-oom";
        goto L15
    L8:
        r42 = "trigger-type-unknown";
        goto L15
    }
}
