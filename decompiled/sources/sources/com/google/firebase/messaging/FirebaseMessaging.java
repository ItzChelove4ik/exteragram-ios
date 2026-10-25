package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Keep;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.messaging.Store;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class FirebaseMessaging {
    private static final long MAX_DELAY_SEC = 0;
    private static Store store;
    static ScheduledExecutorService syncExecutor;
    static Provider<TransportFactory> transportFactory;
    private final AutoInit autoInit;
    private final Context context;
    private final Executor fileExecutor;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallationsApi;
    private final GmsRegistrationClient gmsRegistrationClient;
    private final GmsRpc gmsRpc;
    private final Executor initExecutor;
    private final Application.ActivityLifecycleCallbacks lifecycleCallbacks;
    private final Metadata metadata;
    private final RequestDeduplicator requestDeduplicator;
    private boolean syncScheduledOrRunning;
    private final Task<TopicsSubscriber> topicsSubscriberTask;

    public class AutoInit {
        private Boolean autoInitEnabled;
        private EventHandler<DataCollectionDefaultChange> dataCollectionDefaultChangeEventHandler;
        private boolean initialized;
        private final Subscriber subscriber;
        final /* synthetic */ FirebaseMessaging this$0;

        public static /* synthetic */ void $r8$lambda$RHbnQNQxeZwzPe_AKfWLj3vsXZc(AutoInit r0, Event r1) {
            if (r0.isEnabled() == false) goto L6;
            FirebaseMessaging.access$100(r0.this$0);
            return;
        }

        public AutoInit(FirebaseMessaging r1, Subscriber r2) {
            this.this$0 = r1;
            this.subscriber = r2;
        }

        private Boolean readEnabled() {
            Context r5 = FirebaseMessaging.access$000(this.this$0).getApplicationContext();
            SharedPreferences r1 = r5.getSharedPreferences("com.google.firebase.messaging", 0);
            if (r1.contains("auto_init") == true) goto L5;
            PackageManager r12 = r5.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L18
            if (r12 == null) goto L16;
            ApplicationInfo r52 = r12.getApplicationInfo(r5.getPackageName(), 128);     // Catch: PackageManager.NameNotFoundException -> L18
            if (r52 == null) goto L21;
            Bundle r13 = r52.metaData;     // Catch: PackageManager.NameNotFoundException -> L18
            if (r13 != null) goto L13;
            return null;
        L13:
            if (r13.containsKey("firebase_messaging_auto_init_enabled") == false) goto L23;
            return Boolean.valueOf(r52.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
        L23:
            return null;
        L21:
            return null;
        L16:
            return null;
        L24:
            return null;
        L5:
            return Boolean.valueOf(r1.getBoolean("auto_init", false));
        }

        public synchronized void initialize() {
            monitor-enter(this);
        L10:
            th = move-exception;
            throw th;
        L4:
            if (this.initialized == false) goto L7;
            monitor-exit(this);
            return;
        L7:
            Boolean r0 = readEnabled();     // Catch: Throwable -> L10
            this.autoInitEnabled = r0;     // Catch: Throwable -> L10
            if (r0 != null) goto L12;
            FirebaseMessaging$AutoInit$$ExternalSyntheticLambda0 r02 = new FirebaseMessaging$AutoInit$$ExternalSyntheticLambda0(this);     // Catch: Throwable -> L10
            this.dataCollectionDefaultChangeEventHandler = r02;     // Catch: Throwable -> L10
            this.subscriber.subscribe(DataCollectionDefaultChange.class, r02);     // Catch: Throwable -> L10
        L12:
            this.initialized = true;     // Catch: Throwable -> L10
            monitor-exit(this);
        }

        public synchronized boolean isEnabled() {
            monitor-enter(this);
            initialize();     // Catch: Throwable -> L6
            Boolean r0 = this.autoInitEnabled;     // Catch: Throwable -> L6
            if (r0 == null) goto L8;
            boolean r02 = r0.booleanValue();     // Catch: Throwable -> L6
        L9:
            monitor-exit(this);
            return r02;
        L8:
            r02 = FirebaseMessaging.access$000(this.this$0).isDataCollectionDefaultEnabled();     // Catch: Throwable -> L6
        L6:
            th = move-exception;
            throw th;
        }
    }

    /* renamed from: $r8$lambda$8G-h7_n0Um1DYcfUNw5VOGbjt-M, reason: not valid java name */
    public static /* synthetic */ void m1959$r8$lambda$8Gh7_n0Um1DYcfUNw5VOGbjtM(FirebaseMessaging r1) {
        if (r1.isAutoInitEnabled() == false) goto L6;
        r1.startSyncIfNecessary();
        return;
    }

    public static /* synthetic */ void $r8$lambda$AU6OfTfNF2LL9uPKjic_R9XN5tQ(FirebaseMessaging r1, TaskCompletionSource r2) {
        r1.getClass();
        r2.setResult(r1.blockingRegister(false));     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        r2.setException(e);
    }

    public static /* synthetic */ TransportFactory $r8$lambda$LFn9_qtEIJ6bEv_Ds1y45k5yLjU() {
        return null;
    }

    /* renamed from: $r8$lambda$Qxr5SA_jbDqRuye9ca3FS-HgRPQ, reason: not valid java name */
    public static /* synthetic */ void m1960$r8$lambda$Qxr5SA_jbDqRuye9ca3FSHgRPQ(FirebaseMessaging r0, TopicsSubscriber r1) {
        if (r0.isAutoInitEnabled() == false) goto L6;
        r1.startTopicsSyncIfNecessary();
        return;
    }

    /* renamed from: $r8$lambda$atZ3P6LfRlnvgw-bdmnHzXoaDLc, reason: not valid java name */
    public static /* synthetic */ void m1961$r8$lambda$atZ3P6LfRlnvgwbdmnHzXoaDLc(FirebaseMessaging r1, String r2) {
        if (r1.getTokenWithoutTriggeringSync() != null) goto L6;
        return;
    L6:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L8;
        Log.d("FirebaseMessaging", "FID Change detected! Triggering re-sync");
    L8:
        r1.startSync();
    }

    /* renamed from: $r8$lambda$tNgiC8H-lRw8jP7GhA3Sh30N9gU, reason: not valid java name */
    public static /* synthetic */ Task m1962$r8$lambda$tNgiC8HlRw8jP7GhA3Sh30N9gU(FirebaseMessaging r3, String r4, boolean r5, Store.Token r6) {
        return r3.gmsRegistrationClient.register().onSuccessTask(r3.fileExecutor, new FirebaseMessaging$$ExternalSyntheticLambda9(r3, r4, r5, r6));
    }

    public static /* synthetic */ Task $r8$lambda$uomApBO9Rimks2gDHrUhO7dV5io(FirebaseMessaging r3, String r4, boolean r5, Store.Token r6, String r7) {
        getStore(r3.context).saveToken(r3.getSubtype(), r4, r7, r3.metadata.getAppVersionCode());
        if (r3.gmsRegistrationClient.isV1RegistrationEnabled() == true) goto L8;
        if (r5 == true) goto L8;
        if (r6 == null) goto L8;
        if (r7.equals(r6.token) == false) goto L8;
    L10:
        return Tasks.forResult(r7);
    L8:
        r3.invokeOnRegistrationChanged(r7, false);
        goto L10
    }

    public static /* synthetic */ void $r8$lambda$x_lbHzV0qp229SVkb6by5S67fLg(FirebaseMessaging r0) {
        r0.initializeProxyNotifications();
    }

    /* renamed from: $r8$lambda$yrmi9keLOjePJlj1v0bkHmg-Xns, reason: not valid java name */
    public static /* synthetic */ void m1963$r8$lambda$yrmi9keLOjePJlj1v0bkHmgXns(FirebaseMessaging r0, CloudMessage r1) {
        r0.getClass();
        if (r1 == null) goto L6;
        MessagingAnalytics.logNotificationReceived(r1.getIntent());
        r0.handleProxiedNotificationData();
        return;
    }

    static {
        MAX_DELAY_SEC = 28800;
        transportFactory = new FirebaseMessaging$$ExternalSyntheticLambda0();
    }

    public FirebaseMessaging(FirebaseApp r8, FirebaseInstanceIdInternal r9, Provider<TransportFactory> r10, Subscriber r11, Metadata r12, GmsRpc r13, FirebaseInstallationsApi r14, Executor r15, Executor r16, Executor r17) {
        this.syncScheduledOrRunning = false;
        transportFactory = r10;
        this.firebaseApp = r8;
        this.autoInit = new AutoInit(this, r11);
        Context r1 = r8.getApplicationContext();
        this.context = r1;
        FcmLifecycleCallbacks r102 = new FcmLifecycleCallbacks();
        this.lifecycleCallbacks = r102;
        this.metadata = r12;
        this.gmsRpc = r13;
        this.firebaseInstallationsApi = r14;
        GmsRegistrationClient r0 = new GmsRegistrationClient(r1, r8, r14, r13, r12);
        this.gmsRegistrationClient = r0;
        this.requestDeduplicator = new RequestDeduplicator(r15);
        this.initExecutor = r16;
        this.fileExecutor = r17;
        Context r112 = r8.getApplicationContext();
        if ((r112 instanceof Application) == false) goto L5;
        ((Application) r112).registerActivityLifecycleCallbacks(r102);
    L6:
        if (r9 == null) goto L11;
        if (r0.isV1RegistrationEnabled() == true) goto L11;
        r9.addNewTokenListener(new FirebaseMessaging$$ExternalSyntheticLambda1(this));
    L11:
        if (r0.isV1RegistrationEnabled() == false) goto L13;
        r14.registerFidListener(new FirebaseMessaging$$ExternalSyntheticLambda2(this));
    L13:
        r16.execute(new FirebaseMessaging$$ExternalSyntheticLambda3(this));
        Task<TopicsSubscriber> r82 = TopicsSubscriber.createInstance(r8, this, r14, r12, r1, FcmExecutors.newTopicsSyncExecutor());
        this.topicsSubscriberTask = r82;
        r82.addOnSuccessListener(r16, new FirebaseMessaging$$ExternalSyntheticLambda4(this));
        r16.execute(new FirebaseMessaging$$ExternalSyntheticLambda5(this));
        return;
    L5:
        Log.w("FirebaseMessaging", "Context " + r112 + " was not an application, can't register for lifecycle callbacks. Some notification events may be dropped as a result.");
        goto L6
    }

    public FirebaseMessaging(FirebaseApp r10, FirebaseInstanceIdInternal r11, Provider<UserAgentPublisher> r12, Provider<HeartBeatInfo> r13, FirebaseInstallationsApi r14, Provider<TransportFactory> r15, Subscriber r16) {
        this(r10, r11, r12, r13, r14, r15, r16, new Metadata(r10.getApplicationContext()));
    }

    public FirebaseMessaging(FirebaseApp r12, FirebaseInstanceIdInternal r13, Provider<UserAgentPublisher> r14, Provider<HeartBeatInfo> r15, FirebaseInstallationsApi r16, Provider<TransportFactory> r17, Subscriber r18, Metadata r19) {
        this(r12, r13, r17, r18, r19, new GmsRpc(r12, r19, r14, r15, r16), r16, FcmExecutors.newTaskExecutor(), FcmExecutors.newInitExecutor(), FcmExecutors.newFileIOExecutor());
    }

    public static /* synthetic */ FirebaseApp access$000(FirebaseMessaging r0) {
        return r0.firebaseApp;
    }

    public static /* synthetic */ void access$100(FirebaseMessaging r0) {
        r0.startSyncIfNecessary();
    }

    private String fetchFid() {
        return (String) Tasks.await(this.firebaseInstallationsApi.getId());
    L4:
        return null;
    }

    public static synchronized FirebaseMessaging getInstance() {
        monitor-enter(FirebaseMessaging.class);
        FirebaseMessaging r1 = getInstance(FirebaseApp.getInstance());     // Catch: Throwable -> L7
        monitor-exit(FirebaseMessaging.class);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Keep
    @Deprecated
    public static synchronized FirebaseMessaging getInstance(FirebaseApp r2) {
        monitor-enter(FirebaseMessaging.class);
        FirebaseMessaging r22 = (FirebaseMessaging) r2.get(FirebaseMessaging.class);     // Catch: Throwable -> L7
        Preconditions.checkNotNull(r22, "Firebase Messaging component is not present");     // Catch: Throwable -> L7
        monitor-exit(FirebaseMessaging.class);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    private static synchronized Store getStore(Context r2) {
        monitor-enter(FirebaseMessaging.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (store != null) goto L9;
        store = new Store(r2);     // Catch: Throwable -> L7
    L9:
        Store r22 = store;     // Catch: Throwable -> L7
        monitor-exit(FirebaseMessaging.class);
        return r22;
    }

    private String getSubtype() {
        if ("[DEFAULT]".equals(this.firebaseApp.getName()) == false) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return this.firebaseApp.getPersistenceKey();
    }

    public static TransportFactory getTransportFactory() {
        return transportFactory.get();
    }

    private void handleProxiedNotificationData() {
        this.gmsRpc.getProxyNotificationData().addOnSuccessListener(this.initExecutor, new FirebaseMessaging$$ExternalSyntheticLambda8(this));
    }

    private void initializeProxyNotifications() {
        ProxyNotificationInitializer.initialize(this.context);
        ProxyNotificationPreferences.setProxyRetention(this.context, this.gmsRpc, shouldRetainProxyNotifications());
        if (shouldRetainProxyNotifications() == false) goto L6;
        handleProxiedNotificationData();
        return;
    }

    private void invokeOnRegistrationChanged(String r4, boolean r5) {
        if ("[DEFAULT]".equals(this.firebaseApp.getName()) == true) goto L6;
        return;
    L6:
        if (Log.isLoggable("FirebaseMessaging", 3) == false) goto L8;
        Log.d("FirebaseMessaging", "Invoking onNewToken for app: " + this.firebaseApp.getName());
    L8:
        boolean r0 = this.gmsRegistrationClient.isV1RegistrationEnabled();
        Intent r1 = new Intent();
        r1.putExtra("token", r4);
        if (r0 == false) goto L14;
        if (r5 == false) goto L12;
        String r42 = "com.google.firebase.messaging.FCM_UNREGISTERED";
    L13:
        r1.setAction(r42);
    L16:
        new FcmBroadcastProcessor(this.context).process(r1);
        return;
    L12:
        r42 = "com.google.firebase.messaging.FCM_REGISTERED";
        goto L13
    L14:
        if (r5 == true) goto L18;
        r1.setAction("com.google.firebase.messaging.NEW_TOKEN");
        goto L16
    }

    private boolean shouldRetainProxyNotifications() {
        ProxyNotificationInitializer.initialize(this.context);
        if (ProxyNotificationInitializer.isProxyNotificationEnabled(this.context) == true) goto L6;
        return false;
    L6:
        if (this.firebaseApp.get(AnalyticsConnector.class) == null) goto L9;
        return true;
    L9:
        if (MessagingAnalytics.deliveryMetricsExportToBigQueryEnabled() == true) goto L11;
    L13:
        return false;
    L11:
        if (transportFactory == null) goto L13;
        return true;
    }

    private synchronized void startSync() {
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L4:
        if (this.syncScheduledOrRunning == true) goto L9;
        syncWithDelaySecondsInternal(0);     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
    }

    private void startSyncIfNecessary() {
        if (tokenNeedsRefresh(getTokenWithoutTriggeringSync()) == false) goto L6;
        startSync();
        return;
    }

    public String blockingGetToken() {
        return blockingRegister(false);
    }

    public String blockingRegister(boolean r5) {
        Store.Token r0 = getTokenWithoutTriggeringSync();
        if (tokenNeedsRefresh(r0) == true) goto L8;
        if (r5 == false) goto L7;
        invokeOnRegistrationChanged(r0.token, false);
    L7:
        return r0.token;
    L8:
        String r1 = Metadata.getDefaultSenderId(this.firebaseApp);
        return (String) Tasks.await(this.requestDeduplicator.getOrStartGetTokenRequest(r1, new FirebaseMessaging$$ExternalSyntheticLambda6(this, r1, r5, r0)));
    L11:
        e = move-exception;
        throw new IOException("FCM Registration failed!", e);
    }

    @SuppressLint({"ThreadPoolCreation"})
    public void enqueueTaskWithDelaySeconds(Runnable r4, long r5) {
        monitor-enter(FirebaseMessaging.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (syncExecutor != null) goto L9;
        syncExecutor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("TAG"));     // Catch: Throwable -> L7
    L9:
        syncExecutor.schedule(r4, r5, TimeUnit.SECONDS);     // Catch: Throwable -> L7
        monitor-exit(FirebaseMessaging.class);     // Catch: Throwable -> L7
    }

    public Context getApplicationContext() {
        return this.context;
    }

    @Deprecated
    public Task<String> getToken() {
        if (this.gmsRegistrationClient.isV1RegistrationEnabled() == true) goto L5;
        TaskCompletionSource r0 = new TaskCompletionSource();
        this.initExecutor.execute(new FirebaseMessaging$$ExternalSyntheticLambda7(this, r0));
        return r0.getTask();
    L5:
        return Tasks.forException(new IllegalStateException("API disabled. Please use {@link #register()} instead or enable this API by removing {@code <meta-data android:name=\"firebase_messaging_installation_id_enabled\" android:value=\"true\" />} from your app's manifest."));
    }

    public Store.Token getTokenWithoutTriggeringSync() {
        return getStore(this.context).getToken(getSubtype(), Metadata.getDefaultSenderId(this.firebaseApp));
    }

    public boolean isAutoInitEnabled() {
        return this.autoInit.isEnabled();
    }

    public boolean isGmsCorePresent() {
        return this.metadata.isGmscorePresent();
    }

    public synchronized void setSyncScheduledOrRunning(boolean r1) {
        monitor-enter(this);
        this.syncScheduledOrRunning = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void syncWithDelaySecondsInternal(long r5) {
        monitor-enter(this);
        enqueueTaskWithDelaySeconds(new SyncTask(this, Math.min(Math.max(30, 2 * r5), MAX_DELAY_SEC)), r5);     // Catch: Throwable -> L7
        this.syncScheduledOrRunning = true;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean tokenNeedsRefresh(Store.Token r3) {
        if (r3 != null) goto L5;
    L13:
        return true;
    L5:
        if (r3.needsRefresh(this.metadata.getAppVersionCode()) == true) goto L13;
        if (this.gmsRegistrationClient.isV1RegistrationEnabled() == false) goto L12;
        String r2 = fetchFid();
        return !r3.token.equalsIgnoreCase(r2);
    L12:
        return r3.isFid();
    }
}
