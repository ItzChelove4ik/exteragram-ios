package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.Map;

/* loaded from: classes4.dex */
public final /* synthetic */ class Uploader$$ExternalSyntheticLambda8 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;
    public final /* synthetic */ Map f$1;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda8(Uploader r1, Map r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
    public final Object execute() {
        return Uploader.$r8$lambda$agWmeBeuVmAYcPatmWqsXMsSeBk(this.f$0, this.f$1);
    }
}
