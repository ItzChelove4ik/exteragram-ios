package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* loaded from: classes4.dex */
public final /* synthetic */ class Uploader$$ExternalSyntheticLambda5 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;
    public final /* synthetic */ Iterable f$1;
    public final /* synthetic */ TransportContext f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda5(Uploader r1, Iterable r2, TransportContext r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public final Object execute() {
        return Uploader.$r8$lambda$KczLz_Q_NejuT7e4eizXNubl_f4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
