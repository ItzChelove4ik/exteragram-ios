package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultScheduler$$ExternalSyntheticLambda1 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ DefaultScheduler f$0;
    public final /* synthetic */ TransportContext f$1;
    public final /* synthetic */ EventInternal f$2;

    public /* synthetic */ DefaultScheduler$$ExternalSyntheticLambda1(DefaultScheduler r1, TransportContext r2, EventInternal r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public final Object execute() {
        return DefaultScheduler.$r8$lambda$GlqmrSMOOB_fezGeVe6EN3_zTX0(this.f$0, this.f$1, this.f$2);
    }
}
