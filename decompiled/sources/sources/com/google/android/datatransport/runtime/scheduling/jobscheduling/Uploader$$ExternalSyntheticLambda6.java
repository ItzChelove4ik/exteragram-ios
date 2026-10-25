package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes4.dex */
public final /* synthetic */ class Uploader$$ExternalSyntheticLambda6 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;
    public final /* synthetic */ Iterable f$1;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda6(Uploader r1, Iterable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public final Object execute() {
        return Uploader.$r8$lambda$WsHpQpWMGUydVPnrAR64q25St1g(this.f$0, this.f$1);
    }
}
