package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes4.dex */
public final /* synthetic */ class Uploader$$ExternalSyntheticLambda10 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ ClientHealthMetricsStore f$0;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda10(ClientHealthMetricsStore r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public final Object execute() {
        return this.f$0.loadClientMetrics();
    }
}
