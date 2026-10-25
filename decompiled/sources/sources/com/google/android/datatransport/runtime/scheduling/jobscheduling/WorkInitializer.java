package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class WorkInitializer {
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler scheduler;
    private final EventStore store;

    /* renamed from: $r8$lambda$-ZllettEmXhg2KekoGpGM9ilHcY, reason: not valid java name */
    public static /* synthetic */ Object m1807$r8$lambda$ZllettEmXhg2KekoGpGM9ilHcY(WorkInitializer r4) {
        Iterator<TransportContext> r0 = r4.store.loadActiveContexts().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        TransportContext r1 = r0.next();
        r4.scheduler.schedule(r1, 1);
        goto L4
    L6:
        return null;
    }

    /* renamed from: $r8$lambda$tpvOkK6TzmDn8PHPQtWD9jX7-wo, reason: not valid java name */
    public static /* synthetic */ void m1808$r8$lambda$tpvOkK6TzmDn8PHPQtWD9jX7wo(WorkInitializer r2) {
        r2.guard.runCriticalSection(new WorkInitializer$$ExternalSyntheticLambda1(r2));
    }

    public WorkInitializer(Executor r1, EventStore r2, WorkScheduler r3, SynchronizationGuard r4) {
        this.executor = r1;
        this.store = r2;
        this.scheduler = r3;
        this.guard = r4;
    }

    public void ensureContextsScheduled() {
        this.executor.execute(new WorkInitializer$$ExternalSyntheticLambda0(this));
    }
}
