package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes5.dex */
public final /* synthetic */ class SessionReportingCoordinator$$ExternalSyntheticLambda2 implements Continuation {
    public final /* synthetic */ SessionReportingCoordinator f$0;

    public /* synthetic */ SessionReportingCoordinator$$ExternalSyntheticLambda2(SessionReportingCoordinator r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task r1) {
        return Boolean.valueOf(SessionReportingCoordinator.$r8$lambda$QF1kRPr0KNFr9oZS58J5qfeKzRk(this.f$0, r1));
    }
}
