package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class WorkInitializer_Factory implements Factory<WorkInitializer> {
    private final Provider<Executor> executorProvider;
    private final Provider<SynchronizationGuard> guardProvider;
    private final Provider<WorkScheduler> schedulerProvider;
    private final Provider<EventStore> storeProvider;

    public WorkInitializer_Factory(Provider<Executor> r1, Provider<EventStore> r2, Provider<WorkScheduler> r3, Provider<SynchronizationGuard> r4) {
        this.executorProvider = r1;
        this.storeProvider = r2;
        this.schedulerProvider = r3;
        this.guardProvider = r4;
    }

    public static WorkInitializer_Factory create(Provider<Executor> r1, Provider<EventStore> r2, Provider<WorkScheduler> r3, Provider<SynchronizationGuard> r4) {
        return new WorkInitializer_Factory(r1, r2, r3, r4);
    }

    public static WorkInitializer newInstance(Executor r1, EventStore r2, WorkScheduler r3, SynchronizationGuard r4) {
        return new WorkInitializer(r1, r2, r3, r4);
    }

    @Override // javax.inject.Provider
    public WorkInitializer get() {
        return newInstance(this.executorProvider.get(), this.storeProvider.get(), this.schedulerProvider.get(), this.guardProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
