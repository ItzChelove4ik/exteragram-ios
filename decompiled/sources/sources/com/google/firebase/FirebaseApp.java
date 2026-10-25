package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.core.os.UserManagerCompat;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentDiscoveryService;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import com.google.firebase.concurrent.UiExecutor;
import com.google.firebase.events.Publisher;
import com.google.firebase.heartbeatinfo.DefaultHeartBeatController;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.DataCollectionConfigStorage;
import com.google.firebase.provider.FirebaseInitProvider;
import com.google.firebase.tracing.ComponentMonitor;
import com.google.firebase.tracing.FirebaseTrace;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class FirebaseApp {
    static final Map<String, FirebaseApp> INSTANCES = null;
    private static final Object LOCK = null;
    private final Context applicationContext;
    private final AtomicBoolean automaticResourceManagementEnabled;
    private final List<BackgroundStateChangeListener> backgroundStateChangeListeners;
    private final ComponentRuntime componentRuntime;
    private final Lazy<DataCollectionConfigStorage> dataCollectionConfigStorage;
    private final Provider<DefaultHeartBeatController> defaultHeartBeatController;
    private final AtomicBoolean deleted;
    private final List<FirebaseAppLifecycleListener> lifecycleListeners;
    private final String name;
    private final FirebaseOptions options;

    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean r1);
    }

    @TargetApi(14)
    public static class GlobalBackgroundStateListener implements BackgroundDetector.BackgroundStateChangeListener {
        private static AtomicReference<GlobalBackgroundStateListener> INSTANCE;

        static {
            INSTANCE = new AtomicReference();
        }

        private GlobalBackgroundStateListener() {
        }

        public static /* synthetic */ void access$000(Context r0) {
            ensureBackgroundStateListenerRegistered(r0);
        }

        private static void ensureBackgroundStateListenerRegistered(Context r3) {
            if ((r3.getApplicationContext() instanceof Application) == false) goto L13;
            Application r32 = (Application) r3.getApplicationContext();
            if (INSTANCE.get() != null) goto L11;
            GlobalBackgroundStateListener r0 = new GlobalBackgroundStateListener();
            if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(INSTANCE, null, r0) == false) goto L12;
            BackgroundDetector.initialize(r32);
            BackgroundDetector.getInstance().addListener(r0);
            return;
        L12:
            return;
        L11:
            return;
        }

        @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
        public void onBackgroundStateChanged(boolean r6) {
            Object r5 = FirebaseApp.access$200();
            monitor-enter(r5);
            ArrayList r0 = new ArrayList(FirebaseApp.INSTANCES.values());     // Catch: Throwable -> L9
            int r1 = r0.size();     // Catch: Throwable -> L9
            int r2 = 0;
        L5:
            if (r2 >= r1) goto L11;
            Object r3 = r0.get(r2);     // Catch: Throwable -> L9
            r2 = r2 + 1;     // Catch: Throwable -> L9
            FirebaseApp r32 = (FirebaseApp) r3;     // Catch: Throwable -> L9
            if (FirebaseApp.access$400(r32).get() == false) goto L5;
            FirebaseApp.access$500(r32, r6);     // Catch: Throwable -> L9
            goto L5
        L11:
            monitor-exit(r5);     // Catch: Throwable -> L9
            return;
        L9:
            th = move-exception;
            throw th;
        }
    }

    @TargetApi(24)
    /* loaded from: classes5.dex */
    public static class UserUnlockReceiver extends BroadcastReceiver {
        private static AtomicReference<UserUnlockReceiver> INSTANCE;
        private final Context applicationContext;

        static {
            INSTANCE = new AtomicReference();
        }

        public UserUnlockReceiver(Context r1) {
            this.applicationContext = r1;
        }

        public static /* synthetic */ void access$100(Context r0) {
            ensureReceiverRegistered(r0);
        }

        private static void ensureReceiverRegistered(Context r3) {
            if (INSTANCE.get() != null) goto L8;
            UserUnlockReceiver r0 = new UserUnlockReceiver(r3);
            if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(INSTANCE, null, r0) == false) goto L9;
            r3.registerReceiver(r0, new IntentFilter("android.intent.action.USER_UNLOCKED"));
            return;
        L9:
            return;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r2, Intent r3) {
            Object r22 = FirebaseApp.access$200();
            monitor-enter(r22);
            Iterator<FirebaseApp> r32 = FirebaseApp.INSTANCES.values().iterator();     // Catch: Throwable -> L8
        L6:
            if (r32.hasNext() == false) goto L10;
            FirebaseApp.access$300(r32.next());     // Catch: Throwable -> L8
            goto L6
        L10:
            monitor-exit(r22);     // Catch: Throwable -> L8
            unregister();
            return;
        L8:
            th = move-exception;
            throw th;
        }

        public void unregister() {
            this.applicationContext.unregisterReceiver(this);
        }
    }

    /* renamed from: $r8$lambda$ACPzvYj-hpDUdi_U1Jr3TE9p-sg, reason: not valid java name */
    public static /* synthetic */ void m1917$r8$lambda$ACPzvYjhpDUdi_U1Jr3TE9psg(FirebaseApp r0, boolean r1) {
        if (r1 == true) goto L5;
        r0.defaultHeartBeatController.get().registerHeartBeat();
        return;
    L5:
        r0.getClass();
    }

    public static /* synthetic */ DataCollectionConfigStorage $r8$lambda$pG3s90n26vpdSjq6F1QDhdMcBvY(FirebaseApp r3, Context r4) {
        return new DataCollectionConfigStorage(r4, r3.getPersistenceKey(), (Publisher) r3.componentRuntime.get(Publisher.class));
    }

    static {
        LOCK = new Object();
        INSTANCES = new ArrayMap();
    }

    public FirebaseApp(Context r5, String r6, FirebaseOptions r7) {
        this.automaticResourceManagementEnabled = new AtomicBoolean(false);
        this.deleted = new AtomicBoolean();
        this.backgroundStateChangeListeners = new CopyOnWriteArrayList();
        this.lifecycleListeners = new CopyOnWriteArrayList();
        this.applicationContext = (Context) Preconditions.checkNotNull(r5);
        this.name = Preconditions.checkNotEmpty(r6);
        this.options = (FirebaseOptions) Preconditions.checkNotNull(r7);
        StartupTime r62 = FirebaseInitProvider.getStartupTime();
        FirebaseTrace.pushTrace("Firebase");
        FirebaseTrace.pushTrace("ComponentDiscovery");
        List<Provider<ComponentRegistrar>> r0 = ComponentDiscovery.forContext(r5, ComponentDiscoveryService.class).discoverLazy();
        FirebaseTrace.popTrace();
        FirebaseTrace.pushTrace("Runtime");
        ComponentRuntime.Builder r72 = ComponentRuntime.builder(UiExecutor.INSTANCE).addLazyComponentRegistrars(r0).addComponentRegistrar(new FirebaseCommonRegistrar()).addComponentRegistrar(new ExecutorsRegistrar()).addComponent(Component.of(r5, Context.class, new Class[0])).addComponent(Component.of(this, FirebaseApp.class, new Class[0])).addComponent(Component.of(r7, FirebaseOptions.class, new Class[0])).setProcessor(new ComponentMonitor());
        if (UserManagerCompat.isUserUnlocked(r5) == true) goto L5;
    L7:
        ComponentRuntime r63 = r72.build();
        this.componentRuntime = r63;
        FirebaseTrace.popTrace();
        this.dataCollectionConfigStorage = new Lazy(new FirebaseApp$$ExternalSyntheticLambda0(this, r5));
        this.defaultHeartBeatController = r63.getProvider(DefaultHeartBeatController.class);
        addBackgroundStateChangeListener(new FirebaseApp$$ExternalSyntheticLambda1(this));
        FirebaseTrace.popTrace();
        return;
    L5:
        if (FirebaseInitProvider.isCurrentlyInitializing() == false) goto L7;
        r72.addComponent(Component.of(r62, StartupTime.class, new Class[0]));
        goto L7
    }

    public static /* synthetic */ Object access$200() {
        return LOCK;
    }

    public static /* synthetic */ void access$300(FirebaseApp r0) {
        r0.initializeAllApis();
    }

    public static /* synthetic */ AtomicBoolean access$400(FirebaseApp r0) {
        return r0.automaticResourceManagementEnabled;
    }

    public static /* synthetic */ void access$500(FirebaseApp r0, boolean r1) {
        r0.notifyBackgroundStateChangeListeners(r1);
    }

    private void checkNotDeleted() {
        Preconditions.checkState(!this.deleted.get(), "FirebaseApp was deleted");
    }

    public static FirebaseApp getInstance() {
        Object r1 = LOCK;
        monitor-enter(r1);
        FirebaseApp r2 = INSTANCES.get("[DEFAULT]");     // Catch: Throwable -> L9
        if (r2 == null) goto L12;
        r2.defaultHeartBeatController.get().registerHeartBeat();     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r2;
    L12:
        throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + ProcessUtils.getMyProcessName() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    private void initializeAllApis() {
        if (UserManagerCompat.isUserUnlocked(this.applicationContext) == true) goto L6;
        Log.i("FirebaseApp", "Device in Direct Boot Mode: postponing initialization of Firebase APIs for app " + getName());
        UserUnlockReceiver.access$100(this.applicationContext);
        return;
    L6:
        Log.i("FirebaseApp", "Device unlocked: initializing all Firebase APIs for app " + getName());
        this.componentRuntime.initializeEagerComponents(isDefaultApp());
        this.defaultHeartBeatController.get().registerHeartBeat();
    }

    public static FirebaseApp initializeApp(Context r3) {
        Object r0 = LOCK;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (INSTANCES.containsKey("[DEFAULT]") == false) goto L11;
        FirebaseApp r32 = getInstance();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r32;
    L11:
        FirebaseOptions r1 = FirebaseOptions.fromResource(r3);     // Catch: Throwable -> L9
        if (r1 != null) goto L16;
        Log.w("FirebaseApp", "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L16:
        FirebaseApp r33 = initializeApp(r3, r1);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r33;
    }

    public static FirebaseApp initializeApp(Context r1, FirebaseOptions r2) {
        return initializeApp(r1, r2, "[DEFAULT]");
    }

    public static FirebaseApp initializeApp(Context r5, FirebaseOptions r6, String r7) {
        GlobalBackgroundStateListener.access$000(r5);
        String r72 = normalize(r7);
        if (r5.getApplicationContext() == null) goto L6;
        r5 = r5.getApplicationContext();
    L6:
        Object r1 = LOCK;
        monitor-enter(r1);
        Map<String, FirebaseApp> r2 = INSTANCES;     // Catch: Throwable -> L12
        Preconditions.checkState(!r2.containsKey(r72), "FirebaseApp name " + r72 + " already exists!");     // Catch: Throwable -> L12
        Preconditions.checkNotNull(r5, "Application context cannot be null.");     // Catch: Throwable -> L12
        FirebaseApp r0 = new FirebaseApp(r5, r72, r6);     // Catch: Throwable -> L12
        r2.put(r72, r0);     // Catch: Throwable -> L12
        monitor-exit(r1);     // Catch: Throwable -> L12
        r0.initializeAllApis();
        return r0;
    L12:
        th = move-exception;
        throw th;
    }

    private static String normalize(String r0) {
        return r0.trim();
    }

    private void notifyBackgroundStateChangeListeners(boolean r3) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        Iterator<BackgroundStateChangeListener> r2 = this.backgroundStateChangeListeners.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        r2.next().onBackgroundStateChanged(r3);
        goto L4
    }

    public void addBackgroundStateChangeListener(BackgroundStateChangeListener r2) {
        checkNotDeleted();
        if (this.automaticResourceManagementEnabled.get() == true) goto L5;
    L7:
        this.backgroundStateChangeListeners.add(r2);
        return;
    L5:
        if (BackgroundDetector.getInstance().isInBackground() == false) goto L7;
        r2.onBackgroundStateChanged(true);
        goto L7
    }

    public void addLifecycleEventListener(FirebaseAppLifecycleListener r1) {
        checkNotDeleted();
        Preconditions.checkNotNull(r1);
        this.lifecycleListeners.add(r1);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof FirebaseApp) == true) goto L7;
        return false;
    L7:
        return this.name.equals(((FirebaseApp) r2).getName());
    }

    public <T> T get(Class<T> r1) {
        checkNotDeleted();
        return (T) this.componentRuntime.get(r1);
    }

    public Context getApplicationContext() {
        checkNotDeleted();
        return this.applicationContext;
    }

    public String getName() {
        checkNotDeleted();
        return this.name;
    }

    public FirebaseOptions getOptions() {
        checkNotDeleted();
        return this.options;
    }

    public String getPersistenceKey() {
        return Base64Utils.encodeUrlSafeNoPadding(getName().getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(getOptions().getApplicationId().getBytes(Charset.defaultCharset()));
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean isDataCollectionDefaultEnabled() {
        checkNotDeleted();
        return this.dataCollectionConfigStorage.get().isEnabled();
    }

    public boolean isDefaultApp() {
        return "[DEFAULT]".equals(getName());
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("options", this.options).toString();
    }
}
