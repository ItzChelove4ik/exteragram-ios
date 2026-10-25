package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Uploader_Factory implements Factory<Uploader> {
    private final Provider<BackendRegistry> backendRegistryProvider;
    private final Provider<ClientHealthMetricsStore> clientHealthMetricsStoreProvider;
    private final Provider<Clock> clockProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventStore> eventStoreProvider;
    private final Provider<Executor> executorProvider;
    private final Provider<SynchronizationGuard> guardProvider;
    private final Provider<Clock> uptimeClockProvider;
    private final Provider<WorkScheduler> workSchedulerProvider;

    public Uploader_Factory(Provider<Context> r1, Provider<BackendRegistry> r2, Provider<EventStore> r3, Provider<WorkScheduler> r4, Provider<Executor> r5, Provider<SynchronizationGuard> r6, Provider<Clock> r7, Provider<Clock> r8, Provider<ClientHealthMetricsStore> r9) {
        this.contextProvider = r1;
        this.backendRegistryProvider = r2;
        this.eventStoreProvider = r3;
        this.workSchedulerProvider = r4;
        this.executorProvider = r5;
        this.guardProvider = r6;
        this.clockProvider = r7;
        this.uptimeClockProvider = r8;
        this.clientHealthMetricsStoreProvider = r9;
    }

    public static Uploader_Factory create(Provider<Context> r10, Provider<BackendRegistry> r11, Provider<EventStore> r12, Provider<WorkScheduler> r13, Provider<Executor> r14, Provider<SynchronizationGuard> r15, Provider<Clock> r16, Provider<Clock> r17, Provider<ClientHealthMetricsStore> r18) {
        return new Uploader_Factory(r10, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    public static Uploader newInstance(Context r10, BackendRegistry r11, EventStore r12, WorkScheduler r13, Executor r14, SynchronizationGuard r15, Clock r16, Clock r17, ClientHealthMetricsStore r18) {
        return new Uploader(r10, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    @Override // javax.inject.Provider
    public Uploader get() {
        return newInstance(this.contextProvider.get(), this.backendRegistryProvider.get(), this.eventStoreProvider.get(), this.workSchedulerProvider.get(), this.executorProvider.get(), this.guardProvider.get(), this.clockProvider.get(), this.uptimeClockProvider.get(), this.clientHealthMetricsStoreProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
