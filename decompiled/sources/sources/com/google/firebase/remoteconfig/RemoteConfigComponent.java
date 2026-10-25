package com.google.firebase.remoteconfig;

import android.app.Application;
import android.content.Context;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigFetchHttpClient;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigRealtimeHandler;
import com.google.firebase.remoteconfig.internal.ConfigSharedPrefsClient;
import com.google.firebase.remoteconfig.internal.ConfigStorageClient;
import com.google.firebase.remoteconfig.internal.Personalization;
import com.google.firebase.remoteconfig.internal.rollouts.RolloutsStateFactory;
import com.google.firebase.remoteconfig.internal.rollouts.RolloutsStateSubscriptionsHandler;
import com.google.firebase.remoteconfig.interop.FirebaseRemoteConfigInterop;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class RemoteConfigComponent implements FirebaseRemoteConfigInterop {
    private static final Clock DEFAULT_CLOCK = null;
    private static final Random DEFAULT_RANDOM = null;
    private static final Map<String, FirebaseRemoteConfig> frcNamespaceInstancesStatic = null;
    private final Provider<AnalyticsConnector> analyticsConnector;
    private final String appId;
    private final Context context;
    private Map<String, String> customHeaders;
    private final ScheduledExecutorService executor;
    private final FirebaseABTesting firebaseAbt;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Map<String, FirebaseRemoteConfig> frcNamespaceInstances;

    public static class GlobalBackgroundListener implements BackgroundDetector.BackgroundStateChangeListener {
        private static final AtomicReference<GlobalBackgroundListener> INSTANCE = null;

        static {
            INSTANCE = new AtomicReference();
        }

        private GlobalBackgroundListener() {
        }

        public static /* synthetic */ void access$000(Context r0) {
            ensureBackgroundListenerIsRegistered(r0);
        }

        private static void ensureBackgroundListenerIsRegistered(Context r3) {
            Application r32 = (Application) r3.getApplicationContext();
            AtomicReference<GlobalBackgroundListener> r0 = INSTANCE;
            if (r0.get() != null) goto L8;
            GlobalBackgroundListener r1 = new GlobalBackgroundListener();
            if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(r0, null, r1) == false) goto L9;
            BackgroundDetector.initialize(r32);
            BackgroundDetector.getInstance().addListener(r1);
            return;
        L9:
            return;
        }

        @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
        public void onBackgroundStateChanged(boolean r1) {
            RemoteConfigComponent.access$100(r1);
        }
    }

    /* renamed from: $r8$lambda$Sf9RT1RfRnHdNJXG7KvWe-BAHSc, reason: not valid java name */
    public static /* synthetic */ AnalyticsConnector m1970$r8$lambda$Sf9RT1RfRnHdNJXG7KvWeBAHSc() {
        return null;
    }

    static {
        DEFAULT_CLOCK = DefaultClock.getInstance();
        DEFAULT_RANDOM = new Random();
        frcNamespaceInstancesStatic = new HashMap();
    }

    public RemoteConfigComponent(Context r9, @Blocking ScheduledExecutorService r10, FirebaseApp r11, FirebaseInstallationsApi r12, FirebaseABTesting r13, Provider<AnalyticsConnector> r14) {
        this(r9, r10, r11, r12, r13, r14, true);
    }

    public RemoteConfigComponent(Context r2, ScheduledExecutorService r3, FirebaseApp r4, FirebaseInstallationsApi r5, FirebaseABTesting r6, Provider<AnalyticsConnector> r7, boolean r8) {
        this.frcNamespaceInstances = new HashMap();
        this.customHeaders = new HashMap();
        this.context = r2;
        this.executor = r3;
        this.firebaseApp = r4;
        this.firebaseInstallations = r5;
        this.firebaseAbt = r6;
        this.analyticsConnector = r7;
        this.appId = r4.getOptions().getApplicationId();
        GlobalBackgroundListener.access$000(r2);
        if (r8 == false) goto L6;
        Tasks.call(r3, new RemoteConfigComponent$$ExternalSyntheticLambda0(this));
        return;
    }

    public static /* synthetic */ void access$100(boolean r0) {
        notifyRCInstances(r0);
    }

    private ConfigCacheClient getCacheClient(String r3, String r4) {
        String r32 = String.format("%s_%s_%s_%s.json", new Object[]{"frc", this.appId, r3, r4});
        return ConfigCacheClient.getInstance(this.executor, ConfigStorageClient.getInstance(this.context, r32));
    }

    private ConfigGetParameterHandler getGetHandler(ConfigCacheClient r2, ConfigCacheClient r3) {
        return new ConfigGetParameterHandler(this.executor, r2, r3);
    }

    private static Personalization getPersonalization(FirebaseApp r0, String r1, Provider<AnalyticsConnector> r2) {
        if (isPrimaryApp(r0) == true) goto L5;
        return null;
    L5:
        if (r1.equals("firebase") == true) goto L7;
        return null;
    L7:
        return new Personalization(r2);
    }

    private RolloutsStateSubscriptionsHandler getRolloutsStateSubscriptionsHandler(ConfigCacheClient r2, ConfigCacheClient r3) {
        return new RolloutsStateSubscriptionsHandler(r2, RolloutsStateFactory.create(r2, r3), this.executor);
    }

    public static ConfigSharedPrefsClient getSharedPrefsClient(Context r2, String r3, String r4) {
        return new ConfigSharedPrefsClient(r2.getSharedPreferences(String.format("%s_%s_%s_%s", new Object[]{"frc", r3, r4, "settings"}), 0));
    }

    private static boolean isAbtSupported(FirebaseApp r1, String r2) {
        if (r2.equals("firebase") == true) goto L5;
        return false;
    L5:
        if (isPrimaryApp(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isPrimaryApp(FirebaseApp r1) {
        return r1.getName().equals("[DEFAULT]");
    }

    private static synchronized void notifyRCInstances(boolean r3) {
        monitor-enter(RemoteConfigComponent.class);
        Iterator<FirebaseRemoteConfig> r1 = frcNamespaceInstancesStatic.values().iterator();     // Catch: Throwable -> L9
    L5:
        if (r1.hasNext() == false) goto L11;
        r1.next().setConfigUpdateBackgroundState(r3);     // Catch: Throwable -> L9
        goto L5
    L11:
        monitor-exit(RemoteConfigComponent.class);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public synchronized FirebaseRemoteConfig get(FirebaseApp r17, String r18, FirebaseInstallationsApi r19, FirebaseABTesting r20, Executor r21, ConfigCacheClient r22, ConfigCacheClient r23, ConfigCacheClient r24, ConfigFetchHandler r25, ConfigGetParameterHandler r26, ConfigSharedPrefsClient r27, RolloutsStateSubscriptionsHandler r28) {
        monitor-enter(this);
    L13:
        th = th;
    L21:
        monitor-exit(this);     // Catch: Throwable -> L13
        throw th;
    L15:
        th = th;
        goto L21
    L5:
        if (this.frcNamespaceInstances.containsKey(r18) == true) goto L17;
        Context r9 = this.context;     // Catch: Throwable -> L15
        if (isAbtSupported(r17, r18) == false) goto L9;
        FirebaseABTesting r10 = r20;
    L10:
        RemoteConfigComponent r15 = this;
        String r0 = r18;
        FirebaseRemoteConfig r02 = new FirebaseRemoteConfig(r9, r17, r19, r10, r21, r22, r23, r24, r25, r26, r27, getRealtime(r17, r19, r25, r23, this.context, r18, r27), r28);     // Catch: Throwable -> L13
        r02.startLoadingConfigsFromDisk();     // Catch: Throwable -> L13
        r15.frcNamespaceInstances.put(r0, r02);     // Catch: Throwable -> L13
        frcNamespaceInstancesStatic.put(r0, r02);     // Catch: Throwable -> L13
    L18:
        FirebaseRemoteConfig r03 = r15.frcNamespaceInstances.get(r0);     // Catch: Throwable -> L13
        monitor-exit(this);
        return r03;
    L9:
        r10 = null;
        goto L10
    L17:
        r15 = this;
        r0 = r18;
        goto L18
    }

    public synchronized FirebaseRemoteConfig get(String r15) {
        monitor-enter(this);
        ConfigCacheClient r7 = getCacheClient(r15, "fetch");     // Catch: Throwable -> L16
        ConfigCacheClient r8 = getCacheClient(r15, "activate");     // Catch: Throwable -> L16
        ConfigCacheClient r9 = getCacheClient(r15, "defaults");     // Catch: Throwable -> L16
        ConfigSharedPrefsClient r12 = getSharedPrefsClient(this.context, this.appId, r15);     // Catch: Throwable -> L16
        ConfigGetParameterHandler r11 = getGetHandler(r8, r9);     // Catch: Throwable -> L16
        Personalization r0 = getPersonalization(this.firebaseApp, r15, this.analyticsConnector);     // Catch: Throwable -> L16
        if (r0 != null) goto L22;
    L9:
        RolloutsStateSubscriptionsHandler r13 = getRolloutsStateSubscriptionsHandler(r8, r9);     // Catch: Throwable -> L16
        FirebaseRemoteConfig r14 = get(this.firebaseApp, r15, this.firebaseInstallations, this.firebaseAbt, this.executor, r7, r8, r9, getFetchHandler(r15, r7, r12), r11, r12, r13);     // Catch: Throwable -> L14
        monitor-exit(this);
        return r14;
    L22:
        r11.addListener(new RemoteConfigComponent$$ExternalSyntheticLambda1(r0));     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        Throwable r152 = th;
        RemoteConfigComponent r1 = this;
    L18:
        monitor-exit(r1);     // Catch: Throwable -> L14
        throw r152;
    L16:
        th = th;
        r1 = this;
    L15:
        r152 = th;
    L14:
        th = th;
        goto L15
    }

    public FirebaseRemoteConfig getDefault() {
        return get("firebase");
    }

    public synchronized ConfigFetchHandler getFetchHandler(String r11, ConfigCacheClient r12, ConfigSharedPrefsClient r13) {
        monitor-enter(this);
        FirebaseInstallationsApi r1 = this.firebaseInstallations;     // Catch: Throwable -> L6
        if (isPrimaryApp(this.firebaseApp) == false) goto L8;
        Provider r2 = this.analyticsConnector;     // Catch: Throwable -> L6
    L9:
        ConfigFetchHandler r0 = new ConfigFetchHandler(r1, r2, this.executor, DEFAULT_CLOCK, DEFAULT_RANDOM, r12, getFrcBackendApiClient(this.firebaseApp.getOptions().getApiKey(), r11, r13), r13, this.customHeaders);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L8:
        r2 = new RemoteConfigComponent$$ExternalSyntheticLambda2();     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        throw th;
    }

    public ConfigFetchHttpClient getFrcBackendApiClient(String r11, String r12, ConfigSharedPrefsClient r13) {
        String r3 = this.firebaseApp.getOptions().getApplicationId();
        return new ConfigFetchHttpClient(this.context, r3, r11, r12, r13.getFetchTimeoutInSeconds(), r13.getFetchTimeoutInSeconds());
    }

    public synchronized ConfigRealtimeHandler getRealtime(FirebaseApp r10, FirebaseInstallationsApi r11, ConfigFetchHandler r12, ConfigCacheClient r13, Context r14, String r15, ConfigSharedPrefsClient r16) {
        monitor-enter(this);
        ConfigRealtimeHandler r0 = new ConfigRealtimeHandler(r10, r11, r12, r13, r14, r15, r16, this.executor);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // com.google.firebase.remoteconfig.interop.FirebaseRemoteConfigInterop
    public void registerRolloutsStateSubscriber(String r1, RolloutsStateSubscriber r2) {
        get(r1).getRolloutsStateSubscriptionsHandler().registerRolloutsStateSubscriber(r2);
    }
}
