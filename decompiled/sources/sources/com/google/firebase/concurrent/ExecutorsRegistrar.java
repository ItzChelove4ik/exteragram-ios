package com.google.firebase.concurrent;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.StrictMode;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Lazy;
import com.google.firebase.components.Qualified;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

@SuppressLint({"ThreadPoolCreation"})
/* loaded from: classes.dex */
public class ExecutorsRegistrar implements ComponentRegistrar {
    static final Lazy<ScheduledExecutorService> BG_EXECUTOR = null;
    static final Lazy<ScheduledExecutorService> BLOCKING_EXECUTOR = null;
    static final Lazy<ScheduledExecutorService> LITE_EXECUTOR = null;
    static final Lazy<ScheduledExecutorService> SCHEDULER = null;

    /* renamed from: $r8$lambda$BlSh1XUzVcreyn-V23Qag7S-0sc, reason: not valid java name */
    public static /* synthetic */ ScheduledExecutorService m1926$r8$lambda$BlSh1XUzVcreynV23Qag7S0sc(ComponentContainer r0) {
        return LITE_EXECUTOR.get();
    }

    public static /* synthetic */ ScheduledExecutorService $r8$lambda$OS8tHCI75smJjzjFpk7FAmcergI() {
        return Executors.newSingleThreadScheduledExecutor(factory("Firebase Scheduler", 0));
    }

    public static /* synthetic */ ScheduledExecutorService $r8$lambda$_E4by6EhoAkA7vHrP8i9uEoP8kU() {
        return scheduled(Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors()), factory("Firebase Lite", 0, litePolicy())));
    }

    /* renamed from: $r8$lambda$ajKL2FTOATPQn5AM-gQAEdPg3b4, reason: not valid java name */
    public static /* synthetic */ Executor m1927$r8$lambda$ajKL2FTOATPQn5AMgQAEdPg3b4(ComponentContainer r0) {
        return UiExecutor.INSTANCE;
    }

    /* renamed from: $r8$lambda$cIc-1kmvOGchekIoFcI-gGfoNXQ, reason: not valid java name */
    public static /* synthetic */ ScheduledExecutorService m1928$r8$lambda$cIc1kmvOGchekIoFcIgGfoNXQ(ComponentContainer r0) {
        return BLOCKING_EXECUTOR.get();
    }

    /* renamed from: $r8$lambda$oILf_7MyPHExPMmFKzTN2S-0gK0, reason: not valid java name */
    public static /* synthetic */ ScheduledExecutorService m1929$r8$lambda$oILf_7MyPHExPMmFKzTN2S0gK0() {
        return scheduled(Executors.newFixedThreadPool(4, factory("Firebase Background", 10, bgPolicy())));
    }

    public static /* synthetic */ ScheduledExecutorService $r8$lambda$oK_dgAjsJ4YgUN9Bhr7JxEecTHE(ComponentContainer r0) {
        return BG_EXECUTOR.get();
    }

    /* renamed from: $r8$lambda$wRz-qixVe00UVZpQosGIZYyKmqY, reason: not valid java name */
    public static /* synthetic */ ScheduledExecutorService m1930$r8$lambda$wRzqixVe00UVZpQosGIZYyKmqY() {
        return scheduled(Executors.newCachedThreadPool(factory("Firebase Blocking", 11)));
    }

    static {
        BG_EXECUTOR = new Lazy(new ExecutorsRegistrar$$ExternalSyntheticLambda0());
        LITE_EXECUTOR = new Lazy(new ExecutorsRegistrar$$ExternalSyntheticLambda1());
        BLOCKING_EXECUTOR = new Lazy(new ExecutorsRegistrar$$ExternalSyntheticLambda2());
        SCHEDULER = new Lazy(new ExecutorsRegistrar$$ExternalSyntheticLambda3());
    }

    public ExecutorsRegistrar() {
    }

    private static StrictMode.ThreadPolicy bgPolicy() {
        StrictMode.ThreadPolicy.Builder r0 = new StrictMode.ThreadPolicy.Builder().detectNetwork();
        r0.detectResourceMismatches();
        if (Build.VERSION.SDK_INT < 26) goto L6;
        r0.detectUnbufferedIo();
    L6:
        return r0.penaltyLog().build();
    }

    private static ThreadFactory factory(String r2, int r3) {
        return new CustomThreadFactory(r2, r3, null);
    }

    private static ThreadFactory factory(String r1, int r2, StrictMode.ThreadPolicy r3) {
        return new CustomThreadFactory(r1, r2, r3);
    }

    private static StrictMode.ThreadPolicy litePolicy() {
        return new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
    }

    private static ScheduledExecutorService scheduled(ExecutorService r2) {
        return new DelegatingScheduledExecutorService(r2, SCHEDULER.get());
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(Qualified.qualified(Background.class, ScheduledExecutorService.class), new Qualified[]{Qualified.qualified(Background.class, ExecutorService.class), Qualified.qualified(Background.class, Executor.class)}).factory(new ExecutorsRegistrar$$ExternalSyntheticLambda4()).build(), Component.builder(Qualified.qualified(Blocking.class, ScheduledExecutorService.class), new Qualified[]{Qualified.qualified(Blocking.class, ExecutorService.class), Qualified.qualified(Blocking.class, Executor.class)}).factory(new ExecutorsRegistrar$$ExternalSyntheticLambda5()).build(), Component.builder(Qualified.qualified(Lightweight.class, ScheduledExecutorService.class), new Qualified[]{Qualified.qualified(Lightweight.class, ExecutorService.class), Qualified.qualified(Lightweight.class, Executor.class)}).factory(new ExecutorsRegistrar$$ExternalSyntheticLambda6()).build(), Component.builder(Qualified.qualified(UiThread.class, Executor.class)).factory(new ExecutorsRegistrar$$ExternalSyntheticLambda7()).build()});
    }
}
