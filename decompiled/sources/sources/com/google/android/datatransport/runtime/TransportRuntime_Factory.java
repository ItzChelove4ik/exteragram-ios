package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class TransportRuntime_Factory implements Factory<TransportRuntime> {
    private final Provider<Clock> eventClockProvider;
    private final Provider<WorkInitializer> initializerProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<Uploader> uploaderProvider;
    private final Provider<Clock> uptimeClockProvider;

    public TransportRuntime_Factory(Provider<Clock> r1, Provider<Clock> r2, Provider<Scheduler> r3, Provider<Uploader> r4, Provider<WorkInitializer> r5) {
        this.eventClockProvider = r1;
        this.uptimeClockProvider = r2;
        this.schedulerProvider = r3;
        this.uploaderProvider = r4;
        this.initializerProvider = r5;
    }

    public static TransportRuntime_Factory create(Provider<Clock> r6, Provider<Clock> r7, Provider<Scheduler> r8, Provider<Uploader> r9, Provider<WorkInitializer> r10) {
        return new TransportRuntime_Factory(r6, r7, r8, r9, r10);
    }

    public static TransportRuntime newInstance(Clock r6, Clock r7, Scheduler r8, Uploader r9, WorkInitializer r10) {
        return new TransportRuntime(r6, r7, r8, r9, r10);
    }

    @Override // javax.inject.Provider
    public TransportRuntime get() {
        return newInstance(this.eventClockProvider.get(), this.uptimeClockProvider.get(), this.schedulerProvider.get(), this.uploaderProvider.get(), this.initializerProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
