package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SchedulingModule_WorkSchedulerFactory implements Factory<WorkScheduler> {
    private final Provider<Clock> clockProvider;
    private final Provider<SchedulerConfig> configProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventStore> eventStoreProvider;

    public SchedulingModule_WorkSchedulerFactory(Provider<Context> r1, Provider<EventStore> r2, Provider<SchedulerConfig> r3, Provider<Clock> r4) {
        this.contextProvider = r1;
        this.eventStoreProvider = r2;
        this.configProvider = r3;
        this.clockProvider = r4;
    }

    public static SchedulingModule_WorkSchedulerFactory create(Provider<Context> r1, Provider<EventStore> r2, Provider<SchedulerConfig> r3, Provider<Clock> r4) {
        return new SchedulingModule_WorkSchedulerFactory(r1, r2, r3, r4);
    }

    public static WorkScheduler workScheduler(Context r0, EventStore r1, SchedulerConfig r2, Clock r3) {
        return (WorkScheduler) Preconditions.checkNotNullFromProvides(SchedulingModule.workScheduler(r0, r1, r2, r3));
    }

    @Override // javax.inject.Provider
    public WorkScheduler get() {
        return workScheduler(this.contextProvider.get(), this.eventStoreProvider.get(), this.configProvider.get(), this.clockProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
