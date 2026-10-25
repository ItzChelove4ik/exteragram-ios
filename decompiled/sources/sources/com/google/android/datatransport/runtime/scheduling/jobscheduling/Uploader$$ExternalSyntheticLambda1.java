package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes4.dex */
public final /* synthetic */ class Uploader$$ExternalSyntheticLambda1 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ EventStore f$0;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda1(EventStore r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public final Object execute() {
        return Integer.valueOf(this.f$0.cleanUp());
    }
}
