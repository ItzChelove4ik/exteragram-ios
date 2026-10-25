package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class DefaultScheduler implements Scheduler {
    private static final Logger LOGGER = null;
    private final BackendRegistry backendRegistry;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler workScheduler;

    public static /* synthetic */ Object $r8$lambda$GlqmrSMOOB_fezGeVe6EN3_zTX0(DefaultScheduler r1, TransportContext r2, EventInternal r3) {
        r1.eventStore.persist(r2, r3);
        r1.workScheduler.schedule(r2, 1);
        return null;
    }

    public static /* synthetic */ void $r8$lambda$e6JsNcl7eJ_uvseaJMOS9AGgb2A(DefaultScheduler r2, TransportContext r3, TransportScheduleCallback r4, EventInternal r5) {
        r2.getClass();
        TransportBackend r0 = r2.backendRegistry.get(r3.getBackendName());     // Catch: Exception -> L7
        if (r0 != null) goto L9;
        String r22 = String.format("Transport backend '%s' is not registered", new Object[]{r3.getBackendName()});     // Catch: Exception -> L7
        LOGGER.warning(r22);     // Catch: Exception -> L7
        r4.onSchedule(new IllegalArgumentException(r22));     // Catch: Exception -> L7
        return;
    L9:
        r2.guard.runCriticalSection(new DefaultScheduler$$ExternalSyntheticLambda1(r2, r3, r0.decorate(r5)));     // Catch: Exception -> L7
        r4.onSchedule(null);     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        LOGGER.warning("Error scheduling event " + e.getMessage());
        r4.onSchedule(e);
    }

    static {
        LOGGER = Logger.getLogger(TransportRuntime.class.getName());
    }

    public DefaultScheduler(Executor r1, BackendRegistry r2, WorkScheduler r3, EventStore r4, SynchronizationGuard r5) {
        this.executor = r1;
        this.backendRegistry = r2;
        this.workScheduler = r3;
        this.eventStore = r4;
        this.guard = r5;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.Scheduler
    public void schedule(TransportContext r3, EventInternal r4, TransportScheduleCallback r5) {
        this.executor.execute(new DefaultScheduler$$ExternalSyntheticLambda0(this, r3, r5, r4));
    }
}
