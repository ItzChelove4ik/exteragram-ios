package com.google.firebase.crashlytics;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.remoteconfig.interop.FirebaseRemoteConfigInterop;
import com.google.firebase.sessions.api.FirebaseSessionsDependencies;
import com.google.firebase.sessions.api.SessionSubscriber;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class CrashlyticsRegistrar implements ComponentRegistrar {
    private final Qualified<ExecutorService> backgroundExecutorService;
    private final Qualified<ExecutorService> blockingExecutorService;
    private final Qualified<ExecutorService> lightweightExecutorService;

    public static /* synthetic */ FirebaseCrashlytics $r8$lambda$XFR966Xtld0zGICMasGRYAhWin4(CrashlyticsRegistrar r0, ComponentContainer r1) {
        return r0.buildCrashlytics(r1);
    }

    static {
        FirebaseSessionsDependencies.addDependency(SessionSubscriber.Name.CRASHLYTICS);
    }

    public CrashlyticsRegistrar() {
        this.backgroundExecutorService = Qualified.qualified(Background.class, ExecutorService.class);
        this.blockingExecutorService = Qualified.qualified(Blocking.class, ExecutorService.class);
        this.lightweightExecutorService = Qualified.qualified(Lightweight.class, ExecutorService.class);
    }

    private FirebaseCrashlytics buildCrashlytics(ComponentContainer r12) {
        CrashlyticsWorkers.setEnforcement(false);
        long r0 = System.currentTimeMillis();
        FirebaseCrashlytics r11 = FirebaseCrashlytics.init((FirebaseApp) r12.get(FirebaseApp.class), (FirebaseInstallationsApi) r12.get(FirebaseInstallationsApi.class), r12.getDeferred(CrashlyticsNativeComponent.class), r12.getDeferred(AnalyticsConnector.class), r12.getDeferred(FirebaseRemoteConfigInterop.class), (ExecutorService) r12.get(this.backgroundExecutorService), (ExecutorService) r12.get(this.blockingExecutorService), (ExecutorService) r12.get(this.lightweightExecutorService));
        long r2 = System.currentTimeMillis() - r0;
        if (r2 <= 16) goto L5;
        Logger.getLogger().d("Initializing Crashlytics blocked main for " + r2 + " ms");
    L5:
        return r11;
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseCrashlytics.class).name("fire-cls").add(Dependency.required(FirebaseApp.class)).add(Dependency.required(FirebaseInstallationsApi.class)).add(Dependency.required(this.backgroundExecutorService)).add(Dependency.required(this.blockingExecutorService)).add(Dependency.required(this.lightweightExecutorService)).add(Dependency.deferred(CrashlyticsNativeComponent.class)).add(Dependency.deferred(AnalyticsConnector.class)).add(Dependency.deferred(FirebaseRemoteConfigInterop.class)).factory(new CrashlyticsRegistrar$$ExternalSyntheticLambda0(this)).eagerInDefaultApp().build(), LibraryVersionComponent.create("fire-cls", "20.1.0")});
    }
}
