package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DefaultScheduler_Factory implements Factory<DefaultScheduler> {
    private final Provider<BackendRegistry> backendRegistryProvider;
    private final Provider<EventStore> eventStoreProvider;
    private final Provider<Executor> executorProvider;
    private final Provider<SynchronizationGuard> guardProvider;
    private final Provider<WorkScheduler> workSchedulerProvider;

    public DefaultScheduler_Factory(Provider<Executor> r1, Provider<BackendRegistry> r2, Provider<WorkScheduler> r3, Provider<EventStore> r4, Provider<SynchronizationGuard> r5) {
        this.executorProvider = r1;
        this.backendRegistryProvider = r2;
        this.workSchedulerProvider = r3;
        this.eventStoreProvider = r4;
        this.guardProvider = r5;
    }

    public static DefaultScheduler_Factory create(Provider<Executor> r6, Provider<BackendRegistry> r7, Provider<WorkScheduler> r8, Provider<EventStore> r9, Provider<SynchronizationGuard> r10) {
        return new DefaultScheduler_Factory(r6, r7, r8, r9, r10);
    }

    public static DefaultScheduler newInstance(Executor r6, BackendRegistry r7, WorkScheduler r8, EventStore r9, SynchronizationGuard r10) {
        return new DefaultScheduler(r6, r7, r8, r9, r10);
    }

    @Override // javax.inject.Provider
    public DefaultScheduler get() {
        return newInstance(this.executorProvider.get(), this.backendRegistryProvider.get(), this.workSchedulerProvider.get(), this.eventStoreProvider.get(), this.guardProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
